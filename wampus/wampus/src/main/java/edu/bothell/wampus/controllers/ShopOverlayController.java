package edu.bothell.wampus.controllers;

import edu.bothell.wampus.models.people.Person;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShopOverlayController {
    
    private GameController gameController;
    private GameScreenController gameScreenController;
    private Parent overlay;
    
    @FXML
    private Label goldLabel;
    
    public void init(GameController gameController, GameScreenController gameScreenController, Parent overlay) {
        this.gameController = gameController;
        this.gameScreenController = gameScreenController;
        this.overlay = overlay;
        
        // Update gold display
        updateGoldDisplay();
    }
    
    private void updateGoldDisplay() {
        Person player = gameController.getActiveTeammate();
        goldLabel.setText("Your Gold: " + player.getGold());
    }
    
    @FXML
    private void handleBuyArrow() {
        Person player = gameController.getActiveTeammate();
        int arrowCost = 10;
        
        if (player.getGold() >= arrowCost) {
            player.subtractGold(arrowCost);
            player.addArrow();
            showSuccessMessage("Arrow purchased! You now have " + player.getAmmo() + " arrows.");
            updateGoldDisplay();
        } else {
            showErrorMessage("Not enough gold! Arrows cost " + arrowCost + " gold.");
        }
    }
    
    @FXML
    private void handleBuyPotion() {
        Person player = gameController.getActiveTeammate();
        int potionCost = 15;
        
        if (player.getGold() >= potionCost) {
            player.subtractGold(potionCost);
            // Currently no health mechanic, but we can add it later
            player.heal(10);
            showSuccessMessage("Health potion purchased and used!");
            updateGoldDisplay();
        } else {
            showErrorMessage("Not enough gold! Health potions cost " + potionCost + " gold.");
        }
    }
    
    @FXML
    private void handleBuyRadar() {
        Person player = gameController.getActiveTeammate();
        int radarCost = 20;
        
        if (player.getGold() >= radarCost) {
            player.subtractGold(radarCost);
            gameScreenController.revealHazards();
            showSuccessMessage("Radar purchased and activated!");
            updateGoldDisplay();
        } else {
            showErrorMessage("Not enough gold! Radar costs " + radarCost + " gold.");
        }
    }
    
    @FXML
    private void handleExitButton() {
        closeShop();
    }
    
    private void closeShop() {
        gameScreenController.closeShop(overlay);
    }
    
    private void showSuccessMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Purchase Successful");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Purchase Failed");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
