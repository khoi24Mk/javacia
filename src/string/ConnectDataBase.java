package string;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConnectDataBase {

    public static Connection connection = null;

    //MySQL
    public static Connection createConnection() {
        try {
            // Load the JDBC driver//org.gjt.mm.mysql.Driver
            String driverName = "com.mysql.jdbc.Driver"; // MySQL MM JDBC driver
            //Class.forName(driverName);
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            String serverName = "localhost:3306";
            String mydatabase = "testing";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // a JDBC url
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            // Could not find the database driver
        } catch (SQLException e) {
            // Could not connect to the database
        }
        return null;
    }

    public static ResultSet getStudent() throws SQLException {
        Statement statement = ConnectDataBase.createConnection().createStatement();
        String sql = "SELECT * FROM student;";
        return statement.executeQuery(sql);
    }

    public ResultSet doSth() throws SQLException {
        Statement statement = ConnectDataBase.createConnection().createStatement();
        String sql = "SELECT * FROM patient_history;";
        return statement.executeQuery(sql);
    }


    public void doStuff() throws SQLException {
        String sql = "SET IDENTITY_INSERT payment_history ON \n" +
                "INSERT INTO payment_history(payment_history_id, citizen_id, payment_date,payment_amount) " +
                "VALUES (?, ?, ?, ?) \n" +
                "SET IDENTITY_INSERT payment_history OFF";
        PreparedStatement statement = null;
        try {
            statement = ConnectDataBase.createConnection().prepareStatement(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        statement.setString(1, "1");
        statement.setString(2, "1");
        statement.setString(3, "1");
        statement.setString(4, "1");
        statement.executeUpdate();
    }











}















