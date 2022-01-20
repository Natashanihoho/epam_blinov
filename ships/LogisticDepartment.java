package ships;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticDepartment {
    private final float loadFactor = 0.75F;
    private ReentrantLock locker = new ReentrantLock();
    private Condition condition = locker.newCondition();
    private Queue<Pier> freePeers;

    public LogisticDepartment(Queue<Pier> freePeers) {
        this.freePeers = freePeers;
    }

    public void baseOnPear(Ship ship) {
        locker.lock();
        Port port = Port.getInstance();
        try {
            while (freePeers.isEmpty()) {
                System.out.println("Ship#" + ship.getShipId() + " is waiting for a free pier...");
                condition.await();
            }
            condition.signalAll();
            Pier pier = freePeers.element();
            System.out.println("Ship#" + ship.getShipId() + " is based in the pier#" + pier.getPierId());
            freePeers.remove();
            TimeUnit.SECONDS.sleep(new Random().nextInt(5) + 1);
            System.out.println("Ship#" + ship.getShipId() + " is released the pier#" + pier.getPierId());
            freePeers.add(pier);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }

    }
}
