package View;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BaseWindow extends JFrame{

    protected void exit(){

        int returnVal = JOptionPane.showConfirmDialog(this,"Are you sure you wish to quit the game?","Confirmation",JOptionPane.YES_NO_OPTION);
        if (returnVal == JOptionPane.YES_NO_OPTION) System.exit(0);

    }

    private WindowAdapter kilepes = new WindowAdapter(){

        public void windowClosing(WindowEvent e){

            exit();
        }
    };

    public BaseWindow(){

        setSize(800,600);
        setResizable(false);
        setTitle("Game");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(kilepes);
        setVisible(true);
    }
}
