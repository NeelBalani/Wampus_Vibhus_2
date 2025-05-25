package edu.bothell.wampus.models;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HexagonalRoom extends Group {
    private final AdjacentGameLocation location;
    private final Polygon hexShape;
    private final Label idLabel;

    public HexagonalRoom(AdjacentGameLocation location, double centerX, double centerY, double radius) {
        this.location = location;
        this.hexShape = createHexagon(centerX, centerY, radius);
        this.idLabel = createIdLabel(centerX, centerY, String.valueOf(location.getLocationId()));

        updateVisualState("Normal");
        hexShape.setStroke(Color.DARKGRAY);

        this.getChildren().addAll(hexShape, idLabel);

        this.setOnMouseClicked(e -> {
            System.out.println("Room " + location.getLocationId() + " clicked");
            System.out.println("X: " + centerX + ", Y: " + centerY);
            System.out.println("Adjacent to Room: " + location.getAdjLocations());
        });
    }

    
    private Label createIdLabel(double centerX, double centerY, String id) {
        Label label = new Label(id);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        label.setTextFill(Color.BLACK);
        
        // Center the label in the hexagon
        label.setLayoutX(centerX - 5);  // Adjust based on text width
        label.setLayoutY(centerY - 8);  // Adjust based on text height
        label.setMouseTransparent(true);  // So clicks go to the hexagon, not the label
        
        return label;
    }

    private Polygon createHexagon(double cx, double cy, double r) {
        Polygon hex = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 3.0 * i - Math.PI / 6.0;
            double x = cx + r * Math.cos(angle);
            double y = cy + r * Math.sin(angle);
            hex.getPoints().addAll(x, y);
        }
        return hex;
    }

    public AdjacentGameLocation getLocation() {
        return location;
    }

    public void updateVisualState(String state) {
        switch (state) {
            case "Player" -> {
                hexShape.setFill(Color.BLUE);
                idLabel.setTextFill(Color.WHITE);
            }
            case "Visited" -> {
                hexShape.setFill(Color.GREEN);
                idLabel.setTextFill(Color.BLACK);
            }
            case "AdjacentToPlayer" -> {
                hexShape.setFill(Color.AZURE);
                idLabel.setTextFill(Color.BLACK);
                System.out.println("Adjacent to player: " + location.getLocationId());
            }
            case "Obstacle" -> {
                hexShape.setFill(Color.RED);
                idLabel.setTextFill(Color.WHITE);
            }
            case "Normal" -> {
                hexShape.setFill(Color.LIGHTGRAY);
                idLabel.setTextFill(Color.BLACK);
            }
        }
    }
}
