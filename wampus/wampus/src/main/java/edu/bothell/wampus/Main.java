package edu.bothell.wampus;

public class Main {
    public static void main(String[] args) {
        new GUI(new Controller(), new Cave());
        /*Controller controller = new Controller();
        UI ui = new ConsoleUI(controller);

        controller.addPerson(new Pranav("Pranav"));

        //controller.start();*/
    }
}
