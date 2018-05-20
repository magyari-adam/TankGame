package Control;

import Engine.Bullet;
import Engine.MapModel;
import Engine.Tank;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Client-server communication interface.
 */
public interface GameServerIFace extends Remote{
    /**
     * Registers tank move command with server.
     * @param id ID of client
     * @param posX the new position
     * @throws RemoteException If connection to server failed.
     */
    void move(int id, int posX) throws RemoteException;

    /**
     * Registers turret move with server.
     * @param id ID of client
     * @param angleOffset rotational offset in degrees
     * @throws RemoteException If connection to server failed.
     */
    void moveTurret(int id, int angleOffset) throws RemoteException;

    /**
     * Registers shoot command with server.
     * @param id ID of client
     * @throws RemoteException If connection to server failed.
     */
    void shoot(int id) throws RemoteException;

    /**
     * @return Server-side map hashcode
     * @throws RemoteException If connection to server failed.
     */
    int getMapHashCode() throws RemoteException;

    /**
     * @return Server-side tanks hashcode
     * @throws RemoteException If connection to server failed.
     */
    int getTanksHashCode() throws RemoteException;

    /**
     * @return Server-side bullets hashcode
     * @throws RemoteException If connection to server failed.
     */
    int getBulletsHashCode() throws RemoteException;

    /**
     * @return Server-side {@link MapModel} object
     * @throws RemoteException If connection to server failed.
     */
    MapModel getMap() throws RemoteException;

    /**
     * @return Server-side list of {@link Tank} objects
     * @throws RemoteException If connection to server failed.
     */
    ArrayList<Tank> getTanks() throws RemoteException;

    /**
     * @return Server-side list of {@link Bullet} objects
     * @throws RemoteException If connection to server failed.
     */
    ArrayList<Bullet> getBullets() throws RemoteException;

    /**
     * @return A new ID for freshly connected client
     * @throws RemoteException If connection to server failed.
     */
    int getID() throws RemoteException;

    /**
     * @return true if all players are ready, otherwise false
     * @throws RemoteException If connection to server failed.
     */
    boolean isReady() throws RemoteException;

    /**
     * @return true if the game ended, otherwise false
     * @throws RemoteException If connection to server failed.
     */
    boolean isEnd() throws RemoteException;
}
