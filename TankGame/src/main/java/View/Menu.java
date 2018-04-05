package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.Buffer;
import javax.swing.UnsupportedLookAndFeelException;


public class Menu {
    public static void main(String args[]){
        initializeMenu();
        try{
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex ){
            System.out.println("Error during UnsupportedLookAndFeel initilalization");
        }
    }

    private static void initializeMenu(){
        final JFrame menuWindow = new JFrame();
        menuWindow.setTitle("TankGame");
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setLayout(new BorderLayout());
        menuWindow.setSize(800,600);

        Container container = menuWindow.getContentPane();
        container.setLayout(new GridLayout(1,1));
        final JPanel menuPanel = new JPanel();
        final JPanel gamePanel = new JPanel();
        final JButton playButton = new JButton();


        buttonSetup(playButton,"Play",Color.GRAY,null,100,50);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.remove(menuPanel);
                Render background=new Render(0,0,gamePanel);
                Graphics g=gamePanel.getGraphics();
                background.paintbattleground(gamePanel.getWidth(),gamePanel.getHeight(),g);
                gamePanel.paintComponents(g);
            }
        });



        menuPanel.add(playButton);
        final JButton settingsButton = new JButton();
        buttonSetup(settingsButton,"Settings",Color.GRAY,null,100,50);
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Megnyomtad a gombot" , "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menuPanel.add(settingsButton);

        final JButton exitButton = new JButton();
        buttonSetup(exitButton,"Quit",Color.GRAY,null,100,50);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuPanel.add(exitButton);
        container.add(menuPanel);
        container.add(gamePanel);
        menuWindow.setVisible(true);
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

    private static void buttonSetup(JButton button, String text, Color color, Border border, int width, int height){
        button.setSize(width,height);
        button.setBackground(color);
        button.setText(text);
        button.setBorder(border);
        mouseEvent(button);
    }

}