package model.javaIO.ServerSide;

import frame.ClientFrame;
import frame.ServerFrame;
import model.javaIO.ClientSide.ClientEngine;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;


public class server {


    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket ss = new ServerSocket(4444);
        System.out.println("Server is listening . . .");






        Socket client = null;

        ServerMessenger msgIN =new ServerMessenger(true);
        ServerMessenger msgOUT =new ServerMessenger(false);


        ServerEngine receiveFromClient = new ServerEngine(ServerEngine.itsType.READFROMCLIENT, msgIN);
        /*receiveFromClient.start();*/

        ServerEngine sendToClient = new ServerEngine(ServerEngine.itsType.WRITETOCLIENT, msgOUT);
        /*sendToClient.start();*/



        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    new ServerFrame(msgOUT,msgIN);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        int count = 0;
        while (true){
            System.out.println("Counting: " + count);


            client = ss.accept();
            System.out.println("Accept client " +client.getInetAddress().getHostAddress() + " - "+ client.getPort());


            receiveFromClient.addClient(client);
            sendToClient.addClient(client);
        }
    }
}









