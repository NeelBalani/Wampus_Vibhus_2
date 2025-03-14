package edu.bothell.wampus;

import java.util.List;

public class Game {
    private LocationManager locationManager;
    private List<Person> players;

    public Game(LocationManager locationManager, List<Person> players) {
        this.locationManager = locationManager;
        this.players = players;
    }

    public boolean canMove(Person p, Directions direction) {
        GameLocation currentLocation = locationManager.getGameLocationOfPerson(p);
        GameLocation targetLocation = locationManager.getGameLocationInThisDirection(currentLocation, direction);
        
        return targetLocation != null;
    }

    public void movePlayer(Person p, Directions direction, Result result) {
        if (canMove(p, direction)) {
            GameLocation oldLocation = this.locationManager.getGameLocationOfPerson(p);
            GameLocation newLocation = this.locationManager.getGameLocationInThisDirection(oldLocation, direction);
            this.locationManager.changeGameLocationOfPerson(p, newLocation, oldLocation);
    
    
            boolean obstacleTrigger = newLocation.didPersonTriggerObstacle();
            System.out.println(obstacleTrigger);
            if(obstacleTrigger) resolveHazard(p);
    
            result.playerMove(oldLocation, newLocation);
        } else {
            throw new IllegalArgumentException("Cannot move to the specified location.");
        }
    }

    public String getVisibleInfo(Person p) {
        GameLocation currentLocation = locationManager.getGameLocationOfPerson(p);
        List<Object> items = currentLocation.getItems();
        StringBuilder info = new StringBuilder("You see: ");
        for (Object item : items) {
            info.append(item.toString()).append(", ");
        }
        return info.toString();
    }

    public void resolveHazard(GameLocation currentLocation) {
        if (currentLocation.hasObstacle()) {
            Obstacle obstacle = currentLocation.getObstacle();
            if (obstacle instanceof Wumpus) {
                System.out.println("You encountered a Wumpus! Game Over.");
            } else {
                System.out.println("You encountered a hazard: " + obstacle);
            }
        }
    }
}
