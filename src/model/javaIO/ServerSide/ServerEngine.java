package model.javaIO.ServerSide;



import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerEngine /*extends Thread*/{

    /*static BufferedReader br = null;*/
    /*static BufferedWriter bw = null;*/

    public enum itsType {
        READFROMCLIENT,
        WRITETOCLIENT
    };

    itsType _flag;
    ServerMessenger _msg;
    /*Socket _client;*/

    ArrayList<Socket> _listClient;

    public ServerEngine( itsType flag, ServerMessenger msg) throws IOException {

        this._flag = flag;
        this._msg = msg;
        /*this._client = client;*/
        this._listClient = new ArrayList<>();

        if (_flag == itsType.WRITETOCLIENT){
            try {
                writeToClient();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*@Override
    public void run(){
        if (_flag == itsType.WRITETOCLIENT){
            try {
                writeToClient();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if (_flag == itsType.READFROMCLIENT){
            try {
                readFromClient();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/

    public void addClient(Socket client) throws IOException {
        _listClient.add(client);


        if (_flag == itsType.WRITETOCLIENT){
            /*writeToClient(client);*/

        }
        else if (_flag == itsType.READFROMCLIENT){
            try {
                readFromClient(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void readFromClient(Socket client) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    System.out.println("Waiting msg from client HERE hererrrr");
                    String temp = null;
                    try {
                        temp = br.readLine();
                        System.out.println("reading: "+temp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (temp.equals("FILE")){
                        try {
                            readFileFromClient(client);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("OUT");
                        continue;
                    }
                    try {
                        _msg.set_msg(temp);

                        for (Socket eachClient: _listClient){
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter( eachClient.getOutputStream()));
                            bw.write(temp);
                            bw.newLine(); //HERE!!!!!!
                            bw.flush();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void readFileFromClient(Socket client) throws IOException {
        System.out.println("START");
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        byte [] bytes = new byte[16*1024];
        InputStream in =  client.getInputStream();;
        OutputStream out = new FileOutputStream("src/model/output.txt");;

        int count;
        count = in.read(bytes);
        System.out.println("LOOP");
        out.write(bytes, 0, count);

        for (Socket eachClient :_listClient){
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter( eachClient.getOutputStream()));
            bw.write("FILE");
            bw.newLine(); //HERE!!!!!!
            bw.flush();
            writeFileToClient(eachClient);
        }



        System.out.println("END");

    }

    public void writeFileToClient(Socket client) throws IOException {
        File file = new File("src/model/output.txt");
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = client.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
    }

    public void writeToClient() throws IOException {
        System.out.println("Waiting input . . .");




        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    String temp = null;
                    try {
                        System.out.println("get close");

                        temp = _msg.get_msg();
                        System.out.println("Notified . . ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        for (Socket client: _listClient){
                            System.out.println("SENDING: "+temp);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter( client.getOutputStream()));
                            bw.write(temp);
                            bw.newLine(); //HERE!!!!!!
                            bw.flush();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }


            }
        }.start();


    }


}
