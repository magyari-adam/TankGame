package Control;

import static java.lang.Thread.sleep;

public class ServerRefresh implements Runnable {
    private GameServer gs;

    public ServerRefresh(GameServer gs) {
        this.gs = gs;
    }

    @Override
    public void run() {
        while(true){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.gs.refresh();
        }
    }
}
