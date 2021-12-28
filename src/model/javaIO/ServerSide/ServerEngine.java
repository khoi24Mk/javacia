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
