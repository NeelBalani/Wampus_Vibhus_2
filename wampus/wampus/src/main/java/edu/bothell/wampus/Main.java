package edu.bothell.wampus;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Initialize the controller and UI
        Controller controller = new Controller();
        UI ui = new ConsoleUI(controller);
        controller.addPerson(new Pranav("Pranav"));
        controller.addPlayersToLocationManager();
        //new GUI(controller);
        controller.setNewPlayers();
        LocationManager locationManager = new LocationManager(controller.getCave());
        System.out.println(
            "is it testing"
        );
        //Arrays controller.getCave().getAdjacentLocations(controller.getCave().getLocationBasedOnCoords(1, 1), locationManager));
        // controller.start();
    }
}
