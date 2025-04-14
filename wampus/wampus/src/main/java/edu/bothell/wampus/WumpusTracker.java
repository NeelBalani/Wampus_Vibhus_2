package edu.bothell.wampus;

import java.util.ArrayList;

public class WumpusTracker {
    private Room[] roomArray;
    private ArrayList<Integer> visitedRooms;

    public WumpusTracker(Room[] rooms, ArrayList<Integer> visits) {
        roomArray = rooms;
        visitedRooms = visits;
    }

    /** Returns the average danger level of all visited rooms */
    public double getAverageDanger() {
        double average = 0;
        for(int n = 0; n < visitedRooms.size(); n++){
            average += visitedRooms.get(n);
        }
        average /= visitedRooms.size();
        return average;
    }

    /** Returns the ID of the most dangerous room the player has visited */
    public int getMostDangerousVisitedRoom() {
        int room = 0;
        int dangerLevel = 0;
        for(int n = 0; n < visitedRooms.size(); n++){
            if(dangerLevel < visitedRooms.get(n)){
                dangerLevel = n;
            }
        }
        return room;
    }
}