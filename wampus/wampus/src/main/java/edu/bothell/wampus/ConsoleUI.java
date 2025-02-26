package edu.bothell.wampus;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements UI {

    // Properties
    private Scanner scanner;
    private Controller controller;

        
    // Constructors
    public ConsoleUI(Controller controller) {
        scanner = new Scanner(System.in);
        this.controller = controller;
        this.controller.setUI(this);
    }



    // Methods
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showPersonTurn(Person person) {
        showMessage(person.getName() + "'s turn");
    }

    @Override
    public int getActionChoice(List<String> actions) {
        for (int index = 0; index < actions.size(); index++) {
            showMessage(index + ": " + actions.get(index));
        }
        showMessage("Type the number of the action you want to do:");

        int actionChoice = Integer.parseInt(scanner.nextLine());
        return actionChoice;
    }

    @Override
    public boolean askToContinue(String name) {
        showMessage("Do you want to continue (y/n)?");
        String choiceString = scanner.nextLine();
        return !choiceString.equalsIgnoreCase("n");
    }

    @Override
    public void displaySummary() {
        List<Result> results = this.controller.getSummary();
        for(Result r : results){
            showMessage(r.getMessage());
        }
    }
}
