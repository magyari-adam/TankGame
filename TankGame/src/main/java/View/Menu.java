package View;


import Control.Client;
import Control.GameServer;
import Engine.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Menu extends  BaseWindow{

    private Container gc = getContentPane();
    private final Render gamePanel;
    private final Engine engine;
    private GameServer gameServer;
    private Client c;


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

        this.engine = new Engine();
        this.gamePanel = new Render(this.engine);


        gc.setLayout(new BorderLayout());
        gc.add(gamePanel,BorderLayout.CENTER);
    }


    private AbstractAction client = new AbstractAction("Connect")
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String ipAddress = JOptionPane.showInputDialog(gamePanel,
                    "Add a new IP address to connect", null);

            gamePanel.requestFocus();
            try {
                c = new Client(ipAddress,engine,gamePanel);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            } catch (NotBoundException e1) {
                e1.printStackTrace();
            }

            gamePanel.changeKeyListener(c);
            gamePanel.refresh();
        }
    };


    private AbstractAction host = new AbstractAction("Host game")
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(gameServer!=null){
                gameServer.clean();
            }
            gamePanel.requestFocus();
            try {
                gameServer = new GameServer(engine,gamePanel);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }

            gamePanel.changeKeyListener(gameServer);
            gamePanel.refresh();
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