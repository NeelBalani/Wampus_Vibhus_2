package edu.bothell.wampus;

public class Bats implements Obstacle {
    // Properties
    private GameLocation location;
    private boolean isTriggered = false;

    // Constructor
    public Bats(GameLocation location){
        this.location = location;
    }

    // Methods
    @Override
    public void obstacleTriggered(){
        if(!isTriggered){
            
        }
    }

    @Override
    public GameLocation getLocation(){
        return this.location;
    }

}
