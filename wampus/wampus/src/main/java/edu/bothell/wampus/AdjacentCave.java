package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.Arrays;

public class AdjacentCave {

    private AdjacentGameLocation[] cave;

    
    public AdjacentCave(AdjacentGameLocation[] c){
        this.cave = c;
    }

    public AdjacentCave(){
        this.cave = new AdjacentGameLocation[30];
        
        //AdjacentGameLocation testTrap = getLocationBasedOnCoords(1,0);
        //testTrap.addObstacle(new Pit(testTrap));
        //AdjacentGameLocation wumpusLocation = getLocationBasedOnCoords(2, 3);
        //wumpusLocation.addObstacle(new Wumpus(wumpusLocation));
    }

    public AdjacentGameLocation spawnPoint(){
        return cave[0];
    }

    public int generateLocationId(int index){
        return index + 1;
    }

    public int getSize(){
        return this.cave.length;
    }

    public AdjacentGameLocation getLocationBasedOnId(int locationId){
        return this.cave[locationId - 1];
    }

    public AdjacentGameLocation[] getCave(){
        return this.cave;
    }

    public ArrayList<AdjacentGameLocation> getAdjacentLocations(AdjacentGameLocation target, AdjacentLocationManager lm){
        return target.getAdjGameLocations();
    }


}
