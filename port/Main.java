package port;

public class Main {

    public static void main(String[] args) {
        Port port = Port.getInstance();

        new Ship(1, Mission.LOAD,  10).start();
        new Ship(2, Mission.UNLOAD,  10).start();
        new Ship(3, Mission.LOAD,  10).start();
        new Ship(4, Mission.UNLOAD,  10).start();
    }
}
