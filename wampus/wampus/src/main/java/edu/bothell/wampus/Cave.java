package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.Arrays;

public class Cave {

    // Properties
    private GameLocation[][] cave;

    // Constructors
    
    public Cave(GameLocation[][] c){
        this.cave = c;
    }

    public Cave(){
        this.cave = new GameLocation[2][3];

        for(int y = 0; y < cave.length; y++){
            for(int x = 0; x < cave[y].length; x++){
                cave[y][x] = new GameLocation(x,y,generateLocationId(y, x));
                System.out.print(generateLocationId(y,x) + " " + Arrays.toString(cave[y][x].getLocation()));
            }System.out.println();
        }
        GameLocation testTrap = getLocationBasedOnCoords(1,0);
        testTrap.addObstacle(new Pit(testTrap));
        GameLocation wumpusLocation = getLocationBasedOnCoords(2, 3);
        wumpusLocation.addObstacle(new Wumpus(wumpusLocation));
    }

    public GameLocation spawnPoint(){
        return cave[0][0];
    }

    public int generateLocationId(int i, int j){
        return i * this.cave[0].length + j + 1;
    }

    public int[] getSize(){
        return new int[] {this.cave.length, this.cave[0].length};
    }

    public GameLocation getLocationBasedOnCoords(int y, int x){
        return this.cave[y][x];
    }

    public GameLocation[][] getCave(){
        return this.cave;
    }

    public ArrayList<GameLocation> getAdjacentLocations(GameLocation target, LocationManager lm){
        ArrayList<GameLocation> adjacentLocations = new ArrayList<GameLocation>();
        GameLocation adjecent;

        for(Directions d : Directions.values()){
            adjecent = lm.getGameLocationInThisDirection(target, d);
            adjacentLocations.add(adjecent);
        }

        return adjacentLocations;
    }


}
