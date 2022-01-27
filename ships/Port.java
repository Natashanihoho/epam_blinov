package ships;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private static final int PORT_MAX_CAPACITY = 20;
    private static final int PIERS_AMOUNT = 3;

    private static final ReentrantLock locker = new ReentrantLock();

    private static Port instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);

    private static AtomicInteger currentContainersAmount = new AtomicInteger(9);
    private static LogisticDepartment logisticDepartment = new LogisticDepartment(currentContainersAmount);
    private static List<Pier> pierList;

    private Port() {
        this.pierList = createPiersList();
    }

    private List<Pier> createPiersList() {
        pierList = new ArrayList<>();
        for (int i = 0; i < PIERS_AMOUNT; i++) {
            pierList.add(new Pier(i+1));
        }
        return pierList;
    }

    public List<Pier> getPierList() {
        return pierList;
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

    public AtomicInteger getCurrentContainersAmount() {
        return currentContainersAmount;
    }

    public int getPORT_MAX_CAPACITY() {

        return PORT_MAX_CAPACITY;
    }

    public LogisticDepartment getLogisticDepartment() {

        return logisticDepartment;
    }
}
