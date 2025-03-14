package edu.bothell.wampus;

public class Pit implements Obstacle {
    // Properties
    private GameLocation location;
    private boolean isTriggered = false;
    private boolean isDestroyed = false;

    // Constructors
    public Pit(GameLocation gl){
        this.location = gl;
    }

    // Method

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
    public GameLocation getLocation(){
        return this.location;
    }

    @Override
    public String toString() { return "Pit"; }
}
