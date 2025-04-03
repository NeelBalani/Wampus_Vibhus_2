package edu.bothell.wampus;

import java.util.*;

public class AdjacentLocationManager {
    // Properties
    private HashMap<Person, AdjacentGameLocation> playerLocations = new HashMap<Person, AdjacentGameLocation>();
    private AdjacentCave cave;

    // Constructors
    public AdjacentLocationManager(List<Person> players, AdjacentCave cave){
        this.cave = cave;

        setNewPlayers(players);
    }

    // Methods

    public void setNewPlayers (List<Person> players){
        for(Person p: players){
            this.playerLocations.put(p, this.cave.spawnPoint());
            this.cave.spawnPoint().addPersonToLocation(p);
            System.out.println(p.getName());
        }
    }


    public AdjacentGameLocation getGameLocationOfPerson(Person p){
        return this.playerLocations.get(p);
    }

    public List<Object> getPersonsInLocation(AdjacentGameLocation l){
        return l.getItems();
    }


    public void changeGameLocationOfPerson(Person p, AdjacentGameLocation newGameLocation, AdjacentGameLocation oldLocation){
        this.playerLocations.replace(p, newGameLocation);
        oldLocation.removePlayerFromLocation(p);
        newGameLocation.addPersonToLocation(p);
    }
}
