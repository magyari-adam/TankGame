package Engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is for doing all the calculations, and the logic behind the view.
 */
public class Engine {

    private ArrayList<Tank> tanks;
    private ArrayList<Bullet> bullets;
    private MapModel mapModel;


    private final transient int VEC_LENGTH = 20;
    private final transient int COLLISION_RADIUS = 40;
    private transient boolean endOfGame = false;

    /**
     * Empty constructor: creates a new Enginge object with default parameters.
     */
    public Engine() {
        this.mapModel = new MapModel(FunctionChooser.second);
        tanks = new ArrayList<>();//nope
        tanks.add(new Tank(new Vec2D(50, mapModel.getVerticalPosition(50)-30), 3));//nope
        tanks.add(new Tank(new Vec2D(300, mapModel.getVerticalPosition(300)-30), 3));//nope
        bullets = new ArrayList<>();
    }

    /**
     * Constructor: creates a new Engine object, with the following parameters:
     * @param tanks list of tanks
     * @param bullets list of bullets
     */
    public Engine(ArrayList<Tank> tanks, ArrayList<Bullet> bullets) {
        this.tanks = tanks;
        this.bullets = bullets;
        this.mapModel = new MapModel(FunctionChooser.first);
    }

    /**
     * This method is for recognizing key events.
     * @param event key event.
     */
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

    /**
     * Change the specific tank's turret angle by the given deg.
     * @param deg the amount of the degrees change.
     * @param tankID the ID of the tank.
     */
    public void changeTurretAngleByOne(int deg, int tankID){
        if (tankID >= tanks.size() || tankID < 0){ return; }
        this.tanks.get(tankID).setTurretAngle(tanks.get(tankID).getTurretAngle() + deg);
    }

    /**
     * Moves the first tank to the following place:
     * @param newPlace new place of the tank
     */
    public void move(int newPlace){
        if (newPlace <= 0 || newPlace >= 710) { return; }
        this.tanks.get(0).setPosition(new Vec2D(newPlace,mapModel.getVerticalPosition(newPlace)-30));
    }

    /**
     * Moves the specific tank to the following place:
     * @param newPlace new place of the tank
     * @param tankID indentifier of the specific tank
     */
    public void move(int newPlace, int tankID){
        if (tankID >= tanks.size() || tankID < 0){ return; }
        if (newPlace <= 0 || newPlace >= 710) { return; }
        this.tanks.get(tankID).setPosition(new Vec2D(newPlace,mapModel.getVerticalPosition(newPlace)-30));
    }

    /**
     * Shoots the bullet with the specific angle, from the specific tank.
     * @param facing angle of the bullet
     * @param tankID identifier of the bullet's owner tank
     */
    public void shoot(int facing, int tankID){
        Tank tank = tanks.get(tankID);
        int turretAngle = tank.getTurretAngle();
        System.out.println("angle: "+turretAngle);
        double x = Math.cos(Math.toRadians(turretAngle)) * VEC_LENGTH;
        double y = Math.sin(Math.toRadians(turretAngle)) * VEC_LENGTH * -1;
        System.out.println("x: "+x+" y: "+y);
        bullets.add(new Bullet(new Vec2D(tank.getPosition().getX(),tank.getPosition().getY()),new Vec2D((int)Math.round(x) * facing,(int)Math.round(y)),tankID));
    }

    /**
     * The core logic of the game, refreshes positions, calculating collisions etc.
     */
    public void tick(){
        for (Iterator<Bullet> iterator = bullets.iterator();iterator.hasNext();){
            Bullet actualBullet = iterator.next();

            Vec2D newVec = actualBullet.getPosition();
            if (newVec.getX() > 800 || newVec.getY() > 600 || newVec.getX() < 0 || newVec.getY() < 0){
                // bullet goes outside the board
                iterator.remove();
                continue;
            }else if (mapModel.getMapRepresentation()[actualBullet.getPosition().getX()][actualBullet.getPosition().getY()] == true){
                //bullet hits the ground
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

    /**
     * Collision detection between a bullet and a tank
     * @param tank the given tank object
     * @param bullet the given bullet object
     * @return returns true if the bullet hits the tank else returns false.
     */
    private boolean detectCollision(Vec2D tank, Vec2D bullet){
        return (COLLISION_RADIUS >= getDistanceBetween(tank,bullet));
    }

    /**
     * Gives back the distance between the two Vec2D object
     * @param lhs left hand side operand
     * @param rhs right hand side operand
     * @return returns the calculated distance.
     */
    private static int getDistanceBetween(Vec2D lhs,Vec2D rhs){
        int horizontalDistance = Math.abs(lhs.getX() - rhs.getX());
        int verticalDistance = Math.abs(lhs.getY() - rhs.getY());
        return (int)Math.sqrt(horizontalDistance * horizontalDistance + verticalDistance * verticalDistance);
    }

    /**
     *
     * @return returns the Engine's mapmodel.
     */
    public MapModel getMap() {
        return this.mapModel;
    }

    /**
     *
     * @return returns all of the tanks which are attached to the actual Engine.
     */
    public ArrayList<Tank> getTanks() {
        return this.tanks;
    }

    /**
     *
     * @return  returns all of the bullets which are attached to the actual Engine.
     */
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    /**
     * Change all of the actual tanks to a bunch of new tanks.
     * @param tanks list of new tanks
     */
    public void setTanks(ArrayList<Tank> tanks) {
        this.tanks = tanks;
    }

    /**
     * Change all of the actual bullets to a bunch of new tanks
     * @param bullets
     */
    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    /**
     * Change the actual mapmodel to a new one.
     * @param mapModel the new mapmodel
     */
    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    /**
     *
     * @return returns the hashcode of all bullets list
     */
    public int getBulletsHashCode(){
        return this.bullets.hashCode();
    }

    /**
     *
     * @return returns the hashcode of all the tanks list
     */
    public int getTanksHashCode(){
        return this.tanks.hashCode();
    }

    /**
     *
     * @return returns a boolean value which determines whether the game is ended or not.
     */
    public boolean isEndOfGame() {
        return endOfGame;
    }

    /**
     * Sets the value which determines whether the game is ended or not.
     * @param endOfGame
     */
    public void setEndOfGame(boolean endOfGame) {
        this.endOfGame = endOfGame;
    }
}
