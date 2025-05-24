package edu.bothell.wampus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

public class WampusGameApp extends Application {
    private GameController gameController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("starting");
        // Initialize game controller with the cave file path
        InputStream caveStream = getClass().getClassLoader().getResourceAsStream("edu/bothell/wampus/maps/MapGraph.csv");
        if (caveStream == null) {
            throw new FileNotFoundException("Could not find MapGraph.csv in resources");
        }
        this.gameController = new GameController(caveStream);
//        this.gameController = new GameController("src/main/resources/edu/bothell/wampus/maps/MapGraph.csv"); // DEVELOPMENT IF ABOVE BREAKS

        System.out.println("game controller initialized");

        // Load the start screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/bothell/wampus/views/StartScreen.fxml"));
        Parent root = loader.load();

        // Set up the stage
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/edu/bothell/wampus/css/styles.css").toExternalForm());

        primaryStage.setTitle("Wumpus Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
