package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.List;

public class Neel extends Person {
    private String name;
    private List<String> actions;

    public Neel(String name) {
        super(name);
        this.name = name;
        this.actions = new ArrayList<>();
        actions.add("Shoot");
        actions.add("Move");
        actions.add("Heal");
    }
}
