package edu.bothell.wampus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class DpadButtons implements ActionListener {

    private JButton button;
    private GUI frame;
    
    public DpadButtons(GUI frame){
        frame = this.frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
    public JButton giveJButton(int n){
        this.button = new JButton();
        this.button.setText(n + " ");
        this.button.addActionListener(this);
        return this.button;
    }
}
