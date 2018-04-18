package Engine;

import java.awt.event.KeyEvent;

public class Engine {

    private Tank tank;
    private Bullet bullet;
    private MapModel mapModel;


    public Engine(Tank tank,Bullet bullet,MapModel mapModel){
        this.tank = tank;
        this.bullet = bullet;
        this.mapModel = mapModel;
    }

    public void keyEventRecognizer(KeyEvent event){
        // to something with the event
    }
}
