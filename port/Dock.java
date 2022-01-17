package port;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Dock {
    ReentrantLock locker;
    Condition condition;

    public Dock() {
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public void load(Port port, Ship ship, int containers) {
        locker.lock();
        try {
            System.out.println(ship.getShipName() + " has " + ship.getContainers() + " containers...");
            while (containers > port.getContainers()) {
                System.out.println("Not enough containers to load! " + ship.getShipName() + " is waiting...");
                System.out.println();
                condition.await();
            }
            port.setContainers(port.getContainers() - containers);
            ship.setContainers(ship.getContainers() + containers);
            System.out.println(ship.getShipName() + " is loading " + containers + " containers...");
            TimeUnit.MILLISECONDS.sleep(700);
            System.out.println(ship.getShipName() + " is completed to load " + containers + " containers!");
            System.out.println("Total amount of containers on the ship - " + ship.getContainers());
            System.out.println("Total amount of containers in the port - " + port.getContainers());
            System.out.println();

            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void unload(Port port, Ship ship, int containers) {
        locker.lock();
        try {
            System.out.println(ship.getShipName() + " has " + ship.getContainers() + " containers...");
            while (containers > ship.getContainers()) {
                System.out.println("Not enough containers to unload! " + ship.getShipName() + " is waiting...");
                condition.await();
            }
            port.setContainers(port.getContainers() + containers);
            ship.setContainers(ship.getContainers() - containers);
            System.out.println(ship.getShipName() + " is unloading " + containers + " containers...");
            TimeUnit.MILLISECONDS.sleep(700);
            System.out.println(ship.getShipName() + " is completed to unload " + containers + " containers!");
            System.out.println("Total amount of containers on the ship - " + ship.getContainers());
            System.out.println("Total amount of containers in the port - " + port.getContainers());
            System.out.println();

            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void serve(Port port, Dock dock, Ship ship, Task task) {
        if (task == Task.LOAD) {
           // dock.load(port, ship, new Random().nextInt(10) + 1);
            dock.load(port, ship, 10);
        }
        else if (task == Task.UNLOAD) {
            dock.unload(port, ship, 6);
            //dock.unload(port, ship, new Random().nextInt(10) + 1);
        }
    }
}
