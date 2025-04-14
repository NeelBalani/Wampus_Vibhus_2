package edu.bothell.wampus;

import java.util.ArrayList;

public class WumpusTracker {
    private Room[] roomArray;
    private ArrayList<Integer> visitedRooms;

    public WumpusTracker(Room[] rooms, ArrayList<Integer> visits) {
        this.roomArray = rooms;
        this.visitedRooms = visits;
    }

    /** Returns the average danger level of all visited rooms */
    public double getAverageDanger() {
        double average = 0;
        for(int roomId : this.visitedRooms){
            average += getRoomById(roomId).getDangerLevel();
        }
        average /= this.visitedRooms.size();
        return average;
    }

    /** Returns the ID of the most dangerous room the player has visited */
    public int getMostDangerousVisitedRoom() {
        int room = 0;
        int dangerLevel = 0;
        for(int roomId : this.visitedRooms){
            if(getRoomById(roomId).getDangerLevel() > dangerLevel){
                room = roomId;
                dangerLevel = getRoomById(roomId).getDangerLevel();
            }
        }
        return room;
    }

    public void addVisitedRoom(int roomId) {
        this.visitedRooms.add(roomId);
    }

    public Room getRoomById(int roomId){
        for(Room room : this.roomArray){
            if(room.getID() == roomId) return room;
        }
        return null;
    }
}