package ships;

public class Pier extends Thread {
    private int pierId;
    private boolean isFree;
    private boolean isReady;
    private Mission mission;

    public Pier(int pierId, Mission mission) {  //mission is temp!
        this.pierId = pierId;
        this.isFree = true;
        this.mission = mission;
    }

    @Override
    public void run() {
        if(mission == Mission.LOAD_TO_SHIP) {

        }
    }

    public boolean isFree() {
        return isFree;
    }

    public int getPierId() {
        return pierId;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
