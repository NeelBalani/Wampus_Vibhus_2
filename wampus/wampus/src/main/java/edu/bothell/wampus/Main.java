package edu.bothell.wampus;

public class Main {
    public static void main(String[] args) {
        // Initialize the controller and UI
        Controller controller = new Controller();
        UI ui = new ConsoleUI(controller);

    }
}
