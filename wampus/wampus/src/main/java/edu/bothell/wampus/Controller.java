package edu.bothell.wampus;

import java.io.FileNotFoundException;
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

    public Controller(String caveFilePath) throws FileNotFoundException{
        this.activeTeammates = new ArrayList<>();
        this.allTeammates = this.activeTeammates;
        
        setCaveAs(caveFilePath);
        setNewLocationManager();
        
        this.game = new Game(this.locationManager, this.activeTeammates);
    }

    public Controller() {
        this.activeTeammates = new ArrayList<>();
        this.allTeammates = this.activeTeammates;
    }

    public void setCaveAs(String filePath) throws FileNotFoundException{
        WallCaveInitializer caveInitializer = new WallCaveInitializer(filePath);
        this.cave = caveInitializer.getBuiltCave();
    }

    public void setNewGame(){
        this.game = new Game(this.locationManager, this.activeTeammates);
    }

    public void setNewLocationManager(){
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

    public void passPlayersToLocationManager() {
        this.activeTeammate = this.activeTeammates.getFirst();
        this.locationManager.setNewPlayers(this.activeTeammates);
        
    }


    public void start() {
        
        while (!gameOver()) {

            this.ui.showPersonTurn((this.activeTeammate));

            Result result = this.activeTeammate.doAction(this.ui);
            
            // Check if the player moved
            if(result.getAction().equals("Move")){
                Directions direction = this.activeTeammate.doMove(ui);
                result = this.game.movePlayer(this.activeTeammate, direction, result);
            }

            else if(result.getAction().equals("Shoot")){
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
