package edu.bothell.wampus.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenController {


    public StartScreenController() {
    }


    @FXML
    private void handleStartButton(ActionEvent event) {
        try {
            // Load the game screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bothell/wampus/views/GameScreen.fxml"));
            Parent gameScreen = loader.load();
            
            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            // Set the new scene
            Scene scene = new Scene(gameScreen);
            scene.getStylesheets().add(getClass().getResource("/edu/bothell/wampus/css/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleExitButton(ActionEvent event) {
        System.exit(0);
    }
}
