package edu.bothell.wampus;


import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Let GUI implement UI and replace the previous UI
// Removes the middleman UI that implements GUI methods
// Makes much more sense for the Controller to just talk to the GUI
public class GUI extends JFrame{

    private JButton[][] buttons;
    private GameController c;

    public GUI(GameController c){
        super();

        this.c = c;
        int size = this.c.getCave().getSize();
        System.out.println(size);

        super.setTitle("My first Jframe");
        super.setSize(6*100,5*100);
        super.setLayout(new FlowLayout());
        this.buttons = new JButton[6][5];

        JPanel pane = new JPanel();        
        pane.setLayout(new GridLayout(5,6));

        for(int y = 0; y < this.buttons.length; y++){
            for(int x = 0; x < this.buttons[y].length; x++){
                this.buttons[y][x] = new ButtonLocation(this, ((y * 5 + x % 5) + 1)).giveJButton();
                this.buttons[y][x].setSize(100, 100);
                System.out.println("Y: " + y + "; X: " + x);
                pane.add(this.buttons[y][x]);
            }
        }
        this.add(pane);
        pane.setVisible(true);

        JPanel movePane = new JPanel();
        movePane.setLayout(new FlowLayout());
        movePane.add(new DpadButtons(this, "Up").giveJButton());
        movePane.add(new DpadButtons(this, "Right").giveJButton());
        movePane.add(new DpadButtons(this, "Down").giveJButton());
        movePane.add(new DpadButtons(this, "Left").giveJButton());

        this.add(movePane);
        movePane.setVisible(true);

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setVisible(true);

    }




    public JButton[][] getButtons() {
        return buttons;
    }

    public void handleButtonClick(int roomId) {
        AdjacentGameLocation g = this.c.getCave().getLocationBasedOnId(roomId);
        System.out.println(g.getLocationId());
        System.out.println(this.c.findWhatIsInLocation(g));
    }

    public void handleMoveInDirection(String dir){
        Directions direction = Directions.N;
        if(dir.equals("Left")) direction = Directions.W;
        if(dir.equals("Right")) direction = Directions.E;
        if(dir.equals("Up")) direction = Directions.N;
        if(dir.equals("Down")) direction = Directions.S;
        //VIBHU FIX THIS PLEASE GOOD SIR
        System.out.println(dir);
        this.c.movePlayerUsingDirections(direction);
    }

}
