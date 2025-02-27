package edu.bothell.wampus;


import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI implements CaveView{
    
    // Properties
    private JButton[][] button = new JButton[6][5];
    private Controller c;
    private Cave cave;

    public GUI(Controller c, Cave cave){
        this.c = c;
        this.cave = cave;

        for(int i = 0; i < button.length ;i++){
            for(int o = 0; o < button[i].length; o++)
            this.button[i/6][o%5] = new ButtonLocation(i/3, o%3, this.cave.getLocationBasedOnCoords(i, o), this.c);
        }


        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setLayout(new FlowLayout());
    }

    @Override
    public JButton[][] getButtons() {
        return button;
    }

    @Override
    public void updateGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGrid'");
    }

    @Override
    public void handleButtonClick(int row, int col) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleButtonClick'");
    }

    
       
}
