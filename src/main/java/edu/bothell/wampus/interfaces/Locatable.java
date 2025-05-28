package edu.bothell.wampus.interfaces;


import edu.bothell.wampus.models.AdjacentGameLocation;

public interface Locatable {
    int getX();  // Return the X-coordinate
    int getY();  // Return the Y-coordinate
    int[] getPos();  // Return both coordinates as an array
    AdjacentGameLocation getButtonLocation();  // Return the associated Location object
}