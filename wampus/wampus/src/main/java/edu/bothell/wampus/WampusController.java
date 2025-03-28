package edu.bothell.wampus;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class WampusController{
    @GetMapping("/hello")
    public String hello() {
        return "sigma sigma boy sigma boy sigma boy";
    }
}

