import javax.swing.plaf.synth.SynthFormattedTextFieldUI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class RailwayNetwork {
    private final int numOfStations = 100;
    private final int maxDistanceBetweenStations = 70;
    private LinkedList<RailwayStation> stations;
    private LinkedList<RailwayConnection> connections;

    public RailwayNetwork() {
        stations = new LinkedList<>();
        connections = new LinkedList<>();
        for (int i = 0; i < numOfStations; i++) {
            stations.add(new RailwayStation("Railway station #" + (i)));
        }
        for (int i = 0; i < numOfStations; i++) {
            for (int j = i; j < numOfStations; j++) {
                if (i != j) {
                    int distance = (int) (Math.random() * getMaxDistanceBetweenStations()) + 1;
                    connections.add(new RailwayConnection(stations.get(i), stations.get(j), distance));
                }
            }
        }
    }

    public LinkedHashMap<RailwayConnection, Integer> createRoute(RailwayStation start, RailwayStation end) {
        int numberOfStops = (int) (Math.random() * 8) + 2;
        LinkedList<RailwayStation> copyOfStation = new LinkedList<>(stations);

        int checker = 0;
        for (int i = 0; i < connections.size() && checker < 1; i++) {
            if (connections.get(i).getStartStation() == start || connections.get(i).getEndStation() == end) {
                checker++;
                connections.remove(i);
            }
        }

        LinkedHashMap<RailwayConnection, Integer> route = new LinkedHashMap<>();
        int randomDestination = (int) (Math.random() * copyOfStation.size());
        RailwayStation station = null;

        for (int i = 0; i < copyOfStation.size(); i++) {
            if (i == randomDestination) {
                station = copyOfStation.get(i);
                break;
            }
        }
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).getStartStation() == start && connections.get(i).getEndStation() == station || connections.get(i).getStartStation() == station && connections.get(i).getEndStation() == start) {
                RailwayStation second;
                if (connections.get(i).getStartStation() == station) {
                    second = station;
                    connections.get(i).setStartStation(start);
                    connections.get(i).setEndStation(second);
                }
                route.put(connections.get(i), (int) connections.get(i).getDistance());
                station = connections.get(i).getEndStation();
                break;
            }
        }
        for (int i = 0; i < numberOfStops - 2; i++) {
            int randomSecondStation = (int) (Math.random() * copyOfStation.size());
            RailwayStation randStation = null;
            for (int k = 0; k < stations.size(); k++) {
                if (k == randomSecondStation) {
                    randStation = stations.get(k);
                    copyOfStation.remove(k);
                    break;
                }
            }
            for (int k = 0; k < connections.size(); k++) {
                if (connections.get(k).getStartStation() == station && connections.get(k).getEndStation() == randStation || connections.get(k).getStartStation() == randStation && connections.get(k).getEndStation() == station) {
                    RailwayStation second;
                    if (connections.get(k).getStartStation() == randStation) {
                        second = connections.get(k).getEndStation();
                        connections.get(k).setEndStation(connections.get(k).getStartStation());
                        connections.get(k).setStartStation(second);
                    }
                    route.put(connections.get(k), (int) connections.get(k).getDistance());
                    station = connections.get(k).getEndStation();
                    break;
                }
            }
        }
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).getStartStation() == station && connections.get(i).getEndStation() == end || connections.get(i).getStartStation() == end && connections.get(i).getEndStation() == station) {
                RailwayStation second;
                if (connections.get(i).getStartStation() == end) {
                    second = connections.get(i).getEndStation();
                    connections.get(i).setEndStation(connections.get(i).getStartStation());
                    connections.get(i).setStartStation(second);
                }
                route.put(connections.get(i), (int) connections.get(i).getDistance());
                break;
            }
        }
        return route;
    }
    public void printStations() {
        for (int i = 0; i < numOfStations; i++) {
            System.out.println(stations.get(i).getName());
        }
    }
    public void printConnections() {
        for (int i = 0; i < connections.size(); i++) {
            System.out.println(connections.get(i));
        }
    }
    public int getNumOfStations() {
        return numOfStations;
    }

    public LinkedList<RailwayStation> getStations() {
        return stations;
    }

    public int getMaxDistanceBetweenStations() {
        return maxDistanceBetweenStations;
    }

    public LinkedList<RailwayConnection> getConnections() {
        return connections;
    }

    public void setConnections(LinkedList<RailwayConnection> connections) {
        this.connections = connections;
    }
}