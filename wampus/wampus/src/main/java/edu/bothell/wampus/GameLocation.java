package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.List;

public class GameLocation {

    // Properties
    private int x;
    private int y;
    private int locationId;
    private List<Directions> whereWalls;
    private List<Object> items;

    // Constructor
    public GameLocation(int x, int y, int locationId){
        this.x = x;
        this.y = y;
        this.locationId = locationId;
        this.items = new ArrayList<>();
    }
    public GameLocation(int x, int y){
        this.x = x;
        this.y = y;
        this.items = new ArrayList<>();
    }
    


    //Method

    public int[] getLocation(){
        return new int[]{this.x, this.y};
    }

    public void addItemToLocation(Object o){
        this.items.add(o);
    }

    public List<Object> getItems(){
        return this.items;
    }

    public int getLocationId(){
        return this.locationId;
    }

    public void setWalls(List<Directions> walls){
        this.whereWalls = walls;
    }

    public boolean isThereAWall(Directions direction){
        return this.whereWalls.contains(direction);
    }

    public List<Directions> getWalls(){
        return this.whereWalls;
    }
}
