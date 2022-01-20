package ships;

public class Pier {
    private int pierId;
    private boolean isFree;
    private boolean isReady;

    public Pier(int pierId) {
        this.pierId = pierId;
        this.isFree = true;
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
