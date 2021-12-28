package frame;

import model.javaIO.ClientSide.ClientMessenger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends JFrame {


    ClientMessenger _msgIN;
    ClientMessenger _msgOUT;

    JTextField textOutput;

    public ClientFrame(ClientMessenger msgOUT,ClientMessenger msgIN) throws InterruptedException {
        super("Client");

        _msgOUT = msgOUT;
        _msgIN = msgIN;

        this.setSize(500,500);
        this.setLayout(null);

        JButton btnInput = new JButton("Send");
        btnInput.setBounds(10,10,120,30);

        JButton btnFile = new JButton("FILE");
        btnFile.setBounds(10,40,120,30);


        JTextField textInput = new JTextField();
        textInput.setBounds(10,40,200,200);

        textOutput = new JTextField();
        textOutput.setBounds(10,300,200,200);


        this.add(btnFile);
        this.add(textOutput);
        this.add(btnInput);
        this.add(textInput);
        this.setVisible(true);


        btnInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CLICKED");

                synchronized (this){
                    System.out.println("IN");

                    String msg = textInput.getText();
                    /*messenger.set_msg(msg);*/
                    try {
                        System.out.println("nap dan");
                        _msgOUT.set_msg(msg);
                        System.out.println("nap dan xongggg");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }


            }
        });

        btnFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CLICKED");

                synchronized (this){
                    System.out.println("IN");


                    try {
                        System.out.println("nap dan");
                        _msgOUT.set_msg("FILE");
                        System.out.println("nap dan xongggg");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }


            }
        });

        setInputText();
    }

    public void setInputText() throws InterruptedException {
        while (true){
            String temp = _msgIN.get_msg();
            textOutput.setText(temp);
        }

    }




}
