package frame;

import model.javaIO.ClientSide.ClientMessenger;
import model.javaIO.ServerSide.ServerMessenger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerFrame extends JFrame{

    ServerMessenger _msgIN;
    ServerMessenger _msgOUT;

    JTextField textOutput;

    public ServerFrame(ServerMessenger msgOUT, ServerMessenger msgIN) throws InterruptedException {
        super("Server");


        _msgOUT = msgOUT;
        _msgIN = msgIN;

        this.setSize(500,500);
        this.setLayout(null);

        JButton btnInput = new JButton("Send");
        btnInput.setBounds(10,10,120,30);


        JTextField textInput = new JTextField();
        textInput.setBounds(10,40,200,200);

        textOutput = new JTextField();
        textOutput.setBounds(10,300,200,200);


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

        setInputText();
    }

    public void setInputText() throws InterruptedException {
        while (true){
            String temp = _msgIN.get_msg();
            System.out.println("frame msg: "+temp);
            textOutput.setText(temp);
        }

    }




}
