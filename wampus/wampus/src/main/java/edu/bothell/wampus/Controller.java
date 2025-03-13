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
    private Game game;

    public Controller() {
        this.activeTeammates = new ArrayList<>();
        this.allTeammates = this.activeTeammates;
        this.cave = new Cave();
        this.locationManager = new LocationManager(this.allTeammates, this.cave);
        this.game = new Game(this.locationManager, this.activeTeammates);
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

    public void setNewPlayers() {
        this.activeTeammate = this.activeTeammates.getFirst();
        this.locationManager.setNewPlayers(this.activeTeammates);
    }

    public void thePlayerTriedToMove(Result result){
        Directions direction = this.activeTeammate.doMove(this.ui);

        GameLocation oldLocation = this.locationManager.getGameLocationOfPerson(this.activeTeammate);
        GameLocation newLocation = this.locationManager.getGameLocationInThisDirection(oldLocation, direction);
        this.locationManager.changeGameLocationOfPerson(this.activeTeammate, newLocation, oldLocation);


        boolean obstacleTrigger = newLocation.didPersonTriggerObstacle();
        System.out.println(obstacleTrigger);
        if(obstacleTrigger) obstacleAction(newLocation.getObstacle());

        result.playerMove(oldLocation, newLocation);
    }

    public void obstacleAction(Obstacle obstacle){
        /*
         * Check what obstacle it is
         * Do an action based on what the obstacle is
         */
        // if(obstacle is pit) say player is dead and remove from active players
        Pit pit = new Pit(new GameLocation(0, 0));
        if(obstacle.getClass() == pit.getClass()){
            System.out.println("THE OBSTACLE IS A PIT");
        };
    }

    public void start() {
        
        while (!gameOver()) {

            this.ui.showPersonTurn((this.activeTeammate));

            Result result = this.activeTeammate.doAction(this.ui);
            
            // Check if the player moved
            if(result.getAction().equals("Move")){
                Directions direction = this.activeTeammate.doMove(ui);
                this.game.movePlayer(this.activeTeammate, direction, result);
            }

            if(result.getAction().equals("Shoot")){
                // Todo: Action for shooting
            }

            addResult(result);
            this.ui.showMessage(result.getMessage());


        }
        this.ui.displaySummary();
    }

    public void postMoveActions(){
        this.continueGame = this.ui.askToContinue(this.activeTeammate.getName());
        if(!this.continueGame){
            removePlayer(this.activeTeammate);
        }
        if(!gameOver()) updateActivePlayer();
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
