package edu.bothell.wampus;

import java.util.List;

public class Game {
    private LocationManager locationManager;
    private List<Person> players;

    public Game(LocationManager locationManager, List<Person> players) {
        this.locationManager = locationManager;
        this.players = players;
    }

    public List getQuestions(){

        return null;
    }

    public boolean canMove(Person p, int newGameLocationId) {
        AdjacentGameLocation currentLocation = locationManager.getGameLocationOfPerson(p);
        
        System.out.println(newGameLocationId);

        return this.locationManager.doesGameLocationIdExist(newGameLocationId);
    }

    public LocationManager getLocationManager(){
        return this.locationManager;
    }

    public void syncPlayers(List<Person> people) {
        this.locationManager.setNewPlayers(people);
        this.players = people;
    }

    public void removePlayer(Person removedPerson) {
        this.players.remove(removedPerson);
    }

    public Result movePlayer(Person p, int newGameLocationId, Result result) {
        if (canMove(p, newGameLocationId)) {
            AdjacentGameLocation oldLocation = this.locationManager.getGameLocationOfPerson(p);
            AdjacentGameLocation newLocation = this.locationManager.getGameLocationBasedOnId(newGameLocationId);

            this.locationManager.changeGameLocationOfPerson(p, newLocation, oldLocation);
    
    
            boolean obstacleTrigger = newLocation.didPersonTriggerObstacle();
            System.out.println(obstacleTrigger);
            if(obstacleTrigger) resolveHazard(newLocation);
    
            result.playerMove(oldLocation, newLocation);
            return result;
        } else {
            throw new IllegalArgumentException("Cannot move to the specified location.");
        }
    }

    public String getVisibleInfo(Person p) {
        AdjacentGameLocation currentLocation = locationManager.getGameLocationOfPerson(p);
        List<Object> items = currentLocation.getItems();
        StringBuilder info = new StringBuilder("You see: ");
        for (Object item : items) {
            info.append(item.toString()).append(", ");
        }
        return info.toString();
    }

    public void resolveHazard(AdjacentGameLocation currentLocation) {
        if (currentLocation.hasObstacle()) {
            Obstacle obstacle = currentLocation.getObstacle();
            if (obstacle instanceof Wumpus) {
                System.out.println("You encountered a Wumpus! Game Over.");
            } else {
                System.out.println("You encountered a hazard: " + obstacle);
            }
        }
    }

    public List<Object> findObjectsInLocation(AdjacentGameLocation location) {
        return this.locationManager.getPersonsInLocation(location);
    }

    public boolean checkAnswer(int questionId, int selected) {
        return questionId == selected;
    }
}
