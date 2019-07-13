package dbpos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    DBConnection() {
        try {
            File file = new File("resources/application.properties");
            FileInputStream is = new FileInputStream(file);
            Properties dbprop = new Properties();
            dbprop.load(is);
            String ip = dbprop.getProperty("ip");
            String port = dbprop.getProperty("port");
            String db = dbprop.getProperty("db");
            String user = dbprop.getProperty("user");
            String password = dbprop.getProperty("password");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
