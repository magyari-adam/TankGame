package Engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Engine {

    private ArrayList<Tank> tanks;
    private ArrayList<Bullet> bullets;
    private MapModel mapModel;

    public Engine() {
        tanks = new ArrayList<>();//nope
        tanks.add(new Tank(new Vec2D(50, 50), 10));//nope
        tanks.add(new Tank(new Vec2D(300, 50), 10));//nope
        bullets = new ArrayList<>();
        bullets.add( new Bullet(new Vec2D(145, 55), new Vec2D()));
        this.mapModel = new MapModel(FunctionChooser.first);
    }

    public Engine(ArrayList<Tank> tank, ArrayList<Bullet> bullet) {
        this.tanks = tank;
        this.bullets = bullet;
        this.mapModel = new MapModel(FunctionChooser.first);
    }

    public void keyEventRecognizer(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                System.out.println("left pressed");
                break;
            case KeyEvent.VK_UP:
                System.out.println("up pressed");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("right pressed");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("down pressed");
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Space pressed");
                break;
            default:
                System.out.println("Unrecognized indentifier");
        }
    }

    public void recognizeEventFromOutside(){

    }

    public void move(Vec2D newPlace){

    }

    public void shoot(Vec2D vel){

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
}
