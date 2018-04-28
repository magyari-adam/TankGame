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
        tanks.add(new Tank(new Vec2D(50, mapModel.getVerticalPosition(50)-30), 10));//nope
        tanks.add(new Tank(new Vec2D(300, mapModel.getVerticalPosition(300)-30), 10));//nope
        bullets = new ArrayList<>();
        //bullets.add( new Bullet(new Vec2D(145, 55), new Vec2D()));
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

    public void changeTurretAngleByOne(int deg, int tankID){
        if (tankID >= tanks.size() || tankID < 0){ return; }
        this.tanks.get(tankID).setTurretAngle(tanks.get(tankID).getTurretAngle() + deg);
    }

    public void move(int newPlace){
        if (newPlace <= 0 || newPlace >= 710) { return; }
        this.tanks.get(0).setPosition(new Vec2D(newPlace,mapModel.getVerticalPosition(newPlace)-30));
    }

    public void move(int newPlace, int tankID){
        if (tankID >= tanks.size() || tankID < 0){ return; }
        if (newPlace <= 0 || newPlace >= 710) { return; }
        this.tanks.get(tankID).setPosition(new Vec2D(newPlace,mapModel.getVerticalPosition(newPlace)-30));
    }

    public void shoot(int facing, int tankID){
        Tank tank = tanks.get(tankID);
        int turretAngle = tank.getTurretAngle();
        double x = Math.abs(Math.cos(turretAngle) * VEC_LENGTH);
        double y = Math.abs(Math.sin(turretAngle) * VEC_LENGTH);
        bullets.add(new Bullet(new Vec2D(tank.getPosition().getX() * facing,tank.getPosition().getY()),new Vec2D((int)Math.round(x) * facing,(int)Math.round(y))));
    }

    private Vec2D utils(int angle){
        double x = Math.abs(Math.cos(angle) * VEC_LENGTH);
        double y = Math.abs(Math.sin(angle) * VEC_LENGTH);
        if (angle < 0){
            return new Vec2D((int)Math.round(x) ,(int)Math.round(y) );
        }else{
            return new Vec2D((int)Math.round(x) * 15,(int)Math.round(y) * -15);
        }
    }

    public void tick(){
        for (Bullet x : bullets){
            if (x == null){
                return;
            }
            Vec2D newVec = x.getPosition();
            if (newVec.getX() > 800 || newVec.getY() > 600 || newVec.getX() < 0 || newVec.getY() < 0){
                // bullet kiment a tablabol
                x = null;
                continue;
            }
            int turretAngle = tanks.get(0).getTurretAngle();
            System.out.println("oldVal: "+x.getVelocity().toString() + " newval: "+newVec.toString());
            newVec.add(utils(turretAngle));
            newVec.add(x.getVelocity());
            x.setPosition(new Vec2D(newVec));
            x.setVelocity(new Vec2D(0,1));
            for (Tank tank : tanks){
                if ((!tank.equals(tanks.get(0))) && detectCollision(tank.getPosition(),x.getPosition())){
                    // talalat erte a tankot
                    System.out.println("tank health -1");
                    tank.setHealth(tank.getHealth() - 1);
                    if (tank.getHealth() == 0){
                        //tank felrobbant
                        System.out.println("tank has blown up");
                    }
                    x = null;
                    continue;
                }
            }
        }

    }

    private boolean detectCollision(Vec2D tank, Vec2D bullet){
        return (COLLISION_RADIUS >= getDistanceBetween(tank,bullet));
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
