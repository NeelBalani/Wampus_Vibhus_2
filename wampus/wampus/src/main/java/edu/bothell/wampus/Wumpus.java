package edu.bothell.wampus;

public class Wumpus implements Obstacle {
    private boolean triggered;
    private AdjacentGameLocation location;

    public Wumpus(AdjacentGameLocation location) {
        this.location = location;
        this.triggered = false;
    }

    @Override
    public boolean hasObstacleBeenTriggered() {
        return triggered;
    }

    @Override
    public void triggerObstacle() {
        triggered = true;
        System.out.println("The Wumpus has been triggered!");
    }

    @Override
    public void destroyObstacle() {

        System.out.println("The Wumpus has been destroyed!");
    }

    @Override
    public boolean isObstacleDestroyed() {

        return triggered;
    }

    @Override
    public AdjacentGameLocation getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Wumpus";
    }
}
