package port;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class Port {
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static Port instance;
    private static AtomicBoolean isCreatedPort = new AtomicBoolean(false);

    private final LogisticDepartment logisticDepartment;

    private int currentContainersAmount;

    private Port() {
        currentContainersAmount = 9;
        logisticDepartment = new LogisticDepartment();
    }

    public static Port getInstance() {
        if(!isCreatedPort.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Port();
                    isCreatedPort.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public int getCurrentContainersAmount() {
        return currentContainersAmount;
    }

    public void setCurrentContainersAmount(int currentContainersAmount) {
        this.currentContainersAmount = currentContainersAmount;
    }

    public LogisticDepartment getLogisticDepartment() {
        return logisticDepartment;
    }
}
