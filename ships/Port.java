package ships;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private static Port instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static final ReentrantLock locker = new ReentrantLock();

    private final LogisticDepartment logisticDepartment;
    private final int PORT_MAX_CAPACITY = 20;
    private final int PIERS_AMOUNT = 3;

    private int currentContainersAmount = 9;
    private Queue<Pier> freePiers;

    private Port() {

        this.freePiers = createPiersQueue();
        this.logisticDepartment = new LogisticDepartment(freePiers);
    }

    public static Port getInstance() {

        if(!isCreated.get()) {
            locker.lock();
            try {
                if(instance == null) {
                    instance = new Port();
                    isCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    public Queue<Pier> createPiersQueue() {

        freePiers = new ArrayDeque<>();
        for (int i = 0; i < PIERS_AMOUNT; i++) {
            freePiers.add(new Pier(i+1));
        }

        return freePiers;
    }

    public Queue<Pier> getPiers() {
        return freePiers;
    }

    public int getCurrentContainersAmount() {

        return currentContainersAmount;
    }

    public void setCurrentContainersAmount(int currentContainersAmount) {

        this.currentContainersAmount = currentContainersAmount;
    }

    public int getPORT_MAX_CAPACITY() {

        return PORT_MAX_CAPACITY;
    }

    public int getPIERS_AMOUNT() {

        return PIERS_AMOUNT;
    }

    public LogisticDepartment getLogisticDepartment() {

        return logisticDepartment;
    }
}
