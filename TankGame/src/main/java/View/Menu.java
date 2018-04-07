package View;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu extends  BaseWindow{

    private Container gc = getContentPane();
    private Render gamePanel;

    public Menu(){

        JMenuBar menu = new JMenuBar();
        JMenu playMenu = new JMenu("Play");
        JMenu settingsMenu = new JMenu("Settings");
        JMenu quitMenu = new JMenu("Quit");

        JMenuItem play = new JMenuItem(start);
        JMenuItem kilepes = new JMenuItem(quit);
        JMenuItem settings = new JMenuItem("Settings");

        playMenu.add(play);
        menu.add(playMenu);

        settingsMenu.add(settings);
        menu.add(settingsMenu);

        quitMenu.add(kilepes);
        menu.add(quitMenu);

        setJMenuBar(menu);
        menu.setVisible(true);

        gamePanel = new Render(800,600);


        gc.setLayout(new BorderLayout());
        gc.add(gamePanel,BorderLayout.CENTER);
    }


    private AbstractAction start = new AbstractAction("Play")
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

            BufferedImage image = null;
            try {
                image = ImageIO.read(getClass().getResource("/assets/bg.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            gamePanel.paintimagetopanel(image,0,0);
            Render background=new Render();
            Graphics g=gamePanel.getGraphics();
            background.paintbattleground(gamePanel.getWidth(),gamePanel.getHeight(),g);
            gamePanel.paintComponents(g);
        }
    };

    private  AbstractAction quit = new AbstractAction("Kilépés")
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            exit();
        }
    };


}