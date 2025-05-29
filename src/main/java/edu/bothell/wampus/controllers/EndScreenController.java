package edu.bothell.wampus.controllers;

import edu.bothell.wampus.HighScore;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Controller for the end screen that displays game results and score.
 */
public class EndScreenController {

    @FXML
    private Label titleLabel;
    
    @FXML
    private Label resultLabel;
    
    @FXML
    private Label scoreLabel;
    
    @FXML
    private Label statsLabel;
    
    @FXML
    private Button restartButton;
    
    @FXML
    private Button exitButton;
    
    private Stage stage;
    private boolean playerWon;
    private HighScore highScore;
    private int turns;
    private int gold;
    private int arrows;
    
    /**
     * Initialize the end screen with game results.
     *
     * @param stage The current stage
     * @param playerWon Whether the player won the game
     * @param turns Total number of turns taken
     * @param gold Total gold collected
     * @param arrows Remaining arrows
     */
    public void initialize(Stage stage, boolean playerWon, int turns, int gold, int arrows) {
        this.stage = stage;
        this.playerWon = playerWon;
        this.turns = turns;
        this.gold = gold;
        this.arrows = arrows;
        
        // Create high score
        highScore = new HighScore(playerWon, turns, gold, arrows);
        
        // Update UI elements
        updateUI();
    }
    
    /**
     * Update the UI with game results and statistics.
     */
    private void updateUI() {
        // Format score to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedScore = df.format(highScore.getScore());
        
        if (playerWon) {
            titleLabel.setText("Victory!");
            resultLabel.setText("You defeated the Wumpus!");
        } else {
            titleLabel.setText("Game Over");
            resultLabel.setText("The Wumpus got you!");
        }
        
        scoreLabel.setText("Score: " + formattedScore);
        
        // Display game statistics
        StringBuilder stats = new StringBuilder();
        stats.append("Turns: ").append(turns).append("\n");
        stats.append("Gold collected: ").append(gold).append("\n");
        stats.append("Arrows remaining: ").append(arrows);
        
        statsLabel.setText(stats.toString());
    }
    
    /**
     * Handle the restart button click, navigate back to level picker.
     */
    @FXML
    private void handleRestartButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bothell/wampus/views/LevelPicker.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Handle the exit button click, close the application.
     */
    @FXML
    private void handleExitButton() {
        Platform.exit();
    }
}
