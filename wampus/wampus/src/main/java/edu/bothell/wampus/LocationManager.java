package edu.bothell.wampus;

import java.util.*;

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
        for(int x = 0; x < size[1]; x++){
            for(int y = 0; y < size[0]; y++){
                System.out.print(this.cave.getLocationBasedOnCoords(y, x).getLocationId() + " ");
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
        System.out.println("initial value: " + Arrays.toString(initialLocationValue));
        int[] finalLocationValue = new int[2];
        finalLocationValue[0] = initialLocationValue[0] + direction.dX();
        System.out.println(finalLocationValue[0]);
        finalLocationValue[1] = initialLocationValue[1] + direction.dY();
        System.out.println(finalLocationValue[1]);

        GameLocation finalGameLocation;
        try{
            finalGameLocation = this.cave.getLocationBasedOnCoords(finalLocationValue[1], finalLocationValue[0]);
            return finalGameLocation;
        } catch(IndexOutOfBoundsException e){
            return null;
        }
    }

    public void changeGameLocationOfPerson(Person p, GameLocation newGameLocation, GameLocation oldLocation){
        this.playerLocations.replace(p, newGameLocation);
        oldLocation.removePlayerFromLocation(p);
        newGameLocation.addPersonToLocation(p);
    }
}
