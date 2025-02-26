package edu.bothell.wampus;

import java.util.Arrays;

public class Result {
    // Properties
    private String message;
    private String action;
    private GameLocation location;
    private Person player;

    // Constructor
    public Result(String action, Person player) {
        this.action = action;
        this.player = player;
        this.message = action;
    }

    // Methods

    public void addDirectionToMessage(Directions direction) {
        if(this.message.equals("Shoot")){
            this.message += " to the " + (direction.name());
        }
    }

    public void playerMove(GameLocation oldLocation, GameLocation newLocation) {

        if(this.message.equals("Move")){
            this.message = " Moved from " + oldLocation.getLocationId() + " to " + newLocation.getLocationId();
        }
    }

    public String getAction() {
        return this.action;
    }

    public String getMessage() {
        return this.player.getName() + ":" + this.message;
    }


}
