package port;

import java.util.Random;

public class Ship extends Thread {
    private int containers;
    private String shipName;
    private Task task;
    Dock dock;
    Port port;

    public Ship(int containers, String shipName, Dock dock, Port port, Task task) {
        if(containers > 0) {
            this.containers = containers;
        }
        else {
            System.out.println("Error! Number of containers has to be more than 0 in tne ship!");
        }
        this.dock = dock;
        this.port = port;
        this.task = task;
        this.shipName = shipName;
    }

    public int getContainers() {
        return containers;
    }

    public void setContainers(int containers) {
        this.containers = containers;
    }

    public String getShipName() {
        return shipName;
    }

    @Override
    public void run() {
        dock.serve(port, dock,this, task);
    }
}
