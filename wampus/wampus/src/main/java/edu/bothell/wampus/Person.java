package edu.bothell.wampus;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

public class Person implements Teammate {

    private String name;
    private List<String> actions;

    public Person(String name) {
        this.name = name;
        this.actions = new ArrayList<>();
        actions.add("Shoot");
        actions.add("Move");
        actions.add("Heal");
    }

    public String getName() {
        return name;
    }

    @Override
    public List<String> getActions() {
        return actions;
    }

    @Override
    public Result doAction(UI ui) {
        int actionIndex = ui.getActionChoice(actions);
        String action = actions.get(actionIndex);
        return new Result(action, this);
    }

    public Directions doMove(UI ui) {
        List<String> directions = new ArrayList<>();
        for(Directions d: Directions.values()){
            directions.add(d.name());
        }
        int actionIndex = ui.getActionChoice(directions);
        String stringDirection = directions.get(actionIndex);
        ui.showMessage(stringDirection);
        return Directions.valueOf(stringDirection);
    }
}
