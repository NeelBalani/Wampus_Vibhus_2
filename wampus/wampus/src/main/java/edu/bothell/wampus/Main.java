package edu.bothell.wampus;

public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller();
        UI ui = new ConsoleUI(controller);

        controller.addPerson(new Pranav("Pranav"));
        controller.addPlayersToLocationManager();
        new GUI(controller);
           //controller.start();*/
    }
}
