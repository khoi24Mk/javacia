import frame.tableModel;
import model.Student;
import string.ConnectDataBase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class sthFrame extends JFrame {

    public sthFrame() throws SQLException {
        super("sth");

        this.setSize(500,500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);






        JPanel jpTable = new tableModel();











        this.add(jpTable);
        this.setVisible(true);
    }
}
