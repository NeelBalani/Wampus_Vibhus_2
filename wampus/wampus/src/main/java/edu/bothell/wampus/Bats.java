package edu.bothell.wampus;

public class Bats implements Obstacle {
    // Properties
    private AdjacentGameLocation location;
    private boolean isTriggered = false;
    private boolean isDestroyed = false;

    // Constructor
    public Bats(AdjacentGameLocation location){
        this.location = location;
    }

    // Methods

    @Override
    public boolean hasObstacleBeenTriggered(){
        return this.isTriggered;
    }

    @Override
    public void triggerObstacle(){
        this.isTriggered = true;
    }

    @Override
    public void destroyObstacle(){
        this.isDestroyed = true;
    }

    @Override
    public boolean isObstacleDestroyed(){
        return this.isDestroyed;
    }

    @Override
    public AdjacentGameLocation getLocation(){
        return this.location;
    }

    @Override
    public String toString() { return "Bats"; }
}
