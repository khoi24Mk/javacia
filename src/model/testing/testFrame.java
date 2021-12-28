package model.testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testFrame extends JFrame {

    JPanel jpMain;
    JPanel jpOption;

    public testFrame(){
        super("STH");

        this.setLayout(new FlowLayout());

        jpMain = new JPanel();
        jpMain.setPreferredSize(new Dimension(400,500));
        jpMain.setLayout(new FlowLayout());

        JButton btnGroup = new JButton("Create group");
        btnGroup.setSize(200,30);


        jpMain.add(btnGroup);


        this.add(jpMain);




        btnGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                testModel x = new testModel();

                jpMain.add(x.getPanel());
                jpMain.setVisible(true);
                refresh();
            }
        });

    }

    public void refresh(){
        this.setVisible(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        testFrame x = new testFrame();
        x.pack();
        x.setVisible(true);
    }

}
