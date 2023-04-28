import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TrainSetInfoUpdater implements Runnable {
    private ArrayList<TrainSet> trainSets;

    public TrainSetInfoUpdater(ArrayList<TrainSet> trainSets) {
        this.trainSets = trainSets;
    }

    @Override
    public void run() {
        while (true) {
            ArrayList<TrainSet> sortedTrainSets = sortTrainSets(trainSets);
            try{
                writeTrainSetsToFile(sortedTrainSets);
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private ArrayList<TrainSet> sortTrainSets(ArrayList<TrainSet> trainSets) {
        if (trainSets != null) {
            // Sort by weight in ascending order
            Collections.sort(trainSets, Comparator.comparingInt(TrainSet::totalWeightOfCargo));
            // Sort by route length in descending order
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Collections.sort(trainSets, Comparator.comparingInt(trainSet -> -trainSet.totalRouteLength(trainSet.getRoute())));
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return trainSets;
    }

    private void writeTrainSetsToFile(ArrayList<TrainSet> trainSets) {
        if (trainSets != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("AppState.txt"))) {
                writer.write("List of trains:\n");
                for (int i = 0; i < trainSets.size(); i++) {
                    writer.write(trainSets.get(i).printTrainAppState());
                    writer.newLine();
                    for (int j = 0; j < trainSets.get(i).getRailroadCars().size(); j++) {
                        writer.write(trainSets.get(i).printRailroadCars(j));
                        writer.newLine();
                    }
                    for (int j = 0; j < trainSets.get(i).getFreightRailroadCars().size(); j++) {
                        writer.write(trainSets.get(i).printFreightRailroadCars(j));
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
