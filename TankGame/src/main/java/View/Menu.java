package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {
    public static void main(String args[]){
        inic();
    }
    private static void inic(){
        final JFrame form= new JFrame();
        form.setTitle("TankGame");
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setLayout(new BorderLayout());
        form.setSize(800,600);
        Container egesz=form.getContentPane();
        egesz.setLayout(new GridLayout(1,1));
        final JPanel menu=new JPanel();
        final JPanel game=new JPanel();
        menu.setLayout(new GridLayout(3,1));
        final JButton play=new JButton();
        ButtonSetup(play,"Play",Color.GRAY,null,100,50);
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                game.setVisible(true);
            }
        });
        mouseEvent(play);
        menu.add(play);
        final JButton valami=new JButton();
        ButtonSetup(valami,"Valami",Color.GRAY,null,100,50);
        valami.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Megnyomtad a gombot" , "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mouseEvent(valami);
        menu.add(valami);
        final JButton exit=new JButton();
        ButtonSetup(exit,"Exit",Color.GRAY,null,100,50);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mouseEvent(exit);
        menu.add(exit);
        egesz.add(menu);
        egesz.add(game);
        form.pack();
        form.setVisible(true);
    }

    private static void mouseEvent(final JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBackground(Color.GRAY);
            }
        });
    }

    private static void ButtonSetup(JButton hutton,String text, Color color, Border border,int width,int height){
        hutton.setSize(width,height);
        hutton.setBackground(color);
        hutton.setText(text);
        hutton.setBorder(border);
    }

}