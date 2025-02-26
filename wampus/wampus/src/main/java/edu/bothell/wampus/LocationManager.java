package edu.bothell.wampus;

import java.util.HashMap;
import java.util.List;

public class LocationManager {
    // Properties
    private HashMap<Person, GameLocation> playerLocations;
    private Cave cave;

    // Constructors
    public LocationManager(List<Person> players, Cave cave){
        this.cave = cave;

        setNewPlayers(players);
    }
    public LocationManager(Cave cave){
        this.cave = cave;
    }

    // Methods

    public void setNewPlayers (List<Person> players){
        for(Person p: players){
            playerLocations.put(p, this.cave.spawnPoint());
        }
    }

    public GameLocation getGameLocationOfPerson(Person p){
        return this.playerLocations.get(p);
    }

    public GameLocation getGameLocationInThisDirection(GameLocation initialLocation, Directions direction){
        int[] initialLocationValue = initialLocation.getLocation();
        int[] finalLocationValue = new int[2];
        finalLocationValue[0] = initialLocationValue[0] + direction.dX();
        finalLocationValue[1] = initialLocationValue[1] + direction.dY();

        GameLocation finalGameLocation;
        try{
            finalGameLocation = this.cave.getLocationBasedOnCoords(finalLocationValue[0], finalLocationValue[1]);
            return finalGameLocation;
        } catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public void changeGameLocationOfPerson(Person p, GameLocation newGameLocation){
        this.playerLocations.replace(p, newGameLocation);
    }
}
