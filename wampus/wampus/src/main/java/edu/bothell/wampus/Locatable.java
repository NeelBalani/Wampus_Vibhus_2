package edu.bothell.wampus;


public interface Locatable {
    int getX();  // Return the X-coordinate
    int getY();  // Return the Y-coordinate
    int[] getPos();  // Return both coordinates as an array
    GameLocation getButtonLocation();  // Return the associated Location object
}