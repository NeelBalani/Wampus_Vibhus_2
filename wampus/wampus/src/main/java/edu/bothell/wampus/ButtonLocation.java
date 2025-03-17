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
    private static String num;
    private JButton button;

    public ButtonLocation(GUI frame, int x, int y){
        super();
        this.x = x;
        this.y = y;
        this.frame = frame;
        this.num = String.valueOf(frame.getLocationID(this.y, this.x));
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.button){
            this.frame.handleButtonClick(this.y, this.x);
        }
    }

    public int getX(){
        return x;
    }

    public JButton giveJButton(){
        this.button = new JButton();
        System.out.println(num);
        this.button.setText(num);
        this.button.addActionListener(this);
        return this.button;
    }

    
    public int getY(){
        return y;
    }
    
}
