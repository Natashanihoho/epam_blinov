package port;

public final class Port {
    private static Port port;
    private int containers;

    public Port(int containers) {
        if(containers > 0) {
            this.containers = containers;
        }
        else {
            System.out.println("Error! Number of containers has to be more than 0 in tne port!");
        }
    }

    public static Port getPort(int containers) {
        if (port == null) {
            port = new Port(containers);
        }
        return port;
    }

    public int getContainers() {
        return containers;
    }

    public void setContainers(int containers) {
        this.containers = containers;
    }

    @Override
    public String toString() {
        return "Port: " + containers + " docks";
    }
}
