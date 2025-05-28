package edu.bothell.wampus;

import edu.bothell.wampus.models.AdjacentGameLocation;
import edu.bothell.wampus.models.hazards.Pit;
import edu.bothell.wampus.models.hazards.Wumpus;

import java.util.ArrayList;

public class AdjacentCave {

    private AdjacentGameLocation[] cave;

    
    public AdjacentCave(AdjacentGameLocation[] c){
        this.cave = c;
        AdjacentGameLocation testTrap = getLocationBasedOnId(14);
        testTrap.addObstacle(new Pit(testTrap));
        AdjacentGameLocation wumpusLocation = getLocationBasedOnId(8);
        wumpusLocation.addObstacle(new Wumpus(wumpusLocation));
        for(AdjacentGameLocation l : this.cave){
            if (l != null) {
                System.out.print(l.getLocationId() + " ");
            } else {
                System.out.print("[null] ");
            }
        }
    }

    public AdjacentCave(){
        this.cave = new AdjacentGameLocation[30];
        
        AdjacentGameLocation testTrap = getLocationBasedOnId(8);
        testTrap.addObstacle(new Pit(testTrap));
        AdjacentGameLocation wumpusLocation = getLocationBasedOnId(14);
        wumpusLocation.addObstacle(new Wumpus(wumpusLocation));
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

    public ArrayList<AdjacentGameLocation> getAdjacentLocations(AdjacentGameLocation target){
        ArrayList<Integer> adjacentGameLocationIds = target.getAdjGameLocationId();
        ArrayList<AdjacentGameLocation> adjacentGameLocations = new ArrayList<AdjacentGameLocation>();

        while(adjacentGameLocations.size() != adjacentGameLocationIds.size()){
            for(AdjacentGameLocation adjGameLoc : this.cave){
                if(adjacentGameLocationIds.contains(adjGameLoc.getLocationId())) adjacentGameLocations.add(adjGameLoc);
            }
        }
        return adjacentGameLocations;
    }


}
