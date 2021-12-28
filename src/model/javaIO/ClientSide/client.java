package model.javaIO.ClientSide;

import frame.ClientFrame;

import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket(InetAddress.getLocalHost(),4444);
        ClientMessenger msgIN = new ClientMessenger(true);
        ClientMessenger msgOUT = new ClientMessenger(false);

        ClientEngine receiveFromServer = new ClientEngine(ClientEngine.itsType.READFROMSERVER, msgIN, client);
        receiveFromServer.start();

        ClientEngine sendToServer = new ClientEngine(ClientEngine.itsType.SENDTOSERVER, msgOUT, client);
        sendToServer.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    new ClientFrame(msgOUT,msgIN);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}