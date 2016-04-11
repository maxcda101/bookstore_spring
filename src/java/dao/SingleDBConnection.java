package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.springframework.stereotype.Controller;

/**
 *
 * @author zOzDarKzOz
 * Manager connection
 */
@Controller
public class SingleDBConnection {

    private Connection conn;

    //get connection option in property file: config.properties
    private SingleDBConnection() {
        try {
            if (this.conn == null) {
                Properties properties = new Properties();
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/config/config.properties"));
                Class.forName(properties.getProperty("DRIVER_CLASSNAME"));
                this.conn = DriverManager.getConnection(properties.getProperty("CONNECT_STRING"));
            }
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static SingleDBConnection getMyDBConnection() {
        return new SingleDBConnection();
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
