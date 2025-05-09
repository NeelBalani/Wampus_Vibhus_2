package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;


@Controller
public class WampusController{

    @GetMapping("/index")
    public String homePage() {
        return "index";
    }
    

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/location/{id}")
    public String getLocation(@PathVariable int id, Model model){        
        return "location";
    }


}

