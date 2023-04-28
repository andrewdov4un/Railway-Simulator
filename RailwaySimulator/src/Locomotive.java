public class Locomotive {
    private static int nextId = 0;
    private final int id;
    private final int maxRailroadCars = 15;
    private final int maxWeight = 20000;
    private int speed = 150;
    private boolean busy = false;
    private int numOfCarsThatNeedElectricity;

    public Locomotive() {
        id = nextId;
        nextId++;
    }
    @Override
    public String toString() {
        return "\n --------------------" + "\n Info about locomotive" + "\n ID: " + id + "\n Busy: " + busy + "\n Max Railroad Cars: " + getMaxRailroadCars() + "\n Max Weight: " + getMaxWeight() +  "\n --------------------";
    }
    public int getMaxRailroadCars() {
        return maxRailroadCars;
    }
    public int getMaxWeight() {
        return maxWeight;
    }

    public int getId() {
        return id;
    }
    public int getNumOfCarsThatNeedElectricity() {
        return numOfCarsThatNeedElectricity;
    }

    public void setNumOfCarsThatNeedElectricity(int numOfCarsThatNeedElectricity) {
        this.numOfCarsThatNeedElectricity = numOfCarsThatNeedElectricity;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Locomotive.nextId = nextId;
    }

}