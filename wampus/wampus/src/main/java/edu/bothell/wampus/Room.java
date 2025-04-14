package edu.bothell.wampus;

public class Room {
    private int id;
    private int dangerLevel;

    public Room(int id, int dangerLevel) {
        this.id = id;
        this.dangerLevel = dangerLevel;
    }

    public int getID() {
        return id;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }
}