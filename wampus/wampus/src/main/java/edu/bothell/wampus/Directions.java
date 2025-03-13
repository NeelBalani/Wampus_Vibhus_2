package edu.bothell.wampus;

import java.util.Arrays;

public enum Directions {
    // NE(0, -1),      // North-East
    E(1, 0),     // East
    // SE(0, 1),    // South-East
    // SW(-1, 1),      // South-West
    W(-1, 0),       // West
    // NW(-1, -1)
    S(0,1),
    N(0,-1);        // North-West

    private final int dx;
    private final int dy;
    private boolean offset;

    Directions(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Directions offset(boolean b){
        this.offset = b;
        return this;
    }


    public void shift(){
        this.offset = true;
    }

    public int dX() {
        //if(name().equals("E") || name().equals("W") || !this.offset) return dx;
        //return this.dx + 1;
        return this.dx;
    }

    public int dY() {
        return this.dy;
    }

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
