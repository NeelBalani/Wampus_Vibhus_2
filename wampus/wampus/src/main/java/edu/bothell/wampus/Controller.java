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
    private Cave cave;
    private Game game;

    public Controller(String caveFilePath) throws FileNotFoundException{

        initializePlayerLists();
        setCaveAs(caveFilePath);
        setNewGame();

    }

    private void initializePlayerLists() {
        this.allTeammates = new ArrayList<Person>();
        this.activeTeammates = this.allTeammates;
    }

    public void setCaveAs(String filePath) throws FileNotFoundException{
        WallCaveInitializer caveInitializer = new WallCaveInitializer(filePath);
        this.cave = caveInitializer.getBuiltCave();
    }

    public void setNewGame(){
        this.game = new Game(new LocationManager(this.allTeammates, this.cave), this.activeTeammates);
    }

    public void setPlayers(List<Person> players){
        this.allTeammates = players;
        this.activeTeammates = this.allTeammates;
        this.game.syncPlayers(players);
        this.activeTeammate = this.activeTeammates.getFirst();
    }

    public void setUI(UI ui){
        this.ui = ui;
    }

    public void addPerson(Person teammate) {
        this.allTeammates.add(teammate);
        setPlayers(this.allTeammates);
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
        List<Object> o = this.game.findObjectsInLocation(location);
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

    public void movePlayerUsingDirections(Directions direction) {
        Result result = this.game.movePlayerUsingDirections(direction, this.activeTeammate);
        addResult(result);
        this.ui.showMessage(result.getMessage());
    }
}
