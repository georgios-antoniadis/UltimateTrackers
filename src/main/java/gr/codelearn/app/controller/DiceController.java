package gr.codelearn.app.controller;

import gr.codelearn.app.service.DiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This class deals with the creation of endpoints that a user of our application can access. Endpoints are basically
 * the URLs a user can access through a browser. Each "endpoint" method is represented by three things:
 *
 * 1) A mapping which can be accessed through the GET HTTP Verb (@GetMapping) (usually  used for requesting a
 * html file or refreshing a page) or through the POST HTTP Verb (@PostMapping) (usually used for submitting forms).
 *
 * 2) A String return type. Each String return type represents an html/ftl file that is located under the directory:
 * resources/freemarker. The "return String" should have the same exact name as the files located under that directory.
 * There are cases where we can perform a redirection to another endpoint, as it can be seen in the throwDice() method.
 *
 * 3) Model & Redirect attributes. In both of these, you add attributes and values that are parsed to the return html/ftl
 * files. A Model attribute basically means that each time an endpoint is accessed, the returned file will ALWAYS
 * have an added value, even if it is null. A Redirect attribute is an attribute that is only returned during the
 * redirection towards another endpoint. This attribute will only be added during the redirection to that endpoint. Let's
 * say we have endpoint A, which returns a html/ftl file with a model attribute added to it, and endpoint B which redirects
 * to endpoint A and adds a redirect attribute. If the user accesses endpoint B, then the redirect attribute is added,
 * and then redirection to endpoint A happens. Endpoint A executes the code that is contained within the method (in this
 * case it adds a model attribute) and returns the html/ftl file. The final html/ftl file will not only contain the
 * model attribute that was added, but also the redirect attribute, as the original request begun from endpoint B.
 * If the user now decides to refresh the page, then endpoint A is accessed, and the only attribute that exists is the
 * model one. The redirect attribute disappears
 */
@Controller
public class DiceController {

    private DiceService diceService;

    public DiceController() {
        this.diceService = new DiceService();
    }

    @GetMapping("/") // default URL, so for example if application runs locally: localhost:8080/
    public String index() {
        return "index";
    }

    @GetMapping("/diceTracker/{check}")
    public String diceTracker(Model model, @PathVariable Integer check) {
        // model attribute which adds a list for the html/ftl file to process
        // in order to access it in the html/ftl file, you need to use the name typed as the first parameter
        // in this case "allDieResults"
        if (check == 0){
            model.addAttribute("diceLog", diceService.addLog("dice"));    
        }
        model.addAttribute("allDieResults", diceService.getResults("dice"));
        return "diceTracker";
    }

    @PostMapping("/throwDice")
    public String throwDice(RedirectAttributes redirectAttributes){
        // redirect attribute which simply adds what was the result of a throw
        redirectAttributes.addFlashAttribute("numberOfDice", diceService.throwDie("dice"));
        return "redirect:/diceTracker/1";
    }

    @PostMapping("/emptyDice")
    public String resetDice(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("numberOfDice", diceService.emptyLogs("dice"));
        return "redirect:/resetStatistics";
    }
    
    @PostMapping("/emptyDiceStats")
    public String resetDiceStats(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("numberOfDice", diceService.emptyStats("dice"));
        return "redirect:/resetStatistics";
    }

    // Start: Giorgos Antoniadis
    // Adding the animalTracker as another endpoint
    @GetMapping("/animalTracker/{check}")
    public String animalTracker(Model model, @PathVariable Integer check) {
        if (check == 0){
            model.addAttribute("animalLog", diceService.addLog("animal"));    
        }
        else{
            model.addAttribute("numberOfDice", diceService.throwDie("animal"));
        }
        model.addAttribute("allAnimalResults", diceService.getResults("animal"));
        return "animalTracker";
    }

    // Throw animal dice
    @PostMapping("/randomAnimal")
    public String randomAnimal(){
        return "redirect:/animalTracker/1";
    }

    @PostMapping("/emptyAnimal")
    public String resetAnimal(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("numberOfDice", diceService.emptyLogs("animal"));
        return "redirect:/resetStatistics";
    }

    @PostMapping("/emptyAnimalStats")
    public String resetAnimalStats(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("numberOfDice", diceService.emptyStats("animal"));
        return "redirect:/resetStatistics";
    }

    @GetMapping("/viewStatistics")
    public String viewStatistics(Model model) {
        // Returning stats page and adding as attributes the data from all tables
        model.addAttribute("allAnimalLogs", diceService.getAllLogs("animal"));
        model.addAttribute("allDiceLogs", diceService.getAllLogs("dice"));
        model.addAttribute("allShapeLogs", diceService.getAllLogs("shape"));
        return "viewStatistics";
    }

    // End: Giorgos Antoniadis

    @GetMapping("/resetStatistics")
    public String resetStatistics(Model model) {
        return "resetStatistics";
    }


    // Start: Vasilis Rozakos
    // Adding the geometricShapeTracker as another endpoint
    @GetMapping("/geometricShapeTracker/{check}")
    public String geometricShapeTracker(Model model, @PathVariable Integer check) {
        if (check == 0){
            model.addAttribute("shapeLog", diceService.addLog("shape"));    
        }
        model.addAttribute("allGeometricShapeResults", diceService.getResults("shape"));
        return "geometricShapeTracker";
    }

    // Throw geometric shape dice
    @PostMapping("/randomGeometricShape")
    public String randomGeometricShape(RedirectAttributes redirectAttributes){
        // redirect attribute which simply adds what was the result of a throw
        redirectAttributes.addFlashAttribute("numberOfDice", diceService.throwDie("shape"));
        return "redirect:/geometricShapeTracker/1";
    }

    @PostMapping("/emptyShape")
    public String resetShape(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("numberOfDice", diceService.emptyLogs("shape"));
        return "redirect:/resetStatistics";
    }

    @PostMapping("/emptyShapeStats")
    public String resetShapeStats(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("numberOfDice", diceService.emptyStats("shape"));
        return "redirect:/resetStatistics";
    }

}

