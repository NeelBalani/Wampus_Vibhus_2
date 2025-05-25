package edu.bothell.wampus.controllers;

import edu.bothell.wampus.AdjacentGameLocation;
import edu.bothell.wampus.Directions;
import edu.bothell.wampus.GameController;
import edu.bothell.wampus.HexagonalRoom;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameScreenController {

    @FXML private Pane board;
    @FXML private Label statusLabel;

    private GameController gameController;
    private HexagonalRoom[][] rooms = new HexagonalRoom[6][5];
    private AdjacentGameLocation currentLocation;

    public GameScreenController() {
    }

    @FXML
    public void initialize() {
        // TODO: Initialize D-Pad buttons
        // TODO: Initialize Trivia button and Exit button
    }

    public void initGameController(GameController gameController) {
        if(gameController == null) {
            System.out.println("game controller not initialized");
            return;
        }
        this.gameController = gameController;
        initializeGameBoard();
        updateGameBoard();
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
    private void handleMoveUp() {
        movePlayer(Directions.N);
    }

    @FXML
    private void handleMoveUpRight() {
        movePlayer(Directions.NE);
    }

    @FXML
    private void handleMoveRight() {
        movePlayer(Directions.E);
    }

    @FXML
    private void handleMoveDownRight() {
        movePlayer(Directions.SE);
    }

    @FXML
    private void handleMoveDown() {
        movePlayer(Directions.S);
    }

    @FXML
    private void handleMoveDownLeft() {
        movePlayer(Directions.SW);
    }

    @FXML
    private void handleMoveLeft() {
        movePlayer(Directions.W);
    }

    @FXML
    private void handleMoveUpLeft() {
        movePlayer(Directions.NW);
    }

    private void movePlayer(Directions direction) {
        // TODO: Implement movement logic
        statusLabel.setText("Moving " + direction.toString());

        // This will be connected to the actual game controller
        if (this.gameController != null) {
            direction.offset(this.currentLocation.isShifted());
            this.gameController.movePlayerUsingDirections(direction);
             updateGameBoard();
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
        int row = (locId - 1) / 5;  // Assuming 5 columns
        int col = (locId - 1) % 5;
        if (this.rooms[row][col] != null) {
            this.rooms[row][col].updateVisualState("Player");
        }

        // Update adjacent rooms

        ArrayList<Integer> adjLocs = currentLocation.getAdjLocations();

        for (int adjLocId : adjLocs) {
            int adjRow = (adjLocId - 1) / 5;  // Assuming 5 columns
            int adjCol = (adjLocId - 1) % 5;
            if (this.rooms[adjRow][adjCol] != null && !this.rooms[row][col].getLocation().didPersonTriggerObstacle()) { // Check if the room exists
                this.rooms[adjRow][adjCol].updateVisualState("AdjacentToPlayer");
                System.out.println("Room " + adjLocId + " is adjacent to " + locId);
            }
        }
    }
}
