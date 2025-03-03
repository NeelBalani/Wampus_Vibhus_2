package edu.bothell.wampus;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private UI ui;
    private List<Person> activeTeammates = new ArrayList<>();
    private List<Person> allTeammates = new ArrayList<>();
    private Person activeTeammate;
    private List<Result> summary = new ArrayList<>();
    private boolean continueGame;
    private LocationManager locationManager;
    private Cave cave;

    public Controller() {
        this.activeTeammates = new ArrayList<>();
        this.allTeammates = this.activeTeammates;
        this.cave = new Cave();
        this.locationManager = new LocationManager(this.allTeammates, this.cave);
    }

    public void addPlayersToLocationManager(){
        this.locationManager.setNewPlayers(this.activeTeammates);
    }

    public void setUI(UI ui){
        this.ui = ui;
    }

    public void addPerson(Person teammate) {
        activeTeammates.add(teammate);
    }

    public void start() {
        this.activeTeammate = this.activeTeammates.getFirst();
        this.locationManager.setNewPlayers(this.activeTeammates);
        while (!gameOver()) {

            this.ui.showPersonTurn((this.activeTeammate));
            Result result = this.activeTeammate.doAction(this.ui);
            // Check if the player moved
            if(result.getAction().equals("Move")){
                Directions direction = this.activeTeammate.doMove(this.ui);

                GameLocation oldLocation = this.locationManager.getGameLocationOfPerson(this.activeTeammate);
                GameLocation newLocation = this.locationManager.getGameLocationInThisDirection(oldLocation, direction);
                this.locationManager.changeGameLocationOfPerson(this.activeTeammate, newLocation);

                result.playerMove(oldLocation, newLocation);
            }

            addResult(result);
            this.ui.showMessage(result.getMessage());

            this.continueGame = this.ui.askToContinue(this.activeTeammate.getName());
            if(!this.continueGame){
                removePlayer(this.activeTeammate);
            }
            if(!gameOver()) updateActivePlayer();
        }
        this.ui.displaySummary();
    }

    public List<Object> findWhatIsInLocation(GameLocation location){
        List<Object> o = this.locationManager.getPersonsInLocation(location);
        System.out.println(o + " " + location.getLocationId());
        return o;

    }

    public void addResult(Result result){
        this.summary.add(result);
    }

    public boolean gameOver(){
        return this.activeTeammates.isEmpty();
    }

    public void removePlayer(Person teammate){
        this.activeTeammates.remove(teammate);
    }

    public void updateActivePlayer(){
        int index = this.activeTeammates.indexOf(this.activeTeammate);
        index++;
        if(index >= this.activeTeammates.size()-1){
            index = 0;
        }
        this.activeTeammate = this.activeTeammates.get(index);
    }
    
    public List<Result> getSummary(){
        return this.summary;
    }

    public Cave getCave(){
        return this.cave;
    }
}
