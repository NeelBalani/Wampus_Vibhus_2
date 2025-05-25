package edu.bothell.wampus.models.hazards;

import edu.bothell.wampus.interfaces.Obstacle;
import edu.bothell.wampus.models.AdjacentGameLocation;

public class Wumpus implements Obstacle {
    // Properties
    private AdjacentGameLocation location;
    private boolean isTriggered = false;
    private boolean isDestroyed = false;

    // Constructors
    public Wumpus(AdjacentGameLocation gl){
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
    public AdjacentGameLocation getLocation(){
        return this.location;
    }

    @Override
    public String toString() { return "Wumpus"; }

    @Override
    public String getWarning() {
        return "You smell something terrible.";
    }
}

