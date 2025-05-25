package edu.bothell.wampus.controllers;

import edu.bothell.wampus.models.people.Pranav;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class LevelPickerController {
    @FXML private Button level1Btn;

    @FXML
    private void handleLevel1(ActionEvent event) {
        try {
            // Initialize game controller with the cave file path
            InputStream caveStream = getClass().getClassLoader().getResourceAsStream("edu/bothell/wampus/maps/MapGraph.csv");
            if (caveStream == null) {
                throw new FileNotFoundException("Could not find MapGraph.csv in resources");
            }
            GameController gameController = new GameController(caveStream);
            gameController.addPerson(new Pranav("A"));
//        this.gameController = new GameController("src/main/resources/edu/bothell/wampus/maps/MapGraph.csv"); // DEVELOPMENT IF ABOVE BREAKS

            System.out.println("game controller initialized");

            // Load the game screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bothell/wampus/views/GameScreen.fxml"));
            Parent root = loader.load();
            // Push the game controller to the game screen controller
            edu.bothell.wampus.controllers.GameScreenController controller = loader.getController();
            controller.initGameController(gameController);

            // Get the current stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/edu/bothell/wampus/css/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
