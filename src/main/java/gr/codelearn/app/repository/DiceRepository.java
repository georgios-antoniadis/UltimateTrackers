package gr.codelearn.app.repository;

import gr.codelearn.app.model.Die;
import gr.codelearn.app.model.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class DiceRepository {

    // Listing dice throw results for each tracker
    public List<Die> getAllResults(String tracker) {
        List<Die> allResults = new ArrayList<>();
        try {
            String query;
            if (tracker == "dice"){
                query = "SELECT * FROM DICETRACKER";
            }
            else if (tracker == "animal"){
                query = "SELECT * FROM ANIMALTRACKER";
            }
            else{
                query = "SELECT * FROM GEOMETRICSHAPETRACKER";
            }
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                long id = resultSet.getLong(1);
                int result = resultSet.getInt(2);
                Timestamp throwDate = resultSet.getTimestamp(3);
                allResults.add(new Die(id, result, throwDate));
            }
        } catch (SQLException e) {
            log.error("For some reason, a connection could not be obtained", e);
        }
        return allResults;
    }

    // Storing the new dice throw to the corresponding table
    public void saveResult(int result, String tracker) {
        try {
            String query;
            if (tracker == "dice"){
                query = "INSERT INTO DICETRACKER(result, throw_date) VALUES(?, ?)";
            }
            else if (tracker == "animal"){
                query = "INSERT INTO ANIMALTRACKER(result, throw_date) VALUES(?, ?)";
            }
            else{
                query = "INSERT INTO GEOMETRICSHAPETRACKER(result, throw_date) VALUES(?, ?)";
            }
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, result);
            // created a date (current date) and sets it as SQL's timestamp instance, which is required
            preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("For some reason, a connection could not be obtained", e);
        }
    }

    // Method to reset the tracker's tables that keep track of dice throws for each tracker
    public void resetStats(String tracker){
        try{
            String query;
            if (tracker == "dice"){
                query = "TRUNCATE TABLE DICETRACKER";
            }
            else if (tracker == "animal"){
                query = "TRUNCATE TABLE ANIMALTRACKER";
            }
            else{
                query = "TRUNCATE TABLE GEOMETRICSHAPETRACKER";
            }
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("For some reason, a connection could not be obtained", e);
        }
    }

    // Method that logs a visit each tracker page
    public void log(String tracker){
        try{
            String query;
            if (tracker == "dice"){
                query = "INSERT INTO DICELOG(visit_time) VALUES(?)";
            }
            else if (tracker == "animal"){
                query = "INSERT INTO ANIMALLOG(visit_time) VALUES(?)";
            }
            else{
                query = "INSERT INTO SHAPELOG(visit_time) VALUES(?)";
            }
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("For some reason, a connection could not be obtained", e);
        }
    }

    // Fetch log information from log tables
    public List<Log> getAllLogs(String tracker) {
        List<Log> allLogs = new ArrayList<>();
        try {
            String query;
            if (tracker == "dice"){
                query = "SELECT * FROM DICELOG";
            }
            else if (tracker == "animal"){
                query = "SELECT * FROM ANIMALLOG";
            }
            else{
                query = "SELECT * FROM SHAPELOG";
            }
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                long id = resultSet.getLong(1);
                Timestamp visitDate = resultSet.getTimestamp(2);
                allLogs.add(new Log(id, visitDate));
            }
        } catch (SQLException e) {
            log.error("For some reason, a connection could not be obtained", e);
        }
        return allLogs;
    }

    // Emptying log tables
    public void resetLogs(String tracker){
        try{
            String query;
            if (tracker == "dice"){
                query = "TRUNCATE TABLE DICELOG";
            }
            else if (tracker == "animal"){
                query = "TRUNCATE TABLE ANIMALLOG";
            }
            else{
                query = "TRUNCATE TABLE SHAPELOG";
            }
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("For some reason, a connection could not be obtained", e);
        }
    }
}
