package View;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu extends  BaseWindow{

    private Container gc = getContentPane();
    private Render gamePanel;
    int pozx=50;
    int pozy=50;

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

        gamePanel = new Render();


        gc.setLayout(new BorderLayout());
        gc.add(gamePanel,BorderLayout.CENTER);
    }


    private AbstractAction start = new AbstractAction("Play")
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            BufferedImage backgroundimg = null;
            BufferedImage image=null;
            try {
                backgroundimg = ImageIO.read(getClass().getResource("/assets/bg.png"));
                image=ImageIO.read(getClass().getResource("/assets/tankfull.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            gamePanel.paintbackgroundtopanel(backgroundimg,0,0);
            //gamePanel.paintbattleground();
            gamePanel.paintimagetopanel(image,0,0,image.getWidth(),image.getHeight(),pozx,pozy,pozx+image.getWidth(),pozy+image.getHeight());

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