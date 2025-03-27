package edu.bothell.wampus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WampusController{

    @GetMapping("/hello")
    public String hello() {
        return "tapushity";
    }
}

