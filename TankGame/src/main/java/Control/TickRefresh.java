package Control;

import static java.lang.Thread.sleep;

public class TickRefresh implements Runnable {
    private GameServer gs;

    public TickRefresh(GameServer gs) {
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
    }
}
