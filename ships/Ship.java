package ships;

public class Ship extends Thread {

    private final int shipId;
    private Action action;
    private int containers;
    private boolean isServed = false;

    public Ship(int shipId, Action action, int containers) {
        this.shipId = shipId;
        this.action = action;
        this.containers = containers;
    }

    @Override
    public void run() {

        Pier pier = null;

        try {
            pier = Port.getInstance().getLogisticDepartment().takePear(this);
            Port.getInstance().getLogisticDepartment().serving(this, action, containers);
        } catch (RuntimeException e) {
            System.err.println("Ship #" + this.getShipId() + " lost ->" + e.getMessage());
        } finally {
            if (pier != null) {
                isServed = true;
                Port.getInstance().getLogisticDepartment().releasePear(this, pier);
            }
        }

    }

    public int getShipId() {
        return shipId;
    }

    public boolean isServed() {
        return isServed;
    }

    @Override
    public String toString() {
        return "Ship#" + shipId + ": action = " + action.toString() + " containers = " + containers;
    }
}
