package Control;

import Engine.Bullet;
import Engine.Engine;
import Engine.MapModel;
import Engine.Tank;
import Engine.Vec2D;
import View.Render;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GameServer extends UnicastRemoteObject implements GameServerIFace, KeyListener {
    private final Registry reg;
    private int id;
    private final Engine engine;
    private final Render render;
    private ArrayList<Boolean> ready;

    /**
     * @param engine {@link Engine} to use
     * @param render {@link Render} to use
     * @throws RemoteException If binding failed.
     */
    public GameServer(Engine engine, Render render) throws RemoteException{
        this.id = 1;
        this.ready = new ArrayList<>();
        this.ready.add(false);
        this.engine=engine;
        this.render=render;

        this.reg = LocateRegistry.createRegistry(10273);
        try {
            this.reg.bind("senshado",this);
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        Thread r = new Thread(new ServerRefresh(this));
        r.start();
    }

    /**
     * Unbinds server.
     */
    public void clean(){
        try {
            this.reg.unbind("senshado");
            UnicastRemoteObject.unexportObject(reg,true);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void move(int id, int posX) {
        if(!isEnd() && !ready.get(id) && ready.size()>1 ){
            this.engine.move(posX,id);
        }
    }

    @Override
    public synchronized void moveTurret(int id, int angleOffset) {
        if (!isEnd() && !ready.get(id) && ready.size()>1 ) {
            this.engine.changeTurretAngleByOne(angleOffset, id);
        }
    }

    @Override
    public synchronized void shoot(int id) {
        if(!isEnd() && !ready.get(id) && ready.size()>1 ) {
            this.ready.set(id, true);
            this.engine.shoot(id % 2 == 0 ? 1 : -1, id);

            if (isReady()) {
                Thread tick = new Thread(new DoTicks(this));
                tick.start();
            }
        }
    }

    /**
     * Tells the {@link Engine} to do the tick calculations.
     */
    public synchronized void doTick(){
        this.engine.tick();
    }

    /**
     * Unreadies all players.
     */
    public synchronized void unReadyAll(){
        for (int i = 0; i<ready.size();i++){
            ready.set(i,false);
        }
    }

    @Override
    public int getMapHashCode() {
        return this.engine.getMap().hashCode();
    }

    @Override
    public int getTanksHashCode() {
        return this.engine.getTanksHashCode();
    }

    @Override
    public int getBulletsHashCode() {
        return this.engine.getBulletsHashCode();
    }

    @Override
    public MapModel getMap() {
        return this.engine.getMap();
    }

    @Override
    public ArrayList<Tank> getTanks() {
        return this.engine.getTanks();
    }

    @Override
    public ArrayList<Bullet> getBullets() {
        return this.engine.getBullets();
    }

    @Override
    public int getID() {
        this.ready.add(false);
        return this.id++;
    }

    @Override
    public boolean isReady() {
        for (boolean r :this.ready ){
            if(!r){
                return false;
            }
        }
        return true;
    }

    @Override
    public synchronized boolean isEnd() {
        return this.engine.isEndOfGame();
    }

    /**
     * Tells {@link Render} to refresh.
     */
    public synchronized void refresh(){
        this.render.refresh();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Maps keystrokes to {@link Engine} method calls
     * @param e {@link KeyEvent}
     */
    @Override
    public synchronized void keyReleased(KeyEvent e) {
        Vec2D position = this.engine.getTanks().get(0).getPosition();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.move(0, position.getX() - 10);
                break;
            case KeyEvent.VK_UP:
                this.moveTurret(0, 1);
                break;
            case KeyEvent.VK_RIGHT:
                this.move( 0, position.getX() + 10);
                break;
            case KeyEvent.VK_DOWN:
                this.moveTurret(0, -1);
                break;
            case KeyEvent.VK_SPACE:
                this.shoot(0);
                break;
            default:
                this.engine.tick();
                System.out.println("Unrecognized identifier");
        }
    }
}
