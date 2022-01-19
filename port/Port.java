package port;

import java.util.concurrent.atomic.AtomicBoolean;

public final class Port {
    private static Port instance;
    private static AtomicBoolean isCreatedPort = new AtomicBoolean(false);

    private LogisticDepartment logisticDepartment;
    private int currentContainersAmount;

    private Port() {
        currentContainersAmount = 9;
        logisticDepartment = new LogisticDepartment();
    }

    public static Port getInstance() {
        if(!isCreatedPort.get()) {
            if (instance == null) {
                instance = new Port();
                isCreatedPort.set(true);
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
