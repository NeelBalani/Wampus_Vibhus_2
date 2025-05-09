package edu.bothell.wampus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class DpadButtons implements ActionListener {

    private JButton button;
    private GUI frame;
    private Directions direc;
    private String direcString;

    public DpadButtons(GUI f, String dir){
        this.frame = f;
        this.direcString = dir;
        this.direc = Directions.getDirectionFromString(dir);

        System.out.println(direc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.handleMoveInDirection(Integer.parseInt(direcString));
    }
    
    public JButton giveJButton(){
        this.button = new JButton();
        System.out.println(direcString);
        this.button.setText(direcString);
        this.button.addActionListener(this);
        return this.button;
    }
}
