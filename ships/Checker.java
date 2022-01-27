package ships;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Checker extends TimerTask {

    private final double maxLoadFactor = 0.75;
    private final double minLoadFactor = 0.25;

    private Port port = Port.getInstance();
    private LogisticDepartment logisticDepartment = port.getLogisticDepartment();

    private Timer timer;
    private AtomicInteger numberOfContainers;
    private ArrayList<Ship> ships;

    public Checker(Timer timer, AtomicInteger numberOfContainers, ArrayList<Ship> ships) {
        this.timer = timer;
        this.numberOfContainers = numberOfContainers;
        this.ships = ships;
    }

    @Override
    public void run() {

        if(ships.stream().map(Ship::isServed).allMatch(x -> x == true)) {
            System.out.println("All ships are served!");
            timer.cancel();
            timer.purge();
            System.out.println(Thread.currentThread().getName());
            return;
        }

        double capacity =  (double)numberOfContainers.get() / port.getPORT_MAX_CAPACITY();

        if(capacity >= maxLoadFactor || logisticDepartment.isOverflow()) {
            numberOfContainers.set(0);
            System.out.println("Checker has removed containers. Current number: " + numberOfContainers);
            port.getLogisticDepartment().setOverflow(false);
        } else if (capacity <= minLoadFactor || logisticDepartment.isShortage()) {
            numberOfContainers.set(port.getPORT_MAX_CAPACITY());
            System.out.println("Checker has added containers. Current number: " + numberOfContainers);
            logisticDepartment.setShortage(false);
        }
    }
}
