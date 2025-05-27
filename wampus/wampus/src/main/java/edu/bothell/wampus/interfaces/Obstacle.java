package edu.bothell.wampus.interfaces;

import edu.bothell.wampus.Game;
import edu.bothell.wampus.controllers.GameController;
import edu.bothell.wampus.models.AdjacentGameLocation;

public interface Obstacle {

    // The Controller checks on the obstacles
    // The Cave contains the obstacles
    boolean hasObstacleBeenTriggered();
    void triggerObstacle();
    void destroyObstacle();
    boolean isObstacleDestroyed();
    void doAction(GameController controller);
    AdjacentGameLocation getLocation();
    String toString();
    String getWarning();
}
