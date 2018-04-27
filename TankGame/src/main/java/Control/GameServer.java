package Control;

import Engine.Bullet;
import Engine.Engine;
import Engine.MapModel;
import Engine.Tank;
import View.Render;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GameServer extends UnicastRemoteObject implements GameServerIFace {
    private final Registry reg;
    private int id;
    private final Engine engine;
    private final Render render;
    private ArrayList<Boolean> ready;

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
    }

    public void clean(){
        try {
            this.reg.unbind("senshado");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(int id, int posX) {
        this.engine.move(posX,id);
    }

    @Override
    public void moveTurret(int id, int angleOffset) {
        //this.engine;
    }

    @Override
    public void shoot(int id) {
        this.ready.set(id,true);
        this.engine.shoot(id%2==0?1:-1,id);
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
    public boolean isEnd() {
        return false;
    }
}
