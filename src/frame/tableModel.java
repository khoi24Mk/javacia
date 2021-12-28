package frame;

import model.Student;
import string.ConnectDataBase;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class tableModel extends JPanel {


    ArrayList<Student> listStudent;  //++++++++++++++++++++++++++++
    JTable packageTalbe;
    DefaultTableModel modelListPackage;

    public tableModel()  throws SQLException {
        // getting data from database
        ResultSet res= ConnectDataBase.getStudent();  //+++++++++++++++++++++++++++++++

        listStudent = new ArrayList<Student>(); //+++++++++++++++++++++++++++++++++++
        while(res.next()){

            Student temp = new Student();
            temp.set_ID(res.getString("_ID"));
            temp.set_name(res.getString("_name"));
            temp.set_class(res.getString("_class"));
            temp.set_age(res.getInt("_age"));
            listStudent.add(temp);


        }

        this.setOpaque(true);
        this.setBounds(0,0,400,100);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 250));

        modelListPackage = new DefaultTableModel( //++++++++++++++++++++++++
                Student.getHeader(),
                0
        );
        for(Student x : listStudent){ //+++++++++++++++++++++++++++++++++++
            modelListPackage.addRow(x.getObjects());
        }

        packageTalbe = new JTable(modelListPackage);

        packageTalbe.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane pane = new JScrollPane(packageTalbe);
        this.add(pane, BorderLayout.CENTER);
        packageTalbe.setDefaultEditor(Object.class, null);
        packageTalbe.getTableHeader().setReorderingAllowed(false);



        /*packageTalbe.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                PackageInfoUI.turnOnButton();

                for(PackageClass x: listPackage){
                    System.out.println("selected: "+packageTalbe.convertRowIndexToModel(packageTalbe.getSelectedRow()));
                    if(x.get_ID().equals(packageTalbe.getValueAt(packageTalbe.getSelectedRow(), 0).toString())){
                        PackageInfoUI.selectRowData(x);
                    }

                }
                System.out.println(packageTalbe.getValueAt(packageTalbe.getSelectedRow(), 0).toString());

            }
        });*/



    }



    public void refreshTalbe() throws SQLException {
        ResultSet res= ConnectDataBase.getStudent();

        listStudent.clear();
        while(res.next()){

            Student temp = new Student();
            temp.set_ID(res.getString("_ID"));
            temp.set_name(res.getString("_name"));
            temp.set_class(res.getString("_class"));
            temp.set_age(res.getInt("_age"));
            listStudent.add(temp);
        }

        modelListPackage = new DefaultTableModel(
                Student.getHeader(),
                0
        );
        for(Student x : listStudent){
            modelListPackage.addRow(x.getObjects());
        }

        packageTalbe.setModel(modelListPackage);
    }
}
