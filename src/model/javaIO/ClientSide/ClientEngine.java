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
            _msg.set_msg(temp);
            System.out.println(temp);
        }

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

            ClientMessenger._flag = false;

            System.out.println("Send msg to Server: "+temp);
            bw.write(temp);
            bw.newLine(); //HERE!!!!!!
            bw.flush();

            System.out.println("----------------");

        }




    }

    public void readFromClient(Socket server, Runnable runMain) throws IOException {
        br = new BufferedReader(new InputStreamReader(server.getInputStream()));


        while(true){

            System.out.println("+TOSTRING+"+ runMain.toString());

            System.out.println("+3+"+
                    Thread.currentThread().getName());

            System.out.println("Waiting msg from client");
            String temp = br.readLine();
            /*messenger.set_msg(temp);*/
            System.out.println(temp);
            ClientMessenger._flag = false;
        }
    }



    public void writeToClient(Socket client) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter( client.getOutputStream()));
        Scanner ip = new Scanner(System.in);


        while (true){
            String temp = ip.nextLine();
            bw.write(temp);
            bw.newLine(); //HERE!!!!!!
            bw.flush();
        }
    }


}
