package ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Port {
    private static Port instance;
    private final int portMaxCapacity;
    private final int piersAmount;
    private int currentContainersAmount;
    List<Pier> piers;

    private Port(int portMaxCapacity, int piersAmount, int currentContainersAmount) {
        this.portMaxCapacity = portMaxCapacity;
        this.piersAmount = piersAmount;
        this.currentContainersAmount = currentContainersAmount;
        this.piers = createPiersList();
    }

    public synchronized static Port getInstance(int portMaxCapacity, int piersAmount, int currentContainersAmount) {
        if(instance == null) {
            instance = new Port(portMaxCapacity, piersAmount, currentContainersAmount);
        }
        return instance;
    }

    public List<Pier> createPiersList() {
        piers = new ArrayList<>();
        piers.add(new Pier(1, Mission.LOAD_TO_SHIP));
        piers.add(new Pier(2, Mission.UNLOAD_FROM_SHIP));
        piers.add(new Pier(3, Mission.LOAD_TO_SHIP));
        /*for (int i = 0; i < piersAmount; i++) {
            piers.add(new Pier(i+1));
        }*/
        return Collections.unmodifiableList(piers);
    }

    public List<Pier> getPiers() {
        return piers;
    }

    public int getCurrentContainersAmount() {
        return currentContainersAmount;
    }

    public void setCurrentContainersAmount(int currentContainersAmount) {
        this.currentContainersAmount = currentContainersAmount;
    }

    public int getPortMaxCapacity() {
        return portMaxCapacity;
    }
}
