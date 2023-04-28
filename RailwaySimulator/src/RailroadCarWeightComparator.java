import java.util.Comparator;

public class RailroadCarWeightComparator implements Comparator<FreightRailroadCars> {

    @Override
    public int compare(FreightRailroadCars car1, FreightRailroadCars car2) {
        return Integer.compare(car1.getGrossWeight(), car2.getGrossWeight());
    }
}
