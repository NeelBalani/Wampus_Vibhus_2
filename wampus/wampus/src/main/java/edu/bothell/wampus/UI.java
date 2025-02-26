package edu.bothell.wampus;

import java.util.List;

public interface UI {
    void showMessage(String message);
    void showPersonTurn(Person person);
    int getActionChoice(List<String> actions);
    boolean askToContinue(String name);
    void displaySummary();
}
