package Engine;

import java.awt.event.KeyEvent;

public class Engine {

    private Tank[] tanks;
    private Bullet[] bullets;
    private MapModel mapModel;

    public Engine(){
        tanks=new Tank[2];//nope
        tanks[0]=new Tank(new Vec2D(50,50),10);//nope
        tanks[1]=new Tank(new Vec2D(300,50),10);//nope
        bullets=new Bullet[1];
        bullets[0]=new Bullet(new Vec2D(145,55),new Vec2D());
        this.mapModel = new MapModel(FunctionChooser.first);
    }

    public Engine(Tank[] tank, Bullet[] bullet) {
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
            default:
                System.out.println("Unrecognized indentifier");
        }
    }

    public MapModel getMap() {
        return this.mapModel;
    }

    public Tank[] getTanks() {
        return this.tanks;
    }

    public Bullet[] getBullets() {
        return bullets;
    }
}
