package gr.codelearn.app.service;

import gr.codelearn.app.model.Die;
import gr.codelearn.app.model.Log;
import gr.codelearn.app.repository.DiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that contains the basic business logic of our application. Simply calls the repository/database communication
 * class and returns results to our controller.
 * 
 * The throw method is replicated for each of the trackers.
 * 
 * The methods responsible for fetching data from the tracker tables are now also reponsible for logging the page's visits
 * That is because the methods are called once per visit at the page thus making them suitable for such a task.
 * 
 * Please note that currently a log is also counted when someone refreshes the page!
 * 
 */
@Service
@Slf4j
public class DiceService {

    private DiceRepository diceRepository;

    public DiceService() {
        diceRepository = new DiceRepository();
    }

    // The method now also calls the logger since it is only executed once per visit
    public List<Die> getAllDieResults(){
        diceRepository.logDice();
        return diceRepository.getAllDieResults();
    }

    public int throwDie() {
        int min = 1;
        int max = 6;
        int result = ThreadLocalRandom.current().nextInt(min, max + 1);
        log.info("A die was thrown with the result being: {}", result);
        diceRepository.saveResult(result);
        // we want the result of the throw to be returned so that the user also knows what it was
        return result;
    }

    public List<Log> getAllDiceLogs(){
        return diceRepository.getAllDiceLogs();
    }

    
    // Animal Tracker

    // The method now also calls the logger since it is only executed once per visit
    public List<Die> getAllAnimalResults(){
        diceRepository.logAnimal();
        return diceRepository.getAllAnimalResults();
    }

    public int throwAnimal() {
        int min = 1;
        int max = 6;
        int result = ThreadLocalRandom.current().nextInt(min, max + 1);
        log.info("A die was thrown with the result being: {}", result);
        diceRepository.saveAnimal(result);
        return result;
    }

    public List<Log> getAllAnimalLogs(){
        return diceRepository.getAllAnimalLogs();
    }


    // Shape Tracker

    public List<Die> getAllGeometricShapeResults(){
        diceRepository.logShape();
        return diceRepository.getAllGeometricShapeResults();
    }

    public int throwGeometricShape() {
        int min = 1;
        int max = 6;
        int result = ThreadLocalRandom.current().nextInt(min, max + 1);
        log.info("A die was thrown with the result being: {}", result);
        diceRepository.saveGeometricShape(result);
        // we want the result of the throw to be returned so that the user also knows what it was
        return result;
    }

    public List<Log> getAllShapeLogs(){
        return diceRepository.getAllShapeLogs();
    }

    public Object emptyDiceLogs(){
        diceRepository.resetDiceStats();
        return null;
    }

    public Object emptyAnimalLogs(){
        diceRepository.resetAnimalStats();
        return null;
    }

    public Object emptyShapeLogs(){
        diceRepository.resetShapeStats();
        return null;
    }
}
