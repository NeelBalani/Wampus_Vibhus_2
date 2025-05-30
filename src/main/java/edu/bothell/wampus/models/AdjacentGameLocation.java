package edu.bothell.wampus.models;

import edu.bothell.wampus.interfaces.Obstacle;
import edu.bothell.wampus.models.people.Person;

import java.util.ArrayList;
import java.util.List;

public class AdjacentGameLocation {
    // Properties
    private int locationId;
    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<Integer> adjacentLocationsId = new ArrayList<Integer>();
    private Obstacle obstacle;
    private boolean hasObstacle = false;
    private int x;
    private int y;
    private boolean shifted = false;

    // Constructor
    public AdjacentGameLocation(int locationId){
        this.locationId = locationId;
    }

    public AdjacentGameLocation(int locationId, ArrayList<Integer>adjacentLocationsId){
        this.locationId = locationId;
        setAdjLocations(adjacentLocationsId);
        this.x = (locationId-1) % 5;
        this.y = (locationId-1) / 5;
        if(((this.locationId-1)/5) % 2 == 0) shifted = true;
    }

    //Method

    public ArrayList<Integer> getAdjGameLocationId(){
        return this.adjacentLocationsId;
    }

    public boolean isShifted(){
        return this.shifted;
    }

    public void setAdjLocations(ArrayList<Integer> adjLocs){
        this.adjacentLocationsId = adjLocs;
    }

    public ArrayList<Integer> getAdjLocations(){
        return this.adjacentLocationsId;
    }

    public boolean doesContainPerson(Person p){
        return this.people.contains(p);
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
