package edu.bothell.wampus.controllers;

import edu.bothell.wampus.models.AdjacentGameLocation;
import edu.bothell.wampus.Directions;
import edu.bothell.wampus.models.HexagonalRoom;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameScreenController {

    @FXML private Button E, NE, NW, W, SE, SW;
    @FXML private Pane board;
    @FXML private Label statusLabel;
    @FXML private TextArea historyTextArea;
    @FXML private Button centerButton;

    private GameController gameController;
    private HexagonalRoom[][] rooms = new HexagonalRoom[6][5];
    private AdjacentGameLocation currentLocation;
    private Button[] dpad;
    private int moveCounter = 0;
    private ArrayList<Integer> visited = new ArrayList<Integer>();
    private int arrowsShot = 0;
    
    // Flag to track if we're in shooting mode
    private boolean shootingMode = false;

    public GameScreenController() {
    }

    @FXML
    public void initialize() {
        // TODO: Initialize Trivia button and Exit button

        this.dpad= new Button[]{this.E, this.NE, this.NW, this.W, this.SW, this.SE};
        
        // Initialize history text area
        historyTextArea.setText("Game started. Good luck!\n");
    }

    public void initGameController(GameController gameController) {
        if(gameController == null) {
            System.out.println("game controller not initialized");
            return;
        }
        this.gameController = gameController;
        initializeGameBoard();
        updateGameBoard();
        checkForWarnings(currentLocation);
    }

    private void initializeGameBoard() {
        board.getChildren().clear();
        if (this.gameController == null) {
            System.out.println("this game controller not initialized");
            return;
        }
        AdjacentGameLocation[] locations = this.gameController.getCave().getCave();
        double hexRadius = 40; // Distance from center to any corner
        double hexWidth = 2 * hexRadius; // Flat side to flat side (horizontal span)
        double hexHeight = Math.sqrt(3) * hexRadius; // Point to point (vertical span)
        double vertSpacing = hexHeight; // Vertical spacing between rows


        int locIdx = 1;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {

                if (locIdx > locations.length) break;

                // Shift odd columns downwards by half the height (for pointy-topped staggered columns)
                double x = col * hexWidth + ((row % 2 == 0) ? hexWidth / 2 : 0) + 40;
                double y = row * vertSpacing + 40;

                AdjacentGameLocation location = locations[locIdx-1];
                HexagonalRoom room = new HexagonalRoom(location, x, y, 40);
                board.getChildren().add(room);
                this.rooms[row][col] = room;
                locIdx++;
            }
        }
        System.out.println("Game board initialized");
    }

    // D-Pad button handlers

    @FXML
    private void handleMoveUpRight() {
        if (shootingMode) {
            arrowsShot++;
            shootArrow(Directions.NE);
        } else {
            movePlayer(Directions.NE);
        }
    }

    @FXML
    private void handleMoveRight() {
        if (shootingMode) {
            arrowsShot++;
            shootArrow(Directions.E);
        } else {
            movePlayer(Directions.E);
        }
    }

    @FXML
    private void handleMoveDownRight() {
        if (shootingMode) {
            arrowsShot++;
            shootArrow(Directions.SE);
        } else {
            movePlayer(Directions.SE);
        }
    }

    @FXML
    private void handleMoveDownLeft() {
        if (shootingMode) {
            arrowsShot++;
            shootArrow(Directions.SW);
        } else {
            movePlayer(Directions.SW);
        }
    }

    @FXML
    private void handleMoveLeft() {
        if (shootingMode) {
            arrowsShot++;
            shootArrow(Directions.W);
        } else {
            movePlayer(Directions.W);
        }
    }

    @FXML
    private void handleMoveUpLeft() {
        if (shootingMode) {
            arrowsShot++;
            shootArrow(Directions.NW);
        } else {
            movePlayer(Directions.NW);
        }
    }

    @FXML
    private void handleShootButton() {
        // Toggle shooting mode
        shootingMode = !shootingMode;
        
        if (shootingMode) {
            // Enter shooting mode
            centerButton.getStyleClass().remove("dpad-center-button");
            centerButton.getStyleClass().add("shoot-active");
            statusLabel.setText("Shooting Mode: Select a direction to shoot");
            
            // Make all available D-pad buttons red
            for (Button button : dpad) {
                if (!button.isDisabled()) {
                    button.getStyleClass().remove("dpad-button");
                    button.getStyleClass().add("shoot-mode-button");
                }
            }
        } else {
            // Exit shooting mode
            centerButton.getStyleClass().remove("shoot-active");
            centerButton.getStyleClass().add("dpad-center-button");
            statusLabel.setText("Ready to play!");
            
            // Restore original button styles
            for (Button button : dpad) {
                button.getStyleClass().remove("shoot-mode-button");
                button.getStyleClass().add("dpad-button");
            }
        }
    }
    
    private void shootArrow(Directions direction) {
        if (gameController != null) {
            // Get the adjacent location in the specified direction
            ArrayList<Integer> adjLocIds = currentLocation.getAdjLocations();
            int targetLocId = adjLocIds.get(direction.ordinal());
            
            if (targetLocId == 0) {
                // Can't shoot in this direction
                addToHistory("Cannot shoot in direction " + direction);
                return;
            }
            
            // Get the target location
            AdjacentGameLocation targetLocation = gameController.getGame().getLocationManager()
                    .getGameLocationBasedOnId(targetLocId);
            
            // Check if there's a Wumpus in the target location
            if (targetLocation.hasObstacle() && targetLocation.getObstacle().toString().equals("Wumpus")) {
                // Hit the Wumpus!
                targetLocation.getObstacle().destroyObstacle();
                addToHistory("You shot the Wumpus! You win!");
                statusLabel.setText("You win! The Wumpus is dead.");
                
                // Disable all buttons except menu
                for (Button button : dpad) {
                    button.setDisable(true);
                }
                centerButton.setDisable(true);
            } else {
                // Missed the Wumpus
                addToHistory("You shot an arrow into Room " + targetLocId + " but hit nothing.");
            }
            
            // Exit shooting mode
            shootingMode = false;
            centerButton.getStyleClass().remove("shoot-active");
            centerButton.getStyleClass().add("dpad-center-button");
            
            // Restore original button styles
            for (Button button : dpad) {
                button.getStyleClass().remove("shoot-mode-button");
                button.getStyleClass().add("dpad-button");
            }
        }
    }

    private void movePlayer(Directions direction) {
        if (gameController != null) {
            AdjacentGameLocation prevLocation = currentLocation;
            AdjacentGameLocation newLocation = gameController.movePlayerUsingDirections(direction);
            
            // Update history with movement information
            if (prevLocation != newLocation) {
                addToHistory("Player moved from Room " + prevLocation.getLocationId() +
                             " to Room " + newLocation.getLocationId() + " (" + direction + ")");
            } else {
                addToHistory("Cannot move " + direction + " from Room " + prevLocation.getLocationId());
            }

            // Update obstacle display
            if (newLocation.getObstacle() != null) {
                addToHistory("Player encountered a " + newLocation.getObstacle().toString());
            }

            // Update warning display
            checkForWarnings(newLocation);
            
            updateGameBoard();
        }
    }

    private void checkForWarnings(AdjacentGameLocation newLocation) {
        ArrayList<Integer> adjLocIds = newLocation.getAdjLocations();
        for(int adjLocId : adjLocIds) {
            if(adjLocId == 0) continue;
            AdjacentGameLocation adjLoc = gameController.getGame().getLocationManager().getGameLocationBasedOnId(adjLocId);
            if(adjLoc.hasObstacle()) {
                addToHistory(adjLoc.getObstacle().getWarning());
            }
        }
    }

    @FXML
    private void handleMenuButton() {
        try {
            // Load the start screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bothell/wampus/views/StartScreen.fxml"));
            Parent startScreen = loader.load();

            // Get the current stage
            Stage stage = (Stage) board.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(startScreen);
            scene.getStylesheets().add(getClass().getResource("/edu/bothell/wampus/css/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Setter for game controller
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    // Update the game board display
    public void updateGameBoard() {
        // Get the current game location
        this.currentLocation = this.gameController.getGame().getLocationManager()
                .getGameLocationOfPerson(this.gameController.getActiveTeammate());

        // Reset all rooms first
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                if (this.rooms[row][col] != null && !this.rooms[row][col].getLocation().didPersonTriggerObstacle()) {
                    this.rooms[row][col].updateVisualState("Normal");
                }
            }
        }

        // Highlight current location
        int locId = this.currentLocation.getLocationId();
        visited.add(locId);
        int row = (locId - 1) / 5;  // Assuming 5 columns
        int col = (locId - 1) % 5;
        if (this.rooms[row][col] != null) {
            this.rooms[row][col].updateVisualState("Player");
        }

        // Update adjacent rooms

        ArrayList<Integer> adjLocs = currentLocation.getAdjLocations();
        ArrayList<Integer> adjHazardLocs = new ArrayList<Integer>();
        for(int adj : adjLocs){
            AdjacentGameLocation loc = new AdjacentGameLocation(adj);
            if(loc.hasObstacle()){
                adjHazardLocs.add(loc.getLocationId());
            }
        }

        for (int adjLocId : adjLocs) {
            if(adjLocId == 0) continue;
            int adjRow = (adjLocId - 1) / 5;  // Assuming 5 columns
            int adjCol = (adjLocId - 1) % 5;
            if (this.rooms[adjRow][adjCol] != null && !this.rooms[row][col].getLocation().didPersonTriggerObstacle()) { // Check if the room exists
                this.rooms[adjRow][adjCol].updateVisualState("AdjacentToPlayer");
                for(int nums : visited){
                    int r = (nums - 1) / 5;  // Assuming 5 columns
                    int c = (nums - 1) % 5;
                    if(r == adjRow && c == adjCol){
                        this.rooms[r][c].updateVisualState("Visited");
                    }
                }
                // Don't think we need this
//                for(int number : adjHazardLocs){
//                    int hazR = (number - 1) / 5;  // Assuming 5 columns
//                    int hazC = (number - 1) % 5;
//                    if(hazR == adjRow && hazC == adjCol){
//                        this.rooms[hazR][hazC].updateVisualState("Obstacle");
//                    }
//                }
                System.out.println("Room " + adjLocId + " is adjacent to " + locId);
            }
        }

        // Update D-Pad buttons
        for (Directions direction : Directions.values()) {
            int order = direction.ordinal();
            this.dpad[order].setDisable(adjLocs.get(order) == 0);
        }
    }

    /**
     * Adds a new entry to the game history panel
     * @param text The text to add to the history
     */
    public void addToHistory(String text) {
        if (historyTextArea != null) {
            // Use move counter instead of timestamp
            String entry;
            if (text.startsWith("Player moved")) {
                moveCounter++;
                entry = "Move " + moveCounter + ": " + text + "\n";
            } else {
                entry = text + "\n";
            }
            historyTextArea.appendText(entry);
            
            // Auto-scroll to the bottom
            historyTextArea.positionCaret(historyTextArea.getText().length());
        }
    }

    public int getMoveCounter(){
        return moveCounter;
    }

    public int getArrowsShot(){
        return arrowsShot;
    }

}
