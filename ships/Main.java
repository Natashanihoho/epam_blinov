package ships;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Port port = Port.getInstance();

        /*ExecutorService executorService = Executors.newFixedThreadPool(port.getPIERS_AMOUNT());
        executorService.execute(new Ship(1, Mission.LOAD_TO_SHIP, 5));
        executorService.execute(new Ship(2, Mission.LOAD_TO_SHIP, 5));
        executorService.execute(new Ship(3, Mission.LOAD_TO_SHIP, 5));
        executorService.execute(new Ship(4, Mission.LOAD_TO_SHIP, 5));*/
        for (int i = 0; i < 5; i++) {
            new Ship(i+1, Mission.LOAD_TO_SHIP, 5).start();
        }
    }
}
