package Control;

import Engine.Bullet;
import Engine.MapModel;
import Engine.Tank;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameServerIFace extends Remote{
    void move(int id, int posX) throws RemoteException;
    void shoot(int id) throws RemoteException;
    int getMapHashCode() throws RemoteException;
    int getTanksHashCode() throws RemoteException;
    int getBulletsHashCode() throws RemoteException;
    MapModel getMap() throws RemoteException;
    ArrayList<Tank> getTanks() throws RemoteException;
    ArrayList<Bullet> getBullets() throws RemoteException;
    int getID() throws RemoteException;
    boolean isReady() throws RemoteException;
    boolean isEnd() throws RemoteException;
}
