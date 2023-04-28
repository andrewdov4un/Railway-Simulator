import javax.swing.*;
import java.util.*;

public class Start {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        RailwayNetwork network = new RailwayNetwork();

        ArrayList<Locomotive> locomotives = new ArrayList<>();
        ArrayList<TrainSet> trainSets = new ArrayList<>();
        LinkedList<Thread> threads = new LinkedList<>();

        TrainSetInfoUpdater infoUpdater = new TrainSetInfoUpdater(trainSets);

        MyWindow window = new MyWindow(network, locomotives, trainSets);
    }
}