package Control;

import static java.lang.Thread.sleep;

/**
 * Client side frame redraw timer.
 */
public class ClientRefresh implements Runnable {
    private Client client;

    public ClientRefresh(Client client) {
        this.client=client;
    }

    @Override
    public void run(){
        while(!this.client.isEnd()){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.client.refreshFromServer();
        }
    }
}
