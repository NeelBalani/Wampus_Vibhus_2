package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationManager {
    // Properties
    private HashMap<Person, GameLocation> playerLocations = new HashMap<Person, GameLocation>();
    private Cave cave;

    // Constructors
    public LocationManager(List<Person> players, Cave cave){
        this.cave = cave;

        setGameLocations();
        setNewPlayers(players);
    }
    public LocationManager(Cave cave){
        this.cave = cave;
        setGameLocations();
    }

    // Methods

    public void setGameLocations(){
        int[] size = this.cave.getSize();
        for(int x = 0; x < size[0]; x++){
            for(int y = 0; y < size[1]; y++){
                System.out.print(this.cave.getLocationBasedOnCoords(x, y).getLocationId() + " ");
            } System.out.println();
        }
    }

    public void setNewPlayers (List<Person> players){
        for(Person p: players){
            this.playerLocations.put(p, this.cave.spawnPoint());
            this.cave.spawnPoint().addPersonToLocation(p);
            System.out.println(p.getName());
        }
    }


    public GameLocation getGameLocationOfPerson(Person p){
        return this.playerLocations.get(p);
    }

    public List<Object> getPersonsInLocation(GameLocation l){
        return l.getItems();
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
