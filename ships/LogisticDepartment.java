package ships;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticDepartment {
    private final float loadFactor = 0.75F;
    private Port port;
    private ReentrantLock locker;
    private Condition condition;

    public LogisticDepartment(Port port) {
        this.port = port;
        this.locker = new ReentrantLock();
        this.condition = locker.newCondition();
    }

    public void getContainers(int containersAmount) {
        locker.lock();
        try {
            while (containersAmount < port.getCurrentContainersAmount()) {
                condition.await();
            }
            port.setCurrentContainersAmount(port.getCurrentContainersAmount() - containersAmount);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void putContainers(int containersAmount) {
        locker.lock();
        try {
            while (containersAmount + port.getCurrentContainersAmount() > port.getPortMaxCapacity()) {
                condition.await();
            }
            port.setCurrentContainersAmount(port.getCurrentContainersAmount() + containersAmount);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}
