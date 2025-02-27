package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationManager {
    // Properties
    private HashMap<Person, GameLocation> playerLocations = new HashMap<Person, GameLocation>();
    private HashMap<GameLocation, List<Object>> locationItems = new HashMap<GameLocation, List<Object>>();
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
            for(int y = 0; y < size[1]; x++){
                this.locationItems.put(this.cave.getLocationBasedOnCoords(x, y), null);
            }
        }
    }

    public void setNewPlayers (List<Person> players){
        for(Person p: players){
            this.playerLocations.put(p, this.cave.spawnPoint());
            List<Object> o = this.locationItems.get(this.cave.spawnPoint());
            this.locationItems.replace(this.cave.spawnPoint(), o);
        }
    }

    public GameLocation getGameLocationOfPerson(Person p){
        return this.playerLocations.get(p);
    }

    public List<Object> getPersonsInLocation(GameLocation l){
        return this.locationItems.get(l);
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
