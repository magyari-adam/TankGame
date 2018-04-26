package Engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Engine {

    private ArrayList<Tank> tanks;
    private ArrayList<Bullet> bullets;
    private MapModel mapModel;


    private final transient int VEC_LENGTH = 2;
    private final transient int COLLISION_RADIUS = 20;

    public Engine() {
        this.mapModel = new MapModel();
        tanks = new ArrayList<>();//nope
        tanks.add(new Tank(new Vec2D(50, mapModel.getVerticalPosition(50)-60), 10));//nope
        tanks.add(new Tank(new Vec2D(300, mapModel.getVerticalPosition(300)-60), 10));//nope
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
                shoot(1,0);
                break;
            default:
                System.out.println("Unrecognized indentifier");
        }
    }


    public void move(int newPlace){
        this.tanks.get(0).setPosition(new Vec2D(newPlace,mapModel.getVerticalPosition(newPlace)-30));
    }

    public void move(int newPlace, int tankID){
        if (tankID >= tanks.size() || tankID < 1){ return; }
        this.tanks.get(tankID).setPosition(new Vec2D(newPlace,mapModel.getVerticalPosition(newPlace)-30));
    }

    public void shoot(int facing, int tankID){
        Tank tank = tanks.get(tankID);
        int turretAngle = tank.getTurretAngle();
        double x = Math.cos(turretAngle) * VEC_LENGTH;
        double y = Math.sin(turretAngle) * VEC_LENGTH;
        bullets.add(new Bullet(new Vec2D(tank.getPosition().getX() * facing,tank.getPosition().getY()),new Vec2D((int)Math.round(x) * facing,(int)Math.round(y))));
    }


    public void tick(){
        for (Bullet x : bullets){
            Vec2D newVec = x.getPosition();
            newVec.add(x.getVelocity());
            x.setPosition(new Vec2D(newVec));
            x.setVelocity(new Vec2D(0,1));
            for (Tank tank : tanks){
                if (detectCollision(tank.getPosition(),x.getPosition())){
                    // talalat erte a tankot
                    tank.setHealth(tank.getHealth() - 1);
                    if (tank.getHealth() == 0){
                        //tank felrobbant
                    }
                }
            }
        }

    }

    public boolean detectCollision(Vec2D tank, Vec2D bullet){
        if (COLLISION_RADIUS >= getDistanceBetween(tank,bullet)){
            return true;
        }
        return false;
    }

    private int getDistanceBetween(Vec2D lhs,Vec2D rhs){
        int horizontalDistance = Math.abs(lhs.getX() - rhs.getX());
        int verticalDistance = Math.abs(lhs.getY() - rhs.getY());
        return (int)Math.sqrt(horizontalDistance + verticalDistance);
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
