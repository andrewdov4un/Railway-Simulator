public class RailwayStation {
    private final String name;
    private boolean busy;
    private int peopleToPick;
    private int peopleToLeave;
    private int cargoToPick;

    public RailwayStation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public int getPeopleToPick() {
        return peopleToPick;
    }

    public void setPeopleToPick(int peopleToPick) {
        this.peopleToPick = peopleToPick;
    }

    public int getCargoToPick() {
        return cargoToPick;
    }

    public void setCargoToPick(int cargoToPick) {
        this.cargoToPick = cargoToPick;
    }

    public int getPeopleToLeave() {
        return peopleToLeave;
    }

    public void setPeopleToLeave(int peopleToLeave) {
        this.peopleToLeave = peopleToLeave;
    }
}
