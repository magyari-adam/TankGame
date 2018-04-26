package Control;

import Engine.Bullet;
import Engine.Engine;
import Engine.MapModel;
import Engine.Tank;
import View.Render;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class GameServer extends UnicastRemoteObject implements GameServerIFace {
    private int id;
    private Engine engine;
    private Render render;
    private ArrayList<Boolean> ready;

    public GameServer(Engine engine, Render render) throws RemoteException{
        this.id = 1;
        this.ready = new ArrayList<>();
        this.ready.add(false);
        this.engine=engine;
        this.render=render;
    }

    @Override
    public void move(int id, int posX) {
        engine.move(posX,id);
    }

    @Override
    public void shoot(int id) {
        this.ready.set(id,true);
        engine.shoot(id%2==0?1:-1,id);
    }

    @Override
    public int getMapHashCode() {
        return engine.getMap().hashCode();
    }

    @Override
    public int getTanksHashCode() {
        return engine.getTanksHashCode();
    }

    @Override
    public int getBulletsHashCode() {
        return engine.getBulletsHashCode();
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
