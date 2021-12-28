package model.javaIO.ClientSide;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientEngine extends Thread {

    static BufferedReader br = null;
    static BufferedWriter bw = null;

    public enum itsType{
        READFROMSERVER,
        SENDTOSERVER
    };

    itsType _flag;
    ClientMessenger _msg;
    Socket _client;

    /*SOCKET ++++++++++++++++++++++++++++++++++++++++++++++++*/

    public ClientEngine(itsType flag, ClientMessenger msg, Socket client){
        _flag = flag;
        _msg = msg;
        _client = client;
    }

    @Override
    public void run(){
        if(_flag == itsType.SENDTOSERVER){
            try {
                writeToServer();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(_flag == itsType.READFROMSERVER){
            try {
                readFromServer();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void readFromServer() throws IOException, InterruptedException {

        br = new BufferedReader( new InputStreamReader(_client.getInputStream()));

        while (true){
            String temp = br.readLine();

            if (temp.equals("FILE")){
                readFileFromServer();
                continue;
            }
            _msg.set_msg(temp);
            System.out.println(temp);
        }

    }

    public void readFileFromServer() throws IOException {

        byte [] bytes = new byte[16*1024];
        InputStream in =  _client.getInputStream();;
        OutputStream out = new FileOutputStream("src/model/clientfile.txt");;

        int count;
        count = in.read(bytes);
        System.out.println("LOOP");
        out.write(bytes, 0, count);


        System.out.println("END");
    }
    public void writeToServer() throws IOException, InterruptedException {

        bw = new BufferedWriter( new OutputStreamWriter(_client.getOutputStream()));
        Scanner ip = new Scanner(System.in);

        while (true){

            System.out.println("Chuan bi ban");
            String temp = "";
            System.out.println("Loading");
            temp = _msg.get_msg();
            System.out.println("Go ...");

            if (temp.equals("FILE")){
                bw.write("FILE");
                bw.newLine(); //HERE!!!!!!
                bw.flush();

                /*Thread.sleep(2000);*/

                writeFileToServer();

                continue;
            }

            ClientMessenger._flag = false;

            System.out.println("Send msg to Server: "+temp);
            bw.write(temp);
            bw.newLine(); //HERE!!!!!!
            bw.flush();

            System.out.println("----------------");
        }
    }

    public void writeFileToServer() throws IOException {
        File file = new File("src/model/test.txt");
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = _client.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
    }






}
