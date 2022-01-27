package ships;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class Main {
    private static ArrayList<Ship> ships = new ArrayList<>();

    public static void main(String[] args) {

        ships.add(new Ship(1, getRandomAction(), getRandomNumberOfContainers()));
        ships.add(new Ship(2, getRandomAction(), getRandomNumberOfContainers()));
        ships.add(new Ship(3, getRandomAction(), getRandomNumberOfContainers()));
        ships.add(new Ship(4, getRandomAction(), getRandomNumberOfContainers()));
        for (Ship s:
             ships) {
            System.out.println(s);
        }

        for (Ship ship:
             ships) {
            ship.start();
        }

        Timer timer = new Timer();
        Checker checker = new Checker(timer, Port.getInstance().getCurrentContainersAmount(), ships);
        timer.schedule(checker, 1000, 1000);

    }

    private static Action getRandomAction() {
        int pick = new Random().nextInt(Action.values().length);
        return Action.values()[pick];
    }

    private static int getRandomNumberOfContainers() {
        int pick = new Random().nextInt(20) + 1;
        return pick;
    }
}
