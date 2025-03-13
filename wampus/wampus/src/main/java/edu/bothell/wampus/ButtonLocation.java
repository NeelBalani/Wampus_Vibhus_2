package edu.bothell.wampus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ButtonLocation implements ActionListener{

    private int x;
    private int y;
    private GUI frame;
    private GameLocation location;
    private static String num;

    public ButtonLocation(GUI frame, int x, int y,  GameLocation location){
        super();
        this.x = x;
        this.y = y;
        this.frame = frame;
        this.num = ((x*6)+y) + 1 + " ";
    }

    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("TESTING TESTING");
        this.frame.handleButtonClick(this.x, this.y);
    }

    public int getX(){
        return x;
    }

    public static JButton giveJButton(){
        JButton button = new JButton();
        System.out.println(num);
        button.setText(num);
        //button.setPreferredSize(100,100);
        return button;
    }

    
    public int getY(){
        return y;
    }
    
}
