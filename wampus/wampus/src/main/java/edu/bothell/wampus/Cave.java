package edu.bothell.wampus;

public class Cave {

    private GameLocation[][] cave = new GameLocation[5][6];


    public Cave(){
        for(int i = 0; i < cave.length; i++){
            for(int j = 0; j < cave[i].length; j++){
                cave[i][j] = new GameLocation(i,j,getLocationId(i, j));
                System.out.print(getLocationId(i,j) + " ");
            }System.out.println();
        }
    }

    public GameLocation spawnPoint(){
        return cave[0][0];
    }

    public int getLocationId(int i, int j){
        return i * 6 + j + 1;

    }

    public int[] getSize(){
        return new int[] {this.cave.length, this.cave[0].length};
    }

    public GameLocation getLocationBasedOnCoords(int x, int y){
        return this.cave[x][y];
    }


}
