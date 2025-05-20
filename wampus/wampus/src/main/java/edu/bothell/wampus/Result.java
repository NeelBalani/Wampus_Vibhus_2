package edu.bothell.wampus;

import java.util.Arrays;

public class Result {
    // Properties
    private String message;
    private String action;
    private AdjacentGameLocation location;
    private Person player;
    private boolean addedAction;

    // Constructor
    public Result(String action, Person player) {
        this.action = action;
        this.player = player;
        this.message = action;
    }

    // Methods

    public boolean getAddedAction(){
        return this.addedAction;
    }

    public void changeAddedAction(){
        this.addedAction = !this.addedAction;
    }

    public void addDirectionToMessage(Directions direction) {
        if(this.message.equals("Shoot")){
            this.message += " to the " + (direction.name());
        }
    }

    public void playerMove(AdjacentGameLocation oldLocation, AdjacentGameLocation newLocation) {

        if(this.message.equals("Move")){
            this.message = " Moved from " + oldLocation.getLocationId() + " to " + newLocation.getLocationId();
        }
    }

    public void diedToTrap(Obstacle obstacle){
        this.message += " and died to a " + obstacle.getClass().toString();
    }

    public String getAction() {
        return this.action;
    }

    public String getMessage() {
        return this.player.getName() + ":" + this.message;
    }


}
