package edu.bothell.wampus;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class WampusController{

    GameController controller;

    @GetMapping("")
    public String homePage(Model model) throws FileNotFoundException {
        this.controller = new GameController("wampus/wampus/src/main/java/edu/bothell/wampus/maps/MapGraph.csv");
        controller.addPerson(new Pranav("Pranav"));
        return "index";

    }
    

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("whatever", "My most sweet value");
        return "hello";
    }

    @GetMapping("/location/{id}")
    public String getLocation(@PathVariable int id, Model model){
        return "location";
    }

    @GetMapping("/choosingPage/{previousAction}/{amount}")
    public String choosingPage(@PathVariable String previousAction, @PathVariable int amount, Model model){
        if(previousAction.equals("move")) moveCharacter(amount);

        return "choose_actions";
    }


    public boolean checkWinner(){
        return this.controller.gameOver();
    }

    /*

    Doesn't work as html isn't in templates

    @GetMapping("/html")
    public String getMethodName(Model model) {
        return "html";
    }
    */


    public String moveCharacter(int newLocation){
        this.controller.movePlayerUsingId(newLocation);
        // return this.controller.getDangersNearby()
        /*
        * Retrieve the dangers nearby
        * pass them in so it can be displayed what dangers are near
         */
        return null;
    }
}

