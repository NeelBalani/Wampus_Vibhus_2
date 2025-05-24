package edu.bothell.wampus;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexagonalRoom extends Group {
    private final AdjacentGameLocation location;
    private final Polygon hexShape;

    public HexagonalRoom(AdjacentGameLocation location, double centerX, double centerY, double radius) {
        this.location = location;
        this.hexShape = createHexagon(centerX, centerY, radius);


        updateVisualState("Normal");

        hexShape.setStroke(Color.DARKGRAY);

        this.getChildren().add(hexShape);

        // Example event handler
        this.setOnMouseClicked(e -> {
            // Handle click, e.g., notify controller
            System.out.println("Room " + location.getLocationId() + " clicked");
            System.out.println("X: " + centerX + ", Y: " + centerY);
            System.out.println("Adjacent to Room: " + location.getAdjLocations());
        });
    }

    private Polygon createHexagon(double cx, double cy, double r) {
        Polygon hex = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.PI / 3.0 * i;
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

        // Update color or icon based on game state (e.g., player present, pit, wumpus)
        switch (state) {
            case "Player" -> hexShape.setFill(Color.BLUE);
            case "AdjacentToPlayer" -> hexShape.setFill(Color.AZURE);
            case "Obstacle" -> hexShape.setFill(Color.RED);
            case "Normal" -> hexShape.setFill(Color.LIGHTGRAY);
        }
    }
}
