package edu.bothell.wampus.models.people;

import java.util.ArrayList;
import java.util.List;

public class Vibhu extends Person {
    private String name;
    private List<String> actions;

    public Vibhu(String name) {
        super(name);
        this.name = name;
        this.actions = new ArrayList<>();
        actions.add("Shoot");
        actions.add("Move");
        actions.add("Heal");
    }
}
