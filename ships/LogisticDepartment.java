package ships;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticDepartment {

    private final ReentrantLock locker = new ReentrantLock();

    private Semaphore semaphore = new Semaphore(3, true);
    private AtomicInteger numberOfContainers;

    private boolean isShortage;
    private boolean isOverflow;

    public LogisticDepartment(AtomicInteger numberOfContainers) {

        this.numberOfContainers = numberOfContainers;

    }

    public Pier takePear(Ship ship) throws RuntimeException {

        while (!semaphore.tryAcquire());

        for (Pier resource : Port.getInstance().getPierList()) {
            if (!resource.isBusy()) {
                resource.setBusy(true);
                System.out.println("Ship#" + ship.getShipId() + " has taken the pier#" + resource.getPierId());
                return resource;
            }
        }

        throw new RuntimeException("Exception! Cannot get access to pier");

    }

    public void serving(Ship ship, Action action, int containersNumber) {

        locker.lock();

        Port port = Port.getInstance();

        switch (action) {

            case UPLOAD_TO_SHIP:
                while (containersNumber > numberOfContainers.get()) {
                    isShortage = true;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                numberOfContainers.addAndGet(-containersNumber);
                System.out.println("Is uploaded to the ship#" + ship.getShipId() + ": " + containersNumber + " containers");
                break;

            case UNLOAD_FROM_SHIP:
                while(containersNumber + numberOfContainers.get() > port.getPORT_MAX_CAPACITY()) {
                    isOverflow = true;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                numberOfContainers.addAndGet(containersNumber);
                System.out.println("Is unloaded from the ship#" + ship.getShipId() + ": " + containersNumber + " containers");
                break;
        }

        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(4));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Current number of containers: " + port.getCurrentContainersAmount());

        locker.unlock();
    }

    public void releasePear(Ship ship, Pier pier) {

        pier.setBusy(false);
        System.out.println("Ship#" + ship.getShipId() + " has released the pier#" + pier.getPierId());
        semaphore.release();

    }

    public void setShortage(boolean shortage) {
        isShortage = shortage;
    }

    public void setOverflow(boolean overflow) {
        isOverflow = overflow;
    }

    public boolean isShortage() {
        return isShortage;
    }

    public boolean isOverflow() {
        return isOverflow;
    }
}
