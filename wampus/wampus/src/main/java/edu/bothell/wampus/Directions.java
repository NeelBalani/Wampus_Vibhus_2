package edu.bothell.wampus;

import java.util.Arrays;

public enum Directions {
     NE(-5),      // North-East
    E(1),     // East
     SE(5),    // South-East
     SW(4),      // South-West
    W(-1),       // West
     NW(-6),
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
