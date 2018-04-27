package Control;

import Engine.Engine;
import Engine.Vec2D;
import View.Render;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Client implements KeyListener {
    private final Engine engine;
    private final Render render;
    private final GameServerIFace server;
    private final int id;

    public Client(String ipAddress, int port, Engine engine, Render render) throws RemoteException, NotBoundException {
        this.engine = engine;
        this.render = render;

        this.server = (GameServerIFace) LocateRegistry.getRegistry(ipAddress,port).lookup("senshado");
        this.id = this.server.getID();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Vec2D position = this.engine.getTanks().get(this.id).getPosition();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                //if (position.getX() <= 0) { return; }
                try {
                    this.server.move(this.id, position.getX() - 10);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            case KeyEvent.VK_UP:
                try {
                    this.server.moveTurret(this.id, 1);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            case KeyEvent.VK_RIGHT:
                //if (position.getX() >= 710) { return; }
                try {
                    this.server.move( this.id, position.getX() + 10);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            case KeyEvent.VK_DOWN:
                try {
                    this.server.moveTurret(this.id, -1);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            case KeyEvent.VK_SPACE:
                try {
                    this.server.shoot(this.id);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            default:
                System.out.println("Unrecognized indentifier");
        }
    }
}
