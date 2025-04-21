package edu.bothell.wampus;

public interface Obstacle {

    // The Controller checks on the obstacles
    // The Cave contains the obstacles
    boolean hasObstacleBeenTriggered();
    void triggerObstacle();
    void destroyObstacle();
    boolean isObstacleDestroyed();
    AdjacentGameLocation getLocation();
    String toString();
}
