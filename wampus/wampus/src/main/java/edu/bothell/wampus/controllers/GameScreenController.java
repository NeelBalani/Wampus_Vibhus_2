package edu.bothell.wampus.controllers;

import edu.bothell.wampus.AdjacentCave;
import edu.bothell.wampus.AdjacentGameLocation;
import edu.bothell.wampus.Directions;
import edu.bothell.wampus.GameController;
import edu.bothell.wampus.HexagonalRoom;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameScreenController {

    @FXML private Pane gameBoard;
    @FXML private Label statusLabel;

    private GameController gameController;
    private HexagonalRoom[][] rooms = new HexagonalRoom[5][6];

    @FXML
    public void initialize() {
        // TODO: Initialize D-Pad buttons
        // TODO: Initialize Trivia button and Exit button
    }

    public void initGameController(GameController gameController) {
        if(gameController != null) return;
        this.gameController = gameController;
        initializeGameBoard();
        updateGameBoard();
    }

    private void initializeGameBoard() {
        gameBoard.getChildren().clear();
        if (gameController == null) return;
        AdjacentGameLocation[] locations = gameController.getCave().getCave();
        double hexHeight = Math.sqrt(3) * 40;
        double hexWidth = 2 * 40;
        double vertSpacing = hexHeight * 0.75;

        int locIdx = 1;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 6; col++) {
                if (locIdx >= locations.length) break;
                double x = col * hexWidth * 0.75 + (row % 2) * (hexWidth * 0.375) + 40;
                double y = row * vertSpacing + 40;
                AdjacentGameLocation location = locations[locIdx];
                HexagonalRoom room = new HexagonalRoom(location, x, y, 40);
                gameBoard.getChildren().add(room);
                rooms[row][col] = room;
                locIdx++;
            }
        }
    }

    // D-Pad button handlers
    @FXML
    private void handleMoveUp() {
        movePlayer(Directions.N);
    }

//    @FXML
//    private void handleMoveUpRight() {
//        movePlayer(Directions.NE);
//    }

    @FXML
    private void handleMoveRight() {
        movePlayer(Directions.E);
    }

//    @FXML
//    private void handleMoveDownRight() {
//        movePlayer(Directions.SE);
//    }

    @FXML
    private void handleMoveDown() {
        movePlayer(Directions.S);
    }

//    @FXML
//    private void handleMoveDownLeft() {
//        movePlayer(Directions.SW);
//    }

    @FXML
    private void handleMoveLeft() {
        movePlayer(Directions.W);
    }

//    @FXML
//    private void handleMoveUpLeft() {
//        movePlayer(Directions.NW);
//    }

    private void movePlayer(Directions direction) {
        // TODO: Implement movement logic
        statusLabel.setText("Moving " + direction.toString());

        // This will be connected to the actual game controller
        if (gameController != null) {
            gameController.movePlayerUsingDirections(direction);
            // updateGameBoard();
        }
    }

    @FXML
    private void handleMenuButton() {
        try {
            // Load the start screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bothell/wampus/views/StartScreen.fxml"));
            Parent startScreen = loader.load();

            // Get the current stage
            Stage stage = (Stage) gameBoard.getScene().getWindow();

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
        AdjacentGameLocation currentLocation = gameController.getGame().getLocationManager()
                .getGameLocationOfPerson(gameController.getActiveTeammate());

        // Reset all rooms first
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 6; col++) {
                if (rooms[row][col] != null && !rooms[row][col].getLocation().didPersonTriggerObstacle()) {
                    rooms[row][col].updateVisualState("Normal");
                }
            }
        }

        // Highlight current location
        int locId = currentLocation.getLocationId();
        int row = (locId - 1) / 6;  // Assuming 6 columns
        int col = (locId - 1) % 6;
        if (rooms[row][col] != null) {
            rooms[row][col].updateVisualState("Player");
        }

        // Update adjacent rooms

        ArrayList<Integer> adjLocs = currentLocation.getAdjLocations();

        for (int adjLocId : adjLocs) {
            int adjRow = (adjLocId - 1) / 5;  // Assuming 5 columns
            int adjCol = (adjLocId - 1) % 5;
            if (rooms[adjRow][adjCol] != null && !rooms[row][col].getLocation().didPersonTriggerObstacle()) { // Check if the room exists
                rooms[adjRow][adjCol].updateVisualState("Adjacent");
            }
        }
    }
}
