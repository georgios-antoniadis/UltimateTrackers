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
 * Class that performs the connection with the database. Within this class, one can change the database server used, as
 * well as set username, password and connection URL. Knowledge of how JDBC works is advised if one decides to NOT use
 * MySQL and wants to replace it with some other server. Practically, the only thing that needs to be changed here
 * are the JDBC_DRIVER, DB_URL, USERNAME and PASSWORD fields.
 *
 * The executeScriptSQL() method executes a set of queries during the initialization of the application. These queries
 * are usually for the creation/drops of database tables etc., and can be found under the directory:
 * resources/sql.properties
 */
@Component
@Slf4j
public class DataSource {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.h2.Driver";
    // Database URL
    private static final String DB_URL = "jdbc:h2:mem:diceTracker;DB_CLOSE_DELAY=-1";
    //Database credentials
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "sa";

    private static Connection connection;
    private static final Properties sqlCommands = new Properties();

    // we only want the following to happen once at the beginning, during initialization
    static {
        try {
            getSqlProperties();
            getConnection();
            executeScriptSQL();
            // the only reason we wanted the connection to stay up that moment was to execute the statements and to see
            // if a connection with the database was possible
            connection.close();
            connection = null;
        } catch (SQLException e) {
            log.error("Something went wrong while connecting to the database.", e);
            log.info("Shutting down application...");
            System.exit(-1);
        }

    }

    private static void executeScriptSQL() {
        sqlCommands.forEach((property, value) -> {
            // logging occur so that the user can see what queries are executed
            // pay extra notice to see if new queries you add are the expected queries
            log.info("Executing query with property name: {}", property);
            log.info(String.valueOf(value));
            try {
                Statement statement = connection.createStatement();
                statement.execute(String.valueOf(value));
            } catch (SQLException e) {
                log.info("Error executing query", e);
                System.exit(-1);
            }
        });

    }

    private DataSource() {
    }

    public static Properties getSqlProperties() {
        if (sqlCommands.isEmpty()) {
            try (InputStream inputStream = DataSource.class.getClassLoader()
                    .getResourceAsStream("sql.properties")) {
                if (inputStream == null) {
                    log.error("Unable to find sql.properties, exiting application.");
                }
                sqlCommands.load(inputStream);
            } catch (IOException e) {
                log.error("Unable to parse sql.properties", e);
            }
        }
        return sqlCommands;
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