package ships;

public class Pier {

    private final int pierId;
    private boolean isBusy = false;

    public Pier(int pierId) {
        this.pierId = pierId;
    }

    public int getPierId() {
        return pierId;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public boolean isBusy() {
        return isBusy;
    }
}
