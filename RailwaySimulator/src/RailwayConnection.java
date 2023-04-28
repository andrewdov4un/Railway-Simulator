public class RailwayConnection {
    private RailwayStation startStation;
    private RailwayStation endStation;
    private TrainSet train;
    private boolean busy;
    private final double distance;

    public RailwayConnection(RailwayStation startStation, RailwayStation endStation, int distance) {
        this.startStation = startStation;
        this.endStation = endStation;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Distance between: " + getStartStation().getName() + " <-> " + getEndStation().getName() + " - " + getDistance() + "km";
    }

    public RailwayStation getStartStation() {
        return startStation;
    }

    public RailwayStation getEndStation() {
        return endStation;
    }

    public void setStartStation(RailwayStation startStation) {
        this.startStation = startStation;
    }

    public void setEndStation(RailwayStation endStation) {
        this.endStation = endStation;
    }

    public TrainSet getTrain() {
        return train;
    }

    public void setTrain(TrainSet train) {
        this.train = train;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
