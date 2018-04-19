package Engine;

import java.awt.event.KeyEvent;

public class Engine {

    private Tank[] tanks;
    private Bullet[] bullets;
    private MapModel mapModel;


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
