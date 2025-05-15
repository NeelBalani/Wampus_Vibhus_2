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
       this.controller = new GameController("Wampus_Vibhus_2/wampus/wampus/src/main/java/edu/bothell/wampus/maps/MapGraph.csv");
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

    @GetMapping("/checkWinner")
    public String checkWinner(){
        return "checkWinner";
    }

    /*

    Doesn't work as html isn't in templates

    @GetMapping("/html")
    public String getMethodName(Model model) {
        return "html";
    }
    */
}

