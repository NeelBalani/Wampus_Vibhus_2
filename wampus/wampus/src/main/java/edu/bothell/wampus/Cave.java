package edu.bothell.wampus;

import java.util.Arrays;

public class Cave {

    private GameLocation[][] cave = new GameLocation[5][6];


    public Cave(){
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


}
