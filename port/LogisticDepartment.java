package port;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticDepartment {
    private ReentrantLock locker;
    private Condition condition;

    public LogisticDepartment() {
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public void loadToShip(Ship ship, int containers) {
        locker.lock();
        Port port = Port.getInstance();
        try {
            System.out.println("Ship#" + ship.getShipId() + " has " + ship.getContainers() + " containers...");
            while (containers > port.getCurrentContainersAmount()) {
                System.out.println("Not enough containers to load! " + "Ship#" + ship.getShipId() + " is waiting...");
                System.out.println();
                condition.await();
            }
            port.setCurrentContainersAmount(port.getCurrentContainersAmount() - containers);
            ship.setContainers(ship.getContainers() + containers);

            System.out.println("Ship#" + ship.getShipId() + " is loading " + containers + " containers...");

            TimeUnit.MILLISECONDS.sleep(700);

            System.out.println("Ship#" + ship.getShipId() + " is completed to load " + containers + " containers!");
            System.out.println("Total amount of containers on the ship - " + ship.getContainers());
            System.out.println("Total amount of containers in the port - " + port.getCurrentContainersAmount());
            System.out.println();

            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void unloadFromShip(Ship ship, int containers) {
        locker.lock();
        Port port = Port.getInstance();
        try {
            System.out.println("Ship#" + ship.getShipId() + " has " + ship.getContainers() + " containers...");
            while (containers > ship.getContainers()) {
                System.out.println("Not enough containers to unload! " + "Ship#" + "Ship#" + ship.getShipId() + " is waiting...");
                condition.await();
            }
            port.setCurrentContainersAmount(port.getCurrentContainersAmount() + containers);
            ship.setContainers(ship.getContainers() - containers);

            System.out.println("Ship#" + ship.getShipId() + " is unloading " + containers + " containers...");

            TimeUnit.MILLISECONDS.sleep(700);

            System.out.println("Ship#" + ship.getShipId() + " is completed to unload " + containers + " containers!");
            System.out.println("Total amount of containers on the ship - " + ship.getContainers());
            System.out.println("Total amount of containers in the port - " + port.getCurrentContainersAmount());
            System.out.println();

            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

}
