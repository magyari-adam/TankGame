package Engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Engine {

    private ArrayList<Tank> tanks;
    private ArrayList<Bullet> bullets;
    private MapModel mapModel;


    private final transient int VEC_LENGTH = 2;

    public Engine() {
        this.mapModel = new MapModel();
        tanks = new ArrayList<>();//nope
        tanks.add(new Tank(new Vec2D(50, mapModel.getVerticalPosition(50)-60), 10));//nope
        tanks.add(new Tank(new Vec2D(300, 50), 10));//nope
        bullets = new ArrayList<>();
        bullets.add( new Bullet(new Vec2D(145, 55), new Vec2D()));
    }

    public Engine(ArrayList<Tank> tank, ArrayList<Bullet> bullet) {
        this.tanks = tank;
        this.bullets = bullet;
        this.mapModel = new MapModel(FunctionChooser.first);
    }

    public void keyEventRecognizer(KeyEvent event) {
        Vec2D position = this.tanks.get(0).getPosition();
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (position.getX() <= 0) { return; }
                move( position.getX() - 10);
                break;
            case KeyEvent.VK_UP:
                this.tanks.get(0).setTurretAngle(this.tanks.get(0).getTurretAngle() + 1);
                break;
            case KeyEvent.VK_RIGHT:
                if (position.getX() >= 710) { return; }
                move( position.getX() + 10);
                break;
            case KeyEvent.VK_DOWN:
                this.tanks.get(0).setTurretAngle(this.tanks.get(0).getTurretAngle() - 1);
                break;
            case KeyEvent.VK_SPACE:
                shoot(tanks.get(0).getTurretAngle(),0);
                break;
            default:
                System.out.println("Unrecognized indentifier");
        }
    }


    public void move(int newPlace){
        this.tanks.get(0).setPosition(new Vec2D(newPlace,mapModel.getVerticalPosition(newPlace)-60));
    }

    public void move(int newPlace, int tankID){
        if (tankID >= tanks.size() || tankID < 1){ return; }
        this.tanks.get(tankID).setPosition(new Vec2D(newPlace,mapModel.getVerticalPosition(newPlace)-60));
    }

    public void shoot(int facing, int tankID){
        Tank tank = tanks.get(tankID);
        int turretAngle = tank.getTurretAngle();
        double x = Math.cos(turretAngle) * VEC_LENGTH;
        double y = Math.sin(turretAngle) * VEC_LENGTH;
        bullets.add(new Bullet(new Vec2D(tank.getPosition().getX(),tank.getPosition().getY()),new Vec2D((int)Math.round(x),(int)Math.round(y))));
    }


    public MapModel getMap() {
        return this.mapModel;
    }

    public ArrayList<Tank> getTanks() {
        return this.tanks;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setTanks(ArrayList<Tank> tanks) {
        this.tanks = tanks;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public int getBulletsHashCode(){
        return this.bullets.hashCode();
    }

    public int getTanksHashCode(){
        return this.tanks.hashCode();
    }
}
