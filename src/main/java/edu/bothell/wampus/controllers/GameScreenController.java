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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameScreenController {

    @FXML private Button E, NE, NW, W, SE, SW;
    @FXML private Pane board;
    @FXML private Label statusLabel;
    @FXML private TextArea historyTextArea;
    @FXML private Button centerButton;
    @FXML private Label arrowCountLabel;
    @FXML private Label goldCountLabel;
    @FXML private Button triviaButton;
    @FXML private StackPane rootStack; // StackPane reference
    @FXML private Button giveUpButton;

    private GameController gameController;
    private HexagonalRoom[][] rooms = new HexagonalRoom[6][5];
    private AdjacentGameLocation currentLocation;
    private Button[] dpad;
    private int moveCounter = 0;
    private ArrayList<Integer> visited = new ArrayList<Integer>();
    private int arrowsShot = 0;
    private int lastTriviaMove = -10; // track last move used for trivia
    
    // Flag to track if we're in shooting mode
    private boolean shootingMode = false;

    // Radar tracking
    private boolean radarActive = false;
    private int radarMovesRemaining = 0;
    private static final int RADAR_DURATION_MOVES = 3;

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
        updateTrackerLabels();
        updateTriviaButton();
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
        updateRadarStatus();
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

            checkArrows();
            
            // Get the target location
            AdjacentGameLocation targetLocation = gameController.getGame().getLocationManager()
                    .getGameLocationBasedOnId(targetLocId);

            // Shoot the arrow and check if it hit the Wumpus
            if(gameController.shootArrow(targetLocation)) {
                // Player hit the Wumpus - victory!
                endGame(true, "You shot the Wumpus! Victory!");
                return;
            } else {
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
            updateTrackerLabels();
        }
    }

    private void movePlayer(Directions direction) {
        if (this.gameController != null) {
            AdjacentGameLocation prevLocation = this.currentLocation;
            AdjacentGameLocation newLocation = this.gameController.movePlayerUsingDirections(direction);
            
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
            moveCounter++;
            updateTrackerLabels();
            updateTriviaButton();
            updateRadarStatus();
            checkWumpusEncounter();
            checkArrows();
        }
    }

    private void checkForWarnings(AdjacentGameLocation newLocation) {

        ArrayList<String> warnings = this.gameController.getWarnings(newLocation);

        for (String warning : warnings) {
            addToHistory(warning);
        }
    }


    // Overload updateGameBoard
    public void updateGameBoard() {
        updateGameBoard("Normal");
    }

    // Update the game board display
    public void updateGameBoard(String type) {

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
        this.visited.add(locId);
        int row = (locId - 1) / 5;  // Assuming 5 columns
        int col = (locId - 1) % 5;
        if (this.rooms[row][col] != null) {
            this.rooms[row][col].updateVisualState("Player");
        }


        // Update adjacent rooms
        ArrayList<Integer> adjLocs = new ArrayList<>(currentLocation.getAdjLocations());

        if(type.equals("Normal")) {
             adjLocs= currentLocation.getAdjLocations();
        }
        else if(type.equals("Radar")) {
            int wall = 0;
            for(int i = 0; i < adjLocs.size(); i++) {
                System.out.println(adjLocs.get(i));
                if(adjLocs.get(i) != 0 && this.gameController.getCave().getLocationBasedOnId(adjLocs.get(i)).hasObstacle()) {
                    adjLocs.set(i, wall);
                }
            }
        }


        // Loop through all adjacent locations
        for (int adjLocId : adjLocs) {
            if (adjLocId == 0) continue; // Skip invalid location IDs

            // Calculate the row and column indices of the adjacent location
            int adjRow = (adjLocId - 1) / 5;  // Assuming 5 columns
            int adjCol = (adjLocId - 1) % 5;

            // If the room exists and the player has not triggered the obstacle, highlight it
            if (this.rooms[adjRow][adjCol] != null && !this.rooms[row][col].getLocation().didPersonTriggerObstacle()) {
                this.rooms[adjRow][adjCol].updateVisualState("AdjacentToPlayer");

                // If the room has been visited, set its visual state to "Visited"
                for (int nums : visited) {
                    int r = (nums - 1) / 5;  // Assuming 5 columns
                    int c = (nums - 1) % 5;
                    if (r == adjRow && c == adjCol) {
                        this.rooms[r][c].updateVisualState("Visited");
                    }
                }

                // Print a debug message
                System.out.println("Room " + adjLocId + " is adjacent to " + locId);
            }
        }

        // Update D-Pad buttons
        for (Directions direction : Directions.values()) {
            int order = direction.ordinal();
            this.dpad[order].setDisable(adjLocs.get(order) == 0);
        }
    }

    private void updateTrackerLabels() {
        if(gameController == null) return;
        if(gameController.getActiveTeammate() == null) return;
        arrowCountLabel.setText(String.valueOf(gameController.getActiveTeammate().getAmmo()));
        goldCountLabel.setText(String.valueOf(gameController.getActiveTeammate().getGold()));
    }

    @FXML
    private void handleTriviaButton() {
        // limit access
        lastTriviaMove = moveCounter;
        updateTriviaButton();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bothell/wampus/views/TriviaOverlay.fxml"));
            Parent overlay = loader.load();
            TriviaOverlayController controller = loader.getController();
            controller.init(gameController, this, overlay);
            // Add overlay to root stack
            rootStack.getChildren().add(overlay);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeTriviaOverlay(Parent overlay) {
        rootStack.getChildren().remove(overlay);
        updateTrackerLabels(); // refresh gold
    }

    @FXML
    private void handleShopButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bothell/wampus/views/ShopOverlay.fxml"));
            Parent overlay = loader.load();
            ShopOverlayController controller = loader.getController();
            controller.init(gameController, this, overlay);
            // Add overlay to root stack
            rootStack.getChildren().add(overlay);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void closeShop(Parent overlay) {
        rootStack.getChildren().remove(overlay);
        updateTrackerLabels();
    }
    
    // Method for radar item
    public void revealHazards() {
        // Activate radar for 3 moves
        if(!radarActive) {
            radarActive = true;
            radarMovesRemaining = RADAR_DURATION_MOVES;
        }
        
        updateGameBoard("Radar");
    }
    
    /**
     * Checks if radar is active and decrements the remaining moves if it is.
     * Should be called after each player move.
     */
    private void updateRadarStatus() {
        if (radarActive) {
            radarMovesRemaining--;
            
            if (radarMovesRemaining <= 0) {
                // Radar has expired
                radarActive = false;
                statusLabel.setText("Radar has deactivated. Hazards are hidden again.");
                addToHistory("Radar has deactivated after " + RADAR_DURATION_MOVES + " moves.");
                System.out.println("Radar deactivated");
            } else {
                // Update status with remaining moves
                statusLabel.setText("Radar activated! Nearby hazards will be revealed for " + RADAR_DURATION_MOVES + " moves.");
                // Add to game history
                addToHistory("Radar activated! Scanning for nearby hazards for " + RADAR_DURATION_MOVES + " moves.");

                revealHazards();

            }
        }
    }

    /**
     * Checks if the radar is currently active.
     * 
     * @return true if radar is active, false otherwise
     */
    public boolean isRadarActive() {
        return radarActive;
    }
    
    /**
     * Gets the number of moves remaining for the radar.
     * 
     * @return number of moves remaining before radar deactivates
     */
    public int getRadarMovesRemaining() {
        return radarMovesRemaining;
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
                entry = "Move " + moveCounter + ": " + text + "\n";
            } else {
                entry = text + "\n";
            }
            historyTextArea.appendText(entry);
            
            // Auto-scroll to the bottom
            historyTextArea.positionCaret(historyTextArea.getText().length());
        }
    }

    /** Enable trivia button only if 10 moves passed since last trivia */
    private void updateTriviaButton() {
        if (triviaButton == null) return;
        boolean allowed = (moveCounter - lastTriviaMove) >= 10;
        triviaButton.setDisable(!allowed);
    }

    /**
     * Handle the Give Up button click, end the game as a loss.
     */
    @FXML
    private void handleGiveUpButton() {
        endGame(false, "You gave up!");
    }
    
    /**
     * End the game and navigate to the end screen.
     * 
     * @param playerWon Whether the player won the game
     * @param message The message to display in the status label
     */
    private void endGame(boolean playerWon, String message) {
        // Update status message
        statusLabel.setText(message);
        
        // Add to game history
        addToHistory(message);
        
        // Reset radar if active
        radarActive = false;
        radarMovesRemaining = 0;
        
        try {
            // Get game statistics
            int turns = moveCounter;
            int gold = gameController.getActiveTeammate().getGold();
            int arrows = gameController.getActiveTeammate().getAmmo();
            
            // Load the end screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bothell/wampus/views/EndScreen.fxml"));
            Parent root = loader.load();
            
            // Get the controller and initialize it
            EndScreenController controller = loader.getController();
            Stage stage = (Stage) rootStack.getScene().getWindow();
            controller.initialize(stage, playerWon, turns, gold, arrows);
            
            // Set the scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Check if player has run into the Wumpus
    private void checkWumpusEncounter() {
        if (gameController.isWumpusInRoom()) {
            endGame(false, "Oh no! You walked into the Wumpus's room and were eaten!");
        }
    }
    
    // Check if player has run out of arrows
    private void checkArrows() {
        if (!gameController.getActiveTeammate().hasAmmo()) {
            endGame(false, "You've run out of arrows! Game over!");
        }
    }
}
