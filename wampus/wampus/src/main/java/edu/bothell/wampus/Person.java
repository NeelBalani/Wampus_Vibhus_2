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

    public int doMove(UI ui, ArrayList<Integer> adjacentIntegers) {
        List<String> idChoices = new ArrayList<String>();

        for(int adjacentIds : adjacentIntegers){
            idChoices.add(""+adjacentIds);
        }

        int actionIndex = ui.getActionChoice(idChoices);
        int chosenRoom = adjacentIntegers.get(actionIndex);
        ui.showMessage(""+chosenRoom);
        return chosenRoom;
    }
}
