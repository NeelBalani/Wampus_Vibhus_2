package edu.bothell.wampus;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.naming.java.javaURLContextFactory;
import org.thymeleaf.standard.expression.Fragment;

public class GUI extends JFrame implements CaveView{

    private JButton[][] buttons = new JButton[5][6];
    private Controller c;

    public GUI(Controller c){
        super();
        this.c = c;
        super.setTitle("My first Jframe");
        super.setSize(400,400);
        super.setLayout(new GridLayout(5,6));
        
        for(int i = 0; i < 30 ;i++){
            this.buttons[i/6][i%5] = ButtonLocation.giveJButton();
            this.add(this.buttons[i/6][i%5]);
        }

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(true);

    }

    @Override
    public JButton[][] getButtons() {
        return new JButton[1][1];
    }

    @Override
    public void updateGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGrid'");
    }

    @Override
    public void handleButtonClick(int row, int col) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleButtonClick'");
    }


    
       
}
