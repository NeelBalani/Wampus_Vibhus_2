package edu.bothell.wampus;

import java.util.*;

public class LocationManager {
    // Properties
    private HashMap<Person, AdjacentGameLocation> playerLocations = new HashMap<Person, AdjacentGameLocation>();
    private AdjacentCave cave;

    // Constructors
    public LocationManager(List<Person> players, AdjacentCave cave){
        this.cave = cave;
        setNewPlayers(players);
    }
    public LocationManager(AdjacentCave cave){
        this.cave = cave;
    }

    // Methods

    public void setNewPlayers (List<Person> players){
        for(Person p: players){
            this.playerLocations.put(p, this.cave.spawnPoint());
            this.cave.spawnPoint().addPersonToLocation(p);
            System.out.println(p.getName());
        }
    }

    public AdjacentGameLocation getLocationInDirection(Directions direction, AdjacentGameLocation oldGameLocation) {
        int newLocationId = oldGameLocation.getLocationId() + direction.getShiftNumber();
        return this.cave.getLocationBasedOnId(newLocationId);
    }

    public AdjacentGameLocation getGameLocationOfPerson(Person p){
        return this.playerLocations.get(p);
    }

    public List<Object> getPersonsInLocation(AdjacentGameLocation l){
        return l.getItems();
    }

    public boolean doesGameLocationIdExist(int questionableId){
        try{
            this.cave.getLocationBasedOnId(questionableId);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public boolean canMoveFromLocationToLocation(AdjacentGameLocation currentLocation, int newGameLocationId) {
        return (currentLocation.getAdjLocations().contains(newGameLocationId) && doesGameLocationIdExist(newGameLocationId));
    }

    public boolean doesGameLocationExist(AdjacentGameLocation location){
        try{
            this.cave.getLocationBasedOnId(location.getLocationId());
        } catch(Exception e){
            return false;
        }
        return true;
    }

    public AdjacentGameLocation getGameLocationBasedOnId(int gameLocationId){
        return this.cave.getLocationBasedOnId(gameLocationId);
    }

    public void changeGameLocationOfPerson(Person p, AdjacentGameLocation newGameLocation, AdjacentGameLocation oldLocation){
        this.playerLocations.replace(p, newGameLocation);
        oldLocation.removePlayerFromLocation(p);
        newGameLocation.addPersonToLocation(p);
    }
    public AdjacentGameLocation getLocationOutOfBounds(Directions directions, AdjacentGameLocation gameLocationOfPerson) {
        System.out.println("out of bounds");
        int newLocationId = gameLocationOfPerson.getLocationId() + directions.getShiftNumber();
        if(newLocationId < 0){
            newLocationId += 30;
        }
        if(newLocationId > 30){
            newLocationId -= 31;
        }
        if(directions==Directions.N){
            return this.cave.getLocationBasedOnId(this.cave.getSize() + newLocationId - 1); // wrap around to the last location
        } 
        else if(directions==Directions.S){
            return this.cave.getLocationBasedOnId(newLocationId - this.cave.getSize() + 1); // wrap around to the first location
        }
        else if(directions==Directions.E){
            return this.cave.getLocationBasedOnId(newLocationId - 5); // wrap around to the first location
        }
        else if(directions==Directions.W){
            return this.cave.getLocationBasedOnId( newLocationId + 5); // wrap around to the last location
        }
        else {
            return this.cave.getLocationBasedOnId(newLocationId);
        }    
    }
}
