package ships;

public class Ship extends Thread {
    private int shipId;
    private Mission mission;
    private int containers;

    public Ship(int shipId, Mission mission, int containers) {
        if(containers > 0) {
            this.shipId = shipId;
            this.mission = mission;
            this.containers = containers;
        } else {
            System.out.println("Error! Number of containers has to be more than 0!");
        }
    }

    @Override
    public void run() {
        Port.getInstance().getLogisticDepartment().baseOnPear(this);
    }

    public int getShipId() {
        return shipId;
    }
}
