package edu.bothell.wampus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class DpadButtons implements ActionListener {

    private JButton button;
    private GUI frame;
    private String direc;

        public DpadButtons(GUI frame, String dir){
            frame = this.frame;
            direc = dir;
        System.out.println(direc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(direc);
    }
    
    public JButton giveJButton(){
        this.button = new JButton();
        System.out.println(direc);
        this.button.setText(direc);
        this.button.addActionListener(this);
        return this.button;
    }
}
