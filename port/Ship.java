package port;

public class Ship extends Thread {
    private Port port;
    private Mission mission;

    private int shipId;
    private int containers;

    public Ship(int shipId, Mission mission, int containers) {
        if (containers > 0) {
            this.shipId = shipId;
            this.mission = mission;
            this.containers = containers;
            this.port = Port.getInstance();
        } else {
            System.out.println("Error! Number of containers has to be more than 0 in tne ship!");
        }
    }

    @Override
    public void run() {
        switch (mission) {
            case LOAD -> port.getLogisticDepartment().loadToShip(this, 10);
            case UNLOAD -> port.getLogisticDepartment().unloadFromShip(this, 6);
        }
    }

    public int getContainers() {
        return containers;
    }

    public void setContainers(int containers) {
        this.containers = containers;
    }

    public int getShipId() {
        return shipId;
    }

}
