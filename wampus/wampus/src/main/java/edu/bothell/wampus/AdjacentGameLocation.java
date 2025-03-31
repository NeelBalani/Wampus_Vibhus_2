package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.List;

public class AdjacentGameLocation {
    // Properties
    private int[] adjacentLocationsId = new int[6]; 
    private int x;
    private int y;
    private int locationId;
    private ArrayList<Person> people = new ArrayList<Person>();
    private Obstacle obstacle;
    private boolean hasObstacle;

    // Constructor
    public AdjacentGameLocation(int x, int y, int locationId){
        this.x = x;
        this.y = y;
        this.locationId = locationId;
        
    }
    public AdjacentGameLocation(int x, int y){
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

    public boolean isThereAPlayer(){
        return !this.people.isEmpty();
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
