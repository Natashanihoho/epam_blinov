package port;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
       Port port = Port.getPort(9);
       Dock dock = new Dock();
       //new Ship(new Random().nextInt(10) + 1, "Ship#1", dock, port, Task.LOAD).start();
       //new Ship(new Random().nextInt(10) + 1, "Ship#2", dock, port, Task.UNLOAD).start();
       //new Ship(new Random().nextInt(10) + 1, "Ship#3", dock, port, Task.LOAD_UNLOAD).start();
       //new Ship(new Random().nextInt(10) + 1, "Ship#4", dock, port, Task.LOAD).start();
       //new Ship(new Random().nextInt(10) + 1, "Ship#5", dock, port, Task.UNLOAD).start();

        new Ship(10,"Ship#1", dock, port, Task.LOAD).start();
        new Ship(10, "Ship#2", dock, port, Task.UNLOAD).start();
        new Ship(10, "Ship#3", dock, port, Task.LOAD).start();
        new Ship(10, "Ship#4", dock, port, Task.UNLOAD).start();
    }
}
