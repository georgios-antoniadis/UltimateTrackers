package gr.codelearn.app.service;

import gr.codelearn.app.model.Die;
import gr.codelearn.app.repository.DiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that contains the basic business logic of our application. Simply calls the repository/database communication
 * class and returns results to our controller.
 */
@Service
@Slf4j
public class DiceService {

    private DiceRepository diceRepository;

    public DiceService() {
        diceRepository = new DiceRepository();
    }

    public List<Die> getAllDieResults(){
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

    // Animal Tracker

    public List<Die> getAllAnimalResults(){
        return diceRepository.getAllAnimalResults();
    }

    public int throwAnimal() {
        int min = 1;
        int max = 6;
        int result = ThreadLocalRandom.current().nextInt(min, max + 1);
        log.info("A die was thrown with the result being: {}", result);
        diceRepository.saveAnimal(result);
        // we want the result of the throw to be returned so that the user also knows what it was
        return result;
    }

    public List<Die> getAllGeometricShapeResults(){
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
}
