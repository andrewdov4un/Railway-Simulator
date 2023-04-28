import java.util.Comparator;

public class TrainSetRouteLengthComparator implements Comparator<TrainSet> {
    @Override
    public int compare(TrainSet train1, TrainSet train2) {
        return Integer.compare(train2.totalRouteLength(train2.getRoute()), train1.totalRouteLength(train1.getRoute()));
    }
}
