package edu.bothell.wampus;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.naming.java.javaURLContextFactory;
import org.thymeleaf.standard.expression.Fragment;

public class GUI extends JFrame implements CaveView{

    private JButton[][] buttons;
    private Controller c;

    public GUI(Controller c){
        super();

        this.c = c;
        int[] size = this.c.getCave().getSize();
        
        super.setTitle("My first Jframe");
        super.setSize(size[0]*100,size[1]*100);
        super.setLayout(new FlowLayout());
        this.buttons = new JButton[this.c.getCave().getSize()[0]][this.c.getCave().getSize()[1]];
        
        JPanel pane = new JPanel();        
        pane.setLayout(new GridLayout(size[0],size[1]));
        
        pane.setSize(size[0]*100, size[1]*100);
        
        for(int y = 0; y < this.buttons.length; y++){
            for(int x = 0; x < this.buttons[y].length; x++){
                this.buttons[y][x] = new ButtonLocation(this, x, y).giveJButton();
                this.buttons[y][x].setSize(100, 100);
                System.out.println("Y: " + y + "; X: " + x);
                pane.add(this.buttons[y][x]);
            }
        }
        this.add(pane);
        pane.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(true);

    }

    @Override
    public JButton[][] getButtons() {
        return buttons;
    }

    @Override
    public void updateGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGrid'");
    }

    @Override
    public void handleButtonClick(int row, int col) {
        GameLocation g = this.c.getCave().getLocationBasedOnCoords(row, col);
        System.out.println(g.getLocationId());
        System.out.println(this.c.findWhatIsInLocation(g));
    }

    public int getLocationID(int row, int col){
        return this.c.getCave().getLocationBasedOnCoords(row, col).getLocationId();
    }


    
       
}
