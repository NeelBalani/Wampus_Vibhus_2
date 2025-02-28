package edu.bothell.wampus;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

public class GUI extends JFrame implements CaveView{

    
    // Properties
    private JButton[][] button;
    private Controller c;
    private Cave cave;

    public GUI(Controller c, Cave cave){
        super();
        super.setTitle("Board!"); //Set the title
        super.setSize(600, 1000); //Set the size
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Set Close operation

        this.c = c;
        this.cave = cave;
        int cols = this.cave.getSize()[0];
        int rows = this.cave.getSize()[1];
        this.button = new JButton[cols][rows];
        JPanel panel = new JPanel(new GridLayout(rows, cols));

        super.getContentPane().removeAll();
        for(int i = 0; i < button.length ;i++){
            for(int o = 0; o < button[i].length; o++) {
                this.button[i][o] = new ButtonLocation(this, i, o, this.cave.getLocationBasedOnCoords(i, o));
                panel.add(this.button[i][o]);
                System.out.print(this.cave.getLocationBasedOnCoords(i,o).getLocationId());
            }System.out.println();
        }

        super.add(panel);

        updateGrid();
        super.setVisible(true);
    }

    @Override
    public JButton[][] getButtons() {
        return button;
    }

    @Override
    public void updateGrid() {
        super.pack();
        super.revalidate();
        super.repaint();
    }

    @Override
    public void handleButtonClick(int row, int col) {
        this.c.findWhatIsInLocation(this.cave.getLocationBasedOnCoords(row, col));
    }

    
       
}
