package edu.bothell.wampus.interfaces;

import edu.bothell.wampus.models.people.Person;

import java.util.List;

public interface UI {
    void showMessage(String message);
    void showPersonTurn(Person person);
    int getActionChoice(List<String> actions);
    boolean askToContinue(String name);
    void displaySummary();
}
