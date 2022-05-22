package gr.codelearn.app.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Class that performs the connection with the database. Within this class is included the 
 * connection to the mysql database which is running on a separate docker container on the VM.
 * There is no longer a need for an sql.properties file since the application is designed to run
 * on an existing DB configuration which took place individually on the VM. All tables needed 
 * are already created and data is no longer coupled to the application. Meaning that with each new 
 * build of the application, data from previous build is not deleted.
 */

@Component
@Slf4j
public class DataSource {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // Database URL
    private static final String DB_URL = "jdbc:mysql://172.18.0.2:3306/ultimate_trackers";
    //Database credentials
    private static final String USERNAME = "root";
    private static final String PASSWORD = "0000";

    private static Connection connection;
    private static final Properties sqlCommands = new Properties();

    static {
        try {
            getConnection();
            connection.close();
            connection = null;
        } catch (SQLException e) {
            log.error("Something went wrong while connecting to the database.", e);
            log.info("Shutting down application...");
            System.exit(-1);
        }
    }

    private DataSource() {
    }


    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() throws SQLException {
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //Establishing connection with the database
            log.info("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            log.info("Connection with database established successfully...");
        } catch (ClassNotFoundException | SQLException e) {
            log.error("Connection with the database server could not be established.", e);
            throw new SQLException(e);
        }
    }
}
