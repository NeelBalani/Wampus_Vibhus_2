package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.List;

public class GameLocation {

    // Properties
    private int x;
    private int y;
    private int locationId;
    private List<Directions> whereWalls;
    private ArrayList<Person> people = new ArrayList<Person>();
    private Obstacle obstacle;
    private boolean hasObstacle;

    // Constructor
    public GameLocation(int x, int y, int locationId){
        this.x = x;
        this.y = y;
        this.locationId = locationId;
        
    }
    public GameLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    


    //Method

    public int[] getLocation(){
        return new int[]{this.x, this.y};
    }

    public boolean doesContainPerson(Person p){
        if(this.people.contains(p)) return true;
        return false;
    }

    public void addPersonToLocation(Person o){
        this.people.add(o);
    }

    public void removePlayerFromLocation(Person p){
        this.people.remove(p);
    }

    public List<Object> getItems(){
        List<Object> os = new ArrayList<>();
        os.addAll(this.people);
        if (this.obstacle != null) os.add(this.obstacle);
        return os;
    }

    public int getLocationId(){
        return this.locationId;
    }

    public void setWalls(List<Directions> walls){
        this.whereWalls = walls;
    }

    public boolean isThereAPlayer(){
        return !this.people.isEmpty();
    }

    public boolean isThereAWall(Directions direction){
        return this.whereWalls.contains(direction);
    }

    public Directions[] getWalls(){
        Directions[] directions = new Directions[4];
        for(int d = 0; d < this.whereWalls.size(); d++){
            directions[d] = this.whereWalls.get(d);
        }
        return directions;
    }

    public boolean hasObstacle(){
        return this.hasObstacle;
    }

    public void addObstacle(Obstacle obstacle){
        this.hasObstacle = true;
        this.obstacle = obstacle;
    }

    public Obstacle getObstacle(){
        return this.obstacle;
    }

    // Checks if the person and obstacle are in the same location
    public boolean didPersonTriggerObstacle(){
        if(!this.hasObstacle) return false;
        if(isThereAPlayer() && !this.obstacle.hasObstacleBeenTriggered()){
            this.obstacle.triggerObstacle();
            return true;
        } return false;
    }
}
