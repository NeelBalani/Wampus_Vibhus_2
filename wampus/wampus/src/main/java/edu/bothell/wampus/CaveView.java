package edu.bothell.wampus;

import javax.swing.*;

public interface CaveView{
    JButton[][] getButtons(); // Enforce a JButton grid
    void updateGrid(); // Enforce updating the display
    void handleButtonClick(int row, int col); // Ensure proper button handling
}
