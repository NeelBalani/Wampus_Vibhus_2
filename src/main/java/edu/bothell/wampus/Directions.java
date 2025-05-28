package edu.bothell.wampus;

import java.util.Arrays;

public enum Directions {
    E(1), // East
    NE(-5),      // North-East
    NW(-6),   // North-West
    W(-1),       // West
    SW(4),      // South-West
    SE(5);    // South-East

    private final int shiftNumber;
    private boolean offset;

    Directions(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public Directions offset(boolean b){
        this.offset = b;
        return this;
    }


    public int getShiftNumber() {
        if(this.offset && !(name().equals("E")||name().equals("W")||name().equals("N")||name().equals("S"))) return this.shiftNumber + 1;
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
            case W:  return E;
            case NW: return SE;
            case NE: return SW;
            case SW: return NE;
            case SE: return NW;
            default: throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
