package Engine;

import java.awt.event.KeyEvent;

public class Engine {

    private Tank[] tank;
    private Bullet[] bullet;
    private MapModel mapModel;


    public Engine(Tank[] tank,Bullet[] bullet){
        this.tank = tank;
        this.bullet = bullet;
        this.mapModel = new MapModel(FunctionChooser.first);
    }

    public void keyEventRecognizer(KeyEvent event){
        // to something with the event
    }

    public MapModel getMap(){
        return this.mapModel;
    }
}
