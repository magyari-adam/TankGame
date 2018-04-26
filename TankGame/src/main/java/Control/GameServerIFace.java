package Control;

public interface GameServerIFace {
    public void move(int id, int posX);
    public void shoot(int id);
    public int getMapHashCode();
    public int getTanksHashCode();
    public int getBulletsHashCode();
    public int getID();
    public boolean isReady();
    public boolean isEnd();
}
