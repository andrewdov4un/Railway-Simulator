public class RailroadHazard extends Exception {
    public RailroadHazard(int id) {
        super("Train # " + id + " has exceeded the speed limit of 200km/h.");
    }
}
