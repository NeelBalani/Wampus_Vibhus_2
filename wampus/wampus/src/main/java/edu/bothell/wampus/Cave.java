package edu.bothell.wampus;

import java.util.Arrays;

public class Cave {

    private GameLocation[][] cave = new GameLocation[5][6];


    public Cave(){
        for(int i = 0; i < cave.length; i++){
            for(int j = 0; j < cave[i].length; j++){
                cave[i][j] = new GameLocation(i,j,generateLocationId(i, j));
                System.out.print(generateLocationId(i,j) + " " + Arrays.toString(cave[i][j].getLocation()));
            }System.out.println();
        }
        GameLocation testTrap = getLocationBasedOnCoords(1,0);
        testTrap.addObstacle(new Pit(testTrap));

        for(int j = 0; j < cave[1].length; j++){
            System.out.print(this.cave[0][j].getLocationId());
            System.out.println(Arrays.toString(this.cave[0][j].getLocation()));
        }
        
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
