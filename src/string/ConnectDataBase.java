package string;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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


}
