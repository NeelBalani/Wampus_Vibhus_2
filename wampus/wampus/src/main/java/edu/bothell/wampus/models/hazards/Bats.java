package edu.bothell.wampus.models.hazards;

import edu.bothell.wampus.controllers.GameController;
import edu.bothell.wampus.interfaces.Obstacle;
import edu.bothell.wampus.models.AdjacentGameLocation;
import java.util.Random;

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
    public void doAction(GameController controller) {
        Random r = new Random();
        int room = r.nextInt(1,31);
        controller.movePlayerUsingId(room);
    }

    @Override
    public AdjacentGameLocation getLocation(){
        return this.location;
    }

    @Override
    public String toString() { return "Bats"; }

    @Override
    public String getWarning() {
        return "You hear flapping.";
    }
}
