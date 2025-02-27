package edu.bothell.wampus;


import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

    private JButton[][] button = new JButton[6][5];

    public GUI(){

        for(int i = 0; i < button.length ;i++){
            for(int o = 0; o < button[i].length; o++)
            this.button[i/6][o%5] = new ButtonLocation(i/3, o%3, new GameLocation(i/3, o%3));
        }


        JFrame frame = new JFrame();
        frame.setVisible(true);
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
