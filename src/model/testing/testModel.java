package model.testing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testModel{

    JPanel jpOption;
    JButton btnRun;
    JButton btnPause;
    JButton btnReset;
    JLabel jlNumber;

    Dosth x;
    testing.Thread1 a;
    testing.Thread2 b;

    Boolean _flag = true;




    public testModel(){
        System.out.println("STH HERE");
        jpOption = new JPanel();
        jpOption.setPreferredSize(new Dimension(300,150));

        jlNumber = new JLabel("0");
        jlNumber.setSize(120,120);
        jlNumber.setFont(new Font("Serif", Font.PLAIN, 50));
        jlNumber.setBorder(new EmptyBorder(10, 200, 10, 200));

        btnRun = new JButton("Run");
        btnRun.setSize(120,30);

        btnPause = new JButton("Pause");
        btnPause.setSize(120,30);

        btnReset = new JButton("Reset");
        btnReset.setSize(120,30);

        jpOption.add(jlNumber);
        jpOption.add(btnRun);
        jpOption.add(btnPause);
        jpOption.add(btnReset);

        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_flag){

                    System.out.println("RUN");
                    x = new Dosth(getTHIS());

                    a = new testing.Thread1(x);
                    b = new testing.Thread2(x);
                    a.start();
                    b.start();
                    _flag = false;
                }
                else{
                    System.out.println("RERUN");
                    a.notifyThread();
                    b.notifyThread();
                }





            }
        });

        btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.setPause();
                b.setPause();
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x.resetNumber();
            }
        });

    }

    public JPanel getPanel(){
        return jpOption;
    }
    public testModel getTHIS(){
        return this;
    }

     public void setLabel(String value){
        jlNumber.setText(value);
    }


}
