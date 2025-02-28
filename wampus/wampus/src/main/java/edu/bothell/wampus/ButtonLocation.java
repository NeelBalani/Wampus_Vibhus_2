package edu.bothell.wampus;

import java.awt.*;
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
    private GUI frame;

    public ButtonLocation(GUI frame, int x, int y, GameLocation location){
        super();
        this.x = x;
        this.y = y;
        this.location = location;
        this.frame = frame;

        this.addActionListener(this);
        this.setPreferredSize(new Dimension(100, 100));
        this.setMargin(new Insets(25, 25, 25, 25));
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setText(this.location.getLocationId()+"");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.frame.handleButtonClick(this.x, this.y);
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
