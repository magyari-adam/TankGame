package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu extends  BaseWindow{

    private Container gc = getContentPane();
    private JPanel gamePanel;

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

        gamePanel = new JPanel();

        gamePanel.setLayout(new GridLayout(1,1));

        gc.setLayout(new BorderLayout());
        gc.add(gamePanel,BorderLayout.CENTER);
    }


    private AbstractAction start = new AbstractAction("Play")
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Render background=new Render(0,0,gamePanel);
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