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

    private int id;
    private GUI frame;
    private static String num;
    private JButton button;

    public ButtonLocation(GUI frame, int id){
        super();
        this.id = id;
        this.frame = frame;
        this.num = "" + id;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.button){
            this.frame.handleButtonClick(this.id);
        }
    }

    public int getId(){
        return this.id;
    }

    public JButton giveJButton(){
        this.button = new JButton();
        System.out.println(num);
        this.button.setText(num);
        this.button.addActionListener(this);
        return this.button;
    }
    
}
