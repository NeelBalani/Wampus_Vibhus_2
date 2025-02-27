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

public class ButtonLocation implements Locatable, ActionListener{

    private int x;
    private int y;
    private GameLocation location;

    public ButtonLocation(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
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
    public GameLocation getLocation(){
        return location;
    }
    
}
