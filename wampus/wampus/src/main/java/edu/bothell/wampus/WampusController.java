package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;


@Controller
public class WampusController{

    private Cave cave = new Cave();
    private List<Person> players = new ArrayList<Person>();
    private Game game = new Game(new LocationManager(this.cave), null);

    @GetMapping("/index")
    public String homePage() {
        return "index";
    }
    

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    
}

