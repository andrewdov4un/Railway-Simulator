public abstract class RailroadCars {
    private static int nextId = 1;
    private int id = nextId;
    private String shipper;
    private int netWeight;
    final private int numberOfSeats;

    public RailroadCars(String shipper, int netWeight, int numberOfSeats) {
        id = nextId;
        nextId++;
        this.shipper = shipper;
        this.netWeight = netWeight;
        this.numberOfSeats = numberOfSeats;
    }
    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public int getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(int netWeight) {
        this.netWeight = netWeight;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }


    public int getId() {
        return id;
    }
}





class PassengerRailroadCar extends RailroadCars {
    private final int Id = getId();
    private int numberOfPassengers;
    private String classOfCar;
    public PassengerRailroadCar(String shipper, int netWeight, int numberOfSeats, String classOfCar) {
        super(shipper, netWeight, numberOfSeats);
        this.classOfCar = classOfCar;
    }
    @Override
    public String toString() {
        return "\n --------------------" + "\n ID: " + Id + "\n Shipper: " + getShipper() + "\n Net weight: " + getNetWeight() + "\n Class: " + getClassOfCar() + "\n Number of seats: " + getNumberOfSeats() + "\n Passengers: " + getNumberOfPassengers() + "\n --------------------";
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getClassOfCar() {
        return classOfCar;
    }

    public void setClassOfCar(String classOfCar) {
        this.classOfCar = classOfCar;
    }
}






class RailroadPostOffice extends RailroadCars {
    private final int Id = getId();
    private int numberOfPostmen;
    private int numberOfLetters;

    public RailroadPostOffice(String shipper, int netWeight, int numberOfSeats, int numberOfPostmen, int numberOfLetters) {
        super(shipper, netWeight, numberOfSeats);
        this.numberOfPostmen = numberOfPostmen;
        this.numberOfLetters = numberOfLetters;
    }
    @Override
    public String toString() {
        return "\n --------------------" + "\n ID: " + Id + "\n Shipper: " + getShipper() + "\n Net weight: " + getNetWeight() + "\n Number of seats: " + getNumberOfSeats() + "\n Postmen: " + getNumberOfPostmen() + "\n Letters: " + getNumberOfLetters() + "\n --------------------";
    }
    public int getNumberOfPostmen() {
        return numberOfPostmen;
    }

    public void setNumberOfPostmen(int numberOfPostmen) {
        this.numberOfPostmen = numberOfPostmen;
    }

    public int getNumberOfLetters() {
        return numberOfLetters;
    }

    public void setNumberOfLetters(int numberOfLetters) {
        this.numberOfLetters = numberOfLetters;
    }
}






class RailroadBaggageCar extends RailroadCars{
    private final int Id = getId();
    private int currentNumberOfBags;
    private final int maxNumberOfBags;

    public RailroadBaggageCar(String shipper, int netWeight, int numberOfSeats, int maxNumberOfBags) {
        super(shipper, netWeight, numberOfSeats);
        this.maxNumberOfBags = maxNumberOfBags;
    }
    @Override
    public String toString() {
        return "\n --------------------" + "\n ID: " + Id + "\n Shipper: " + getShipper() + "\n Net weight: " + getNetWeight() + "\n Max number of bags: " + getMaxNumberOfBags() + "\n Current number of bags:" + getCurrentNumberOfBags() + "\n --------------------";
    }
    public int getCurrentNumberOfBags() {
        return currentNumberOfBags;
    }

    public void setCurrentNumberOfBags(int currentNumberOfBags) {
        this.currentNumberOfBags = currentNumberOfBags;
    }

    public int getMaxNumberOfBags() {
        return maxNumberOfBags;
    }
}





class RailroadMailCar extends RailroadCars{
    private final int Id = getId();
    private int numberOfLetters;
    private int toDeliverOnThisStation;

    public RailroadMailCar(String shipper, int netWeight, int numberOfSeats) {
        super(shipper, netWeight, numberOfSeats);
    }

    @Override
    public String toString() {
        return "\n --------------------" + "\n ID: " + Id + "\n Shipper: " + getShipper() + "\n Net weight: " + getNetWeight() + "\n Number of letters: " + getNumberOfLetters() + "\n --------------------";
    }

    public int getNumberOfLetters() {
        return numberOfLetters;
    }

    public void setNumberOfLetters(int numberOfLetters) {
        this.numberOfLetters = numberOfLetters;
    }

    public int getToDeliverOnThisStation() {
        return toDeliverOnThisStation;
    }

    public void setToDeliverOnThisStation(int toDeliverOnThisStation) {
        this.toDeliverOnThisStation = toDeliverOnThisStation;
    }
}
