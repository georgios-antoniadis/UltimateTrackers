package gr.codelearn.app.repository;

import gr.codelearn.app.model.Die;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that contains all the logic that is needed for Java to communicate with the database. Pay extra notice to how
 * the queries are formed, as you will be called to "adapt" them for your coursework. For this example, there are two
 * methods:
 *
 * 1) The getAllDieResults() method selects all rows from the DiceTracker table and returns a list of Dice. A ResultSet
 * is practically the set of rows that is returned after the query is executed. After receiving the result set, a
 * die is created from the data that was returned, and added to a list, which is finally returned. The data that are
 * returned come in exactly the same order as they are seen in the database server. In this example, getLong(1) refers
 * to the position (index) 1 of the row, and is expected to be "collected" as a long. If we see in the database, for each
 * row in the DiceTracker table, the first column is an id, which happens to be an int(db)/long(java).
 *
 * 2) The saveResult() method saves each result from a die that the user requested to throw. Pay extra attention to the
 * so-called "prepared statement/query".  The query requests specifically to insert data into the result and throw_date
 * columns of our DiceTracker table, omitting the ID, which is auto-generated. So for each new entry, the ID is auto-
 * generated and the rest of the columns are inserted manually. Similarly to the other method, we need to set the
 * position of each "?" value, manually with result being the first position (index), and throw_date the second one. We
 * do not care about a result, as we simply want to execute the insertion and that's it.
 * 
 * Each of the trackers has its own copy of the get and save methods. This solution is not ideal due to its poor 
 * expandability and could be further optimized, however for the purposes of this application this implementation meets
 * the requirements.
 * 
 */
@Component
@Slf4j
public class DiceRepository {

    public List<Die> getAllDieResults() {
        List<Die> allDieResults = new ArrayList<>();
        try {
            String query = "SELECT * FROM DICETRACKER";
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                long id = resultSet.getLong(1);
                int result = resultSet.getInt(2);
                Timestamp throwDate = resultSet.getTimestamp(3);
                allDieResults.add(new Die(id, result, throwDate));
            }
        } catch (SQLException e) {
            log.error("For some reason, a connection could not be obtained", e);
        }
        return allDieResults;
    }

    public void saveResult(int result) {
        try {
            String query = "INSERT INTO DICETRACKER(result, throw_date) VALUES(?, ?)";
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


    // Animal Tracker

    public List<Die> getAllAnimalResults() {
        List<Die> allAnimalResults = new ArrayList<>();
        try {
            String query = "SELECT * FROM ANIMALTRACKER";
            Connection connection = DataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                long id = resultSet.getLong(1);
                int result = resultSet.getInt(2);
                Timestamp throwDate = resultSet.getTimestamp(3);
                allAnimalResults.add(new Die(id, result, throwDate));
            }
        } catch (SQLException e) {
            log.error("For some reason, a connection could not be obtained", e);
        }
        return allAnimalResults;
    }

    public void saveAnimal(int result) {
        try {
            String query = "INSERT INTO ANIMALTRACKER(result, throw_date) VALUES(?, ?)";
            Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, result);
            preparedStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("For some reason, a connection could not be obtained", e);
        }
    }

}
