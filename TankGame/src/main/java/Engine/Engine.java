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
            case 37:
                System.out.println("left pressed");
                break;
            case 38:
                System.out.println("up pressed");
                break;
            case 39:
                System.out.println("right pressed");
                break;
            case 40:
                System.out.println("down pressed");
                break;
            default:
                System.out.println("Unrecognizer indentifier");
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
