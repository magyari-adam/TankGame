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
    private int pozx=50;
    private int pozy=50;
    private boolean palya[][];


    public Menu(){

        JMenuBar menu = new JMenuBar();
        JMenu playMenu = new JMenu("Play");
        JMenu settingsMenu = new JMenu("Settings");
        JMenu quitMenu = new JMenu("Quit");

        JMenuItem clientItem = new JMenuItem(client);
        JMenuItem hostItem = new JMenuItem(host);
        JMenuItem kilepes = new JMenuItem(quit);
        JMenuItem settings = new JMenuItem("Settings");

        playMenu.add(clientItem);
        playMenu.add(hostItem);
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


    private AbstractAction client = new AbstractAction("Connect")
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String ipAddress = JOptionPane.showInputDialog(gamePanel,
                    "Add a new IP address to connect", null);
            // do smth with ip
        }
    };


    private AbstractAction host = new AbstractAction("Host game")
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            gamePanel.start();
            gamePanel.requestFocus();
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