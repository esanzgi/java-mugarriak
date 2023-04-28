package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {
    private static Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/mugarria_6";
    private static final String USER = "root";
    private static final String PASSWORD = "zubiri";

    public static Connection getConnection(){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conected with MYSQL database");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
