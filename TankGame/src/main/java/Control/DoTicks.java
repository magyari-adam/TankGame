package Control;

import static java.lang.Thread.sleep;

public class DoTicks implements Runnable {
    private GameServer gs;

    public DoTicks(GameServer gs) {
        this.gs = gs;
    }

    @Override
    public void run() {
        while(this.gs.getBullets().size()>0){
            this.gs.doTick();
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.gs.unReadyAll();
    }
}
