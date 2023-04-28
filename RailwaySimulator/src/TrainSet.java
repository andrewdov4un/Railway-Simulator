import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class TrainSet implements Runnable {
    private int currentRailroadCars;
    private final RailwayNetwork network;
    static boolean showMovement = true;
    private int weightOfCargo;
    private static int nextId = 0;
    private final int id;
    private Locomotive locomotive;
    private RailwayStation homeRailwayStation;
    private RailwayStation destinationRailwayStation;
    private LinkedHashMap<RailwayConnection, Integer> route;
    private final int maxRailroadCars;
    private MyWindow myWindow;

    private LinkedList<RailroadCars> RailroadCars;
    private LinkedList<FreightRailroadCars> FreightRailroadCars;

    public TrainSet(Locomotive locomotive, RailwayStation home, RailwayStation destination, RailwayNetwork network, MyWindow myWindow) {
        id = nextId;
        nextId++;
        this.locomotive = locomotive;
        homeRailwayStation = home;
        destinationRailwayStation = destination;
        RailroadCars = new LinkedList<>();
        FreightRailroadCars = new LinkedList<>();
        maxRailroadCars = 20;
        this.network = network;
        this.myWindow = myWindow;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Train #" + id + " started a new route.");
            route = network.createRoute(homeRailwayStation, destinationRailwayStation);
            printRoute();
            moveTrain();
        }
    }

    public void moveTrain() {
        for (Map.Entry<RailwayConnection, Integer> entry : route.entrySet()) {
            if (entry.getKey().isBusy()) {
                while (true) {
                    try {
                        if (!entry.getKey().isBusy()) {
                            break;
                        } else {
                            if (showMovement) {
                                System.out.println("Train #" + id + " waits");
                                myWindow.setjTextArea("Train #" + id + " waits");
                                myWindow.setjTextArea("\n");
                            }
                        }
                        synchronized (this) {
                            wait();
                        }
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            entry.getKey().setBusy(true);
            RailwayConnection connection = entry.getKey();
            RailwayStation startStation = entry.getKey().getStartStation();
            RailwayStation endStation = entry.getKey().getEndStation();
            double distance = entry.getKey().getDistance();
            int time = (int) Math.ceil((double) distance / locomotive.getSpeed()) * 3600; // time in seconds
            if (showMovement) {
                System.out.println("Train #" + id + " departs from " + startStation.getName() + " towards " + endStation.getName() + ". Distance: " + distance + " km.");
                myWindow.setjTextArea("Train #" + id + " departs from " + startStation.getName() + " towards " + endStation.getName() + ". Distance: " + distance + " km.\n");
            }
            double distanceLeft = distance;
            double distanceTraveled = 0;
            double percent = 0;
            while (true) {
                int random = (int) (Math.random() * 2) + 1;
                if (random == 1) {
                    locomotive.setSpeed((int) (locomotive.getSpeed() + locomotive.getSpeed() * 0.03));
                } else {
                    locomotive.setSpeed((int) (locomotive.getSpeed() - locomotive.getSpeed() * 0.03));
                }
                if (locomotive.getSpeed() > 200) {
                    try {
                        throw new RailroadHazard(id);
                    } catch (RailroadHazard e) {
                        System.out.println("The train #" + id + " exceeded speed of 200 km/h.");
                        myWindow.setjTextArea("The train #" + id + " exceeded speed of 200 km/h.\n");
                        locomotive.setSpeed(180);
                    }
                } else if (locomotive.getSpeed() < 50) locomotive.setSpeed(50);
                distanceTraveled = distance - distanceLeft;
                percent = (distanceTraveled / distance) * 100;
                if (distanceLeft <= 0) {
                    break;
                } else {
                    distanceLeft -= locomotive.getSpeed() * 2.0 / 3600.0;
                    if (showMovement) {
                        System.out.println("Train #" + id + " passed: " + percent + " % moving towards -> " + entry.getKey().getEndStation().getName());
                        String formattedNumber = String.format("%.2f", percent);
                        myWindow.setjTextArea("Train #" + id + " passed: " + formattedNumber + " % moving towards -> " + entry.getKey().getEndStation().getName() + "\n");
                    }
                    try {
                        Thread.sleep(2000); // simulate real-time movement with a delay of 2 seconds
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        while (true) {
            try {
                if (entry.getKey().getEndStation().isBusy()) {
                    if (showMovement) {
                        System.out.println("Waiting till " + entry.getKey().getEndStation().getName() + " will be free.");
                        myWindow.setjTextArea("Waiting till " + entry.getKey().getEndStation().getName() + " will be free.");
                    }
                    Thread.sleep(2000);
                } else break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            int totalPassengers = 0;
            for (RailroadCars cars : RailroadCars) {
                if (cars instanceof PassengerRailroadCar) {
                    totalPassengers += ((PassengerRailroadCar) cars).getNumberOfPassengers();
                }
            }
            entry.getKey().getEndStation().setPeopleToPick((int) (Math.random() * 50) + 5);
            if (totalPassengers > 10) {
                entry.getKey().getEndStation().setPeopleToLeave((int) (Math.random() * totalPassengers / 2) + totalPassengers / 3);
                for (RailroadCars cars : RailroadCars) {
                    if (cars instanceof PassengerRailroadCar) {
                        if (((PassengerRailroadCar) cars).getNumberOfPassengers() >= entry.getKey().getEndStation().getPeopleToLeave()) {
                            int leftPeople = ((PassengerRailroadCar) cars).getNumberOfPassengers() - entry.getKey().getEndStation().getPeopleToLeave();
                            ((PassengerRailroadCar) cars).setNumberOfPassengers(((PassengerRailroadCar) cars).getNumberOfPassengers() - entry.getKey().getEndStation().getPeopleToLeave());
                            entry.getKey().getEndStation().setPeopleToLeave(entry.getKey().getEndStation().getPeopleToLeave() - leftPeople);
                        } else {
                            int leftPeople = ((PassengerRailroadCar) cars).getNumberOfPassengers();
                            ((PassengerRailroadCar) cars).setNumberOfPassengers(0);
                        }
                    }
                }
                System.out.println(entry.getKey().getEndStation().getPeopleToLeave() + " people left train.");
                myWindow.setjTextArea(entry.getKey().getEndStation().getPeopleToLeave() + " people left train.");
            }
            for (RailroadCars cars : RailroadCars) {
                if (cars instanceof PassengerRailroadCar && cars.getNumberOfSeats() >= ((PassengerRailroadCar) cars).getNumberOfPassengers()) {
                    int peopleCarCanTake = cars.getNumberOfSeats() - ((PassengerRailroadCar) cars).getNumberOfPassengers();
                    if (peopleCarCanTake < entry.getKey().getEndStation().getPeopleToPick()) {
                        ((PassengerRailroadCar) cars).setNumberOfPassengers(cars.getNumberOfSeats());
                        System.out.println("Railroad car #" + cars.getId() + " is full!");
                    } else {
                        ((PassengerRailroadCar) cars).setNumberOfPassengers(((PassengerRailroadCar) cars).getNumberOfPassengers() + entry.getKey().getEndStation().getPeopleToPick());
                        System.out.println("Train #" + id + " loaded " + ((PassengerRailroadCar) cars).getNumberOfPassengers() + entry.getKey().getEndStation().getPeopleToPick() + " people on railroad car #" + cars.getId());
                    }
                }
            }
            System.out.println("Entering " + entry.getKey().getEndStation().getName());
            myWindow.setjTextArea("Entering " + entry.getKey().getEndStation().getName());
            entry.getKey().getEndStation().setBusy(true);
            if (entry.getKey().getEndStation() == destinationRailwayStation) {
                System.out.println("Train #" + id + " finished it's route. Waiting 30 seconds and returning home.");
                myWindow.setjTextArea("Train #" + id + " finished it's route. Waiting 30 seconds and returning home.");
                Thread.sleep(30000);
                RailwayStation second = destinationRailwayStation;
                setDestinationRailwayStation(getHomeRailwayStation());
                setHomeRailwayStation(second);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

    public LinkedHashMap<RailwayConnection, Integer> createRouteByHand(int stations, int id_start, int id_end) {
        LinkedHashMap<RailwayConnection, Integer> route = new LinkedHashMap<>();
        if (stations == 1) {
            RailwayStation start = null;
            RailwayStation end = null;
            int checker = 0;
            for (int i = 0; i < network.getStations().size() && checker < 1; i++) {
                if (i == id_start) {
                    start = network.getStations().get(i);
                    checker++;
                }
                else if (i == id_end) {
                    end = network.getStations().get(i);
                    checker++;
                }
            }
            for (RailwayConnection connection : network.getConnections()) {
                if (connection.getStartStation() == start && connection.getEndStation() == end || connection.getStartStation() == end && connection.getEndStation() == start) {
                    RailwayStation second;
                    if (connection.getStartStation() == end) {
                        second = connection.getEndStation();
                        connection.setEndStation(connection.getStartStation());
                        connection.setStartStation(second);
                    }
                    route.put(connection, (int) connection.getDistance());
                    break;
                }
            }
        }
        return route;
    }

    // add railroad cars but not more than allowed
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public void addRailroadCar(FreightRailroadCars car) {
        int hypoteticalWeightOfCargo = getWeightOfCargo() + car.getGrossWeight();
        if (maxRailroadCars > currentRailroadCars && hypoteticalWeightOfCargo <= locomotive.getMaxWeight()) {
            FreightRailroadCars.add(car);
            currentRailroadCars++;
            weightOfCargo += car.getGrossWeight();
        } else System.out.println(ANSI_RED + "Something went wrong! \n" + currentRailroadCars + " / " + maxRailroadCars + " attached.\n" + weightOfCargo + " / " + locomotive.getMaxWeight() + " loaded." + ANSI_RESET);
    }
    public void addRailroadCar(RailroadCars car) {
        int hypoteticalWeightOfCargo = getWeightOfCargo() + car.getNetWeight();
        if (maxRailroadCars > currentRailroadCars && hypoteticalWeightOfCargo <= locomotive.getMaxWeight()) {
            RailroadCars.add(car);
            currentRailroadCars++;
            weightOfCargo += car.getNetWeight();
        } else System.out.println(ANSI_RED + "Something went wrong! \n" + currentRailroadCars + " / " + maxRailroadCars + " attached.\n" + weightOfCargo + " / " + locomotive.getMaxWeight() + " loaded." + ANSI_RESET);
    }
    // removes railroad car
    public void removeRailroadCar(int id) {
        RailroadCars.removeIf(car -> car.getId() == id);
    }

    // print whole train
    public void printTrain() {
        myWindow.jTextArea.append("Train id: " + id);
        myWindow.jTextArea.append(String.valueOf(locomotive));
        myWindow.jTextArea.append("Current weight of cargo: " + getWeightOfCargo());
        for (RailroadCars i : RailroadCars) {
            myWindow.jTextArea.append(String.valueOf(i));
        }
        for (FreightRailroadCars i : FreightRailroadCars) {
            myWindow.jTextArea.append(String.valueOf(i));
        }
    }
    public String printTrainAppState() {
        return "Train #" + id + "\nLocomotive #" + locomotive.getId() + "\nCurrent weight of cargo: " + getWeightOfCargo();
    }
    public void printRoute() {
        for (Map.Entry<RailwayConnection, Integer> entry : route.entrySet()) {
            RailwayConnection connection = entry.getKey();
            int distance = entry.getValue();
            System.out.println("ROUTE. Train #" + id + " " + connection.getStartStation().getName() + " -> " + connection.getEndStation().getName() + " (" + distance + " km)");
        }
    }
    public String printRailroadCars(int i) {
            return "Railroad Car #" + RailroadCars.get(i).getId() + " of Train #" + id + "\nClass: " + RailroadCars.get(i).getClass() + "\nShipper: " + RailroadCars.get(i).getShipper() + "\nNet Weight: " + RailroadCars.get(i).getNetWeight() + "\nNumber of seats: " + RailroadCars.get(i).getNumberOfSeats();
    }
    public String printFreightRailroadCars(int i) {
        return "Freight Railroad Car #" + FreightRailroadCars.get(i).getId() + " of Train #" + id + "\nShipper: " + FreightRailroadCars.get(i).getShipper() + "\nNet Weight: " + FreightRailroadCars.get(i).getNetWeight() + "\nGross Weight: " + FreightRailroadCars.get(i).getGrossWeight();
    }
    public int totalRouteLength(LinkedHashMap<RailwayConnection, Integer> route) {
        int totalLength = 0;
        for (Map.Entry<RailwayConnection, Integer> entry : route.entrySet()) {
            totalLength += entry.getKey().getDistance();
        }
        return totalLength;
    }
    public int totalWeightOfCargo() {
        int totalWeight = 0;
        for (int i = 0; i < FreightRailroadCars.size(); i++) {
            totalWeight += FreightRailroadCars.get(i).getGrossWeight();
        }
        return totalWeight;
    }
    // getters and setters
    public int getWeightOfCargo() {
        return weightOfCargo;
    }

    public void setWeightOfCargo(int weightOfCargo) {
        this.weightOfCargo = weightOfCargo;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public LinkedHashMap<RailwayConnection, Integer> getRoute() {
        return route;
    }

    public void setRoute(LinkedHashMap<RailwayConnection, Integer> route) {
        this.route = route;
    }
    public int getId() {
        return id;
    }

    public boolean isShowMovement() {
        return showMovement;
    }

    public void setHomeRailwayStation(RailwayStation homeRailwayStation) {
        this.homeRailwayStation = homeRailwayStation;
    }

    public void setDestinationRailwayStation(RailwayStation destinationRailwayStation) {
        this.destinationRailwayStation = destinationRailwayStation;
    }

    public void setShowMovement(boolean showMovement) {
        this.showMovement = showMovement;
    }
    public RailwayStation getHomeRailwayStation() {
        return homeRailwayStation;
    }
    public RailwayStation getDestinationRailwayStation() {
        return destinationRailwayStation;
    }

    public int getMaxRailroadCars() {
        return maxRailroadCars;
    }

    public int getCurrentRailroadCars() {
        return currentRailroadCars;
    }

    public void setCurrentRailroadCars(int currentRailroadCars) {
        this.currentRailroadCars = currentRailroadCars;
    }
    public LinkedList<RailroadCars> getRailroadCars() {
        return RailroadCars;
    }

    public void setRailroadCars(LinkedList<RailroadCars> railroadCars) {
        RailroadCars = railroadCars;
    }

    public LinkedList<FreightRailroadCars> getFreightRailroadCars() {
        return FreightRailroadCars;
    }

    public void setFreightRailroadCars(LinkedList<FreightRailroadCars> freightRailroadCars) {
        FreightRailroadCars = freightRailroadCars;
    }
}