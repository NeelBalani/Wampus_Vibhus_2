package edu.bothell.wampus;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ButtonLocation extends JButton implements Locatable, ActionListener{

    private int x;
    private int y;
    private GameLocation location;
    private Controller c;

    public ButtonLocation(GUI frame, int x, int y, GameLocation location, Controller c){
        super();
        this.x = x;
        this.y = y;
        this.location = location;
        this.c = c;

        frame.add(this);
        super.setSize(200,200);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        c.findWhatIsInLocation(this.location);
    }

    @Override
    public int getX(){
        return x;
    }

    @Override
    public int getY(){
        return y;
    }

    @Override
    public int[] getPos(){
        int[] array = new int[] {x,y};
        return array;
    }

    @Override
    public GameLocation getButtonLocation(){
        return location;
    }
    
}
