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
    public List<Die> getResults(String tracker){
        return diceRepository.getAllResults(tracker);
    }

    public int throwDie(String tracker){
        int min = 1;
        int max = 6;
        int result = ThreadLocalRandom.current().nextInt(min, max + 1);
        log.info("A die was thrown with the result being: {}", result);
        diceRepository.saveResult(result, tracker);
        // we want the result of the throw to be returned so that the user also knows what it was
        return result;
    }

    public Object emptyStats(String tracker){
        diceRepository.resetStats(tracker);
        return null;
    }

    public List<Log> getAllLogs(String tracker){
        return diceRepository.getAllLogs(tracker);
    }

    // Loggers, write to db methods
    public Object addLog(String tracker){
        diceRepository.log(tracker);
        return null;
    }

    public Object emptyLogs(String tracker){
        diceRepository.resetLogs(tracker);
        return null;
    }
}
