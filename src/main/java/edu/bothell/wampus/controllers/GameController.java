package edu.bothell.wampus.controllers;

import edu.bothell.wampus.*;
import edu.bothell.wampus.initializers.AdjacentCaveInitializer;
import edu.bothell.wampus.interfaces.UI;
import edu.bothell.wampus.models.AdjacentGameLocation;
import edu.bothell.wampus.models.Result;
import edu.bothell.wampus.models.people.Person;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private List<Person> activeTeammates = new ArrayList<>();
    private List<Person> allTeammates = new ArrayList<>();
    private Person activeTeammate;
    private List<Result> summary = new ArrayList<>();
    private boolean continueGame;
    private AdjacentCave cave;
    private Game game;

    public GameController(String caveFilePath) throws FileNotFoundException{

        initializePlayerLists();
        setCaveAs(caveFilePath);
        setNewGame();

    }

    public GameController(InputStream caveStream) throws FileNotFoundException{
        initializePlayerLists();
        setCaveAs(caveStream);
        setNewGame();
    }

    private void initializePlayerLists() {
        this.allTeammates = new ArrayList<Person>();
        this.activeTeammates = this.allTeammates;
    }

    public void setCaveAs(String filePath) throws FileNotFoundException{
        AdjacentCaveInitializer caveInitializer = new AdjacentCaveInitializer(filePath);
        this.cave = caveInitializer.getBuiltCave();
    }
    public void setCaveAs(InputStream filePath) throws FileNotFoundException{
        AdjacentCaveInitializer caveInitializer = new AdjacentCaveInitializer(filePath);
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


    public void addPerson(Person teammate) {
        this.allTeammates.add(teammate);
        setPlayers(this.allTeammates);
    }


//    public void gameTurnTerminal(){
//
//        if(!this.game.getVisibleInfo(activeTeammate).equals("You see: ")){
////            this.ui.showMessage(this.game.getVisibleInfo(activeTeammate));
//        }
//        else{
////            this.ui.showMessage("You see nothing.");
//        }
//
////        Result result = this.activeTeammate.doAction(this.ui);
//
//        // Check if the player moved
//        if(result.getAction().equals("Move")){
//            int id = this.activeTeammate.doMove(ui, this.game.getLocationManager().getGameLocationOfPerson(activeTeammate).getAdjGameLocationId());
//            result = this.game.movePlayer(this.activeTeammate, id, result);
//        }
//
//        else if(result.getAction().equals("Shoot")){
//            // Todo: Action for shooting
//        }
//
//        else if(result.getAction().equals("Heal")){
//
//        }
//
//        addResult(result);
//        this.ui.showMessage(result.getMessage());
//    }


    public void postMoveActions(){
//        this.continueGame = this.ui.askToContinue(this.activeTeammate.getName());
        if(!this.continueGame){
            removePlayer(this.activeTeammate);
        }
        if(!gameOver()) updateActivePlayer();
    }

    public List<Object> findWhatIsInLocation(AdjacentGameLocation location){
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

    public AdjacentCave getCave(){
        return this.cave;
    }

    public void movePlayerUsingId(int roomId) {
        Result result = this.game.movePlayer(this.activeTeammate, roomId, new Result("Move", this.activeTeammate));
        if(result.getAddedAction()){ 
            System.out.println(roomId);
            addResult(result);
        }
        //this.ui.showMessage(result.getMessage());
    }

    public Person getActiveTeammate() {
        return this.activeTeammate;
    }

    public AdjacentGameLocation movePlayerUsingDirections(Directions directions) {

        AdjacentGameLocation newGameLocation = this.game.getLocationManager().movePlayer(this.activeTeammate, directions);

        movePlayerUsingId(newGameLocation.getLocationId());

        if(newGameLocation.getObstacle() != null) {
//            this.ui.showMessage("Player encountered a " + newGameLocation.getObstacle().toString());

        }

        return newGameLocation;
    }

    public Game getGame() {
        return this.game;
    }

    public boolean shootArrow(AdjacentGameLocation targetLocation) {
        if(this.activeTeammate == null) return false;
        if(!this.activeTeammate.hasAmmo()) {
            return false;
        }
        this.activeTeammate.shoot();

        if(targetLocation.hasObstacle() && targetLocation.getObstacle().toString().equals("Wumpus")) {
            targetLocation.getObstacle().destroyObstacle();
            return true;
        }
        return false;
    }

    public void awardGold(int amount) {
        if(this.activeTeammate != null) {
            this.activeTeammate.addGold(amount);
        }
    }

    public ArrayList<String> getWarnings(AdjacentGameLocation newLocation) {
        ArrayList<Integer> adjLocIds = newLocation.getAdjLocations();
        ArrayList<String> warnings = new ArrayList<String>();

        for(int adjLocId : adjLocIds) {
            if(adjLocId == 0) continue;
            AdjacentGameLocation adjLoc = this.game.getLocationManager().getGameLocationBasedOnId(adjLocId);
            if(adjLoc.hasObstacle()) {
                warnings.add(adjLoc.getObstacle().getWarning());
            }
        }
        return warnings;
    }

    public int getAmmo(){
        return this.activeTeammate.getAmmo();
    }

    public boolean isAmmoEmpty(){
        if(this.activeTeammate.getAmmo() == 0) {
            this.activeTeammates.remove(this.activeTeammate); // remove player
            return true;
        } else {
            return false;
        }
    }

    public boolean hasAmmo(){
        return this.activeTeammate.hasAmmo();
    }

    /**
     * Checks if the active player is in the same room as the Wumpus.
     * @return true if player is in the same room as the Wumpus, false otherwise
     */
    public boolean isWumpusInRoom() {
        if (this.activeTeammate == null) return false;
        
        // Get the current location of the player
        AdjacentGameLocation currentLocation = this.game.getLocationManager().getGameLocationOfPerson(this.activeTeammate);
        
        // Check if the location has the Wumpus obstacle
        return currentLocation.hasObstacle() && 
               currentLocation.getObstacle().toString().equals("Wumpus");
    }
    
    /**
     * Gets the current room the player is in.
     * @return The current HexagonalRoom
     */
}
