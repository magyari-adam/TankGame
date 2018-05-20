package Engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Engine {

    private ArrayList<Tank> tanks;
    private ArrayList<Bullet> bullets;
    private MapModel mapModel;


    private final transient int VEC_LENGTH = 20;
    private final transient int COLLISION_RADIUS = 40;
    private transient boolean endOfGame = false;

    public Engine() {
        this.mapModel = new MapModel(FunctionChooser.second);
        tanks = new ArrayList<>();//nope
        tanks.add(new Tank(new Vec2D(50, mapModel.getVerticalPosition(50)-30), 3));//nope
        tanks.add(new Tank(new Vec2D(300, mapModel.getVerticalPosition(300)-30), 3));//nope
        bullets = new ArrayList<>();
        //bullets.add( new Bullet(new Vec2D(145, 55), new Vec2D()));
    }

    public Engine(ArrayList<Tank> tank, ArrayList<Bullet> bullet) {
        this.tanks = tank;
        this.bullets = bullet;
        this.mapModel = new MapModel(FunctionChooser.first);
    }

    public void keyEventRecognizer(KeyEvent event) {
        if (endOfGame) return;
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
                System.out.println("Unrecognized identifier");
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
        System.out.println("angle: "+turretAngle);
        double x = Math.cos(Math.toRadians(turretAngle)) * VEC_LENGTH;
        double y = Math.sin(Math.toRadians(turretAngle)) * VEC_LENGTH * -1;
        System.out.println("x: "+x+" y: "+y);
        bullets.add(new Bullet(new Vec2D(tank.getPosition().getX(),tank.getPosition().getY()),new Vec2D((int)Math.round(x) * facing,(int)Math.round(y)),tankID));
    }

    public void tick(){
        for (Iterator<Bullet> iterator = bullets.iterator();iterator.hasNext();){
            Bullet actualBullet = iterator.next();

            Vec2D newVec = actualBullet.getPosition();
            if (newVec.getX() > 800 || newVec.getY() > 600 || newVec.getX() < 0 || newVec.getY() < 0){
                // bullet kiment a tablabol
                iterator.remove();
                continue;
            }else if (mapModel.getMapRepresentation()[actualBullet.getPosition().getX()][actualBullet.getPosition().getY()] == true){
                //bullet nekiment a földnek
                iterator.remove();
                continue;
            }

            newVec.add(actualBullet.getVelocity());
            actualBullet.setVelocity(Vec2D.add(actualBullet.getVelocity(),new Vec2D(0,1)));
            actualBullet.setPosition(newVec);
            for (Tank tank : tanks){
                if (detectCollision(tank.getPosition(),actualBullet.getPosition())){
                    if(!tank.equals(tanks.get(actualBullet.getTankID()))){
                        System.out.println("tank health -1");
                        tank.setHealth(tank.getHealth() - 1);
                        if (tank.getHealth() == 0){
                            endOfGame = true;
                            System.out.println("tank has blown up");
                        }
                        iterator.remove();
                    }
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
        return (int)Math.sqrt(horizontalDistance * horizontalDistance + verticalDistance * verticalDistance);
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

    public boolean isEndOfGame() {
        return endOfGame;
    }

    public void setEndOfGame(boolean endOfGame) {
        this.endOfGame = endOfGame;
    }
}
