package edu.bothell.wampus;

import java.util.Arrays;

public enum Directions {
    // NE(0, -1),      // North-East
    E(1),     // East
    // SE(0, 1),    // South-East
    // SW(-1, 1),      // South-West
    W(-1),       // West
    // NW(-1, -1)
    S(5),
    N(-5);        // North-West

    private final int shiftNumber;
    private boolean offset;

    Directions(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public Directions offset(boolean b){
        this.offset = b;
        return this;
    }

    public static Directions getDirectionFromString(String directionStr){
        if(directionStr.equals("Up")) return N;
        if(directionStr.equals("Down")) return S;
        if(directionStr.equals("Right")) return E;
        if(directionStr.equals("Left")) return W;
        return null;
    }

    public int getShiftNumber() {
        return this.shiftNumber;
    }

    // public void shift(){
    //     this.offset = true;
    // }

    // public int dX() {
        //if(name().equals("E") || name().equals("W") || !this.offset) return dx;
        //return this.dx + 1;
    //     return this.dx;
    // }

    // public int dY() {
    //     return this.dy;
    // }

    /**
     * Get the opposite direction.
     */
    public Directions opposite() {
        switch (this) {
            //case NE: return SW;
            case E:  return W;
            //case SE: return NW;
            //case SW: return NE;
            case W:  return E;
            //case NW: return SE;
            case N: return S;
            case S: return N;
            default: throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
