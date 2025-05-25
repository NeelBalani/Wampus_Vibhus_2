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

    public AdjacentGameLocation movePlayer(Person activeTeammate, Directions directions) {
        AdjacentGameLocation currentGameLocation = this.getGameLocationOfPerson(activeTeammate);
        ArrayList<Integer> adjLocs = currentGameLocation.getAdjLocations();
        int directionIndex = directions.ordinal(); // Assumes Directions enum matches MapGraph.csv order

        // If direction index is out of bounds or wall (0), stay in place
        if (directionIndex >= adjLocs.size()) {
            return currentGameLocation;
        }
        int targetId = adjLocs.get(directionIndex);
        if (targetId == 0) {
            return currentGameLocation;
        }

        return this.cave.getLocationBasedOnId(targetId);
    }
}
