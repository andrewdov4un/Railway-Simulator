import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MyWindow extends JFrame {

    RailwayNetwork network;

    ArrayList<Locomotive> locomotives;
    ArrayList<TrainSet> trainSets;
    JTextArea jTextArea;

    public MyWindow(RailwayNetwork network, ArrayList<Locomotive> locomotives, ArrayList<TrainSet> trainSets) {
        // preparation
        this.network = network;
        this.locomotives = locomotives;
        this.trainSets = trainSets;

        // console
        JPanel consoleArea = new JPanel();
        consoleArea.setLayout(new BorderLayout());
        JTextArea console = new JTextArea();
        jTextArea = console;
        console.setEditable(false);
        JScrollPane consoleScroll = new JScrollPane(console);
        consoleScroll.setPreferredSize(new Dimension(400, getHeight()));
        consoleArea.add(consoleScroll, BorderLayout.CENTER);

        // console buttons
        JPanel consoleButtons = new JPanel(new GridLayout(1, 2));
        JButton clearConsole = new JButton("Clear");
        JButton showHideMovement = new JButton("Hide Movement");
        consoleButtons.add(clearConsole, BorderLayout.WEST);
        consoleButtons.add(showHideMovement);

        consoleArea.add(consoleButtons, BorderLayout.SOUTH);

        // buttons
        JButton addLocomotive = new JButton("Add Locomotive");
        JButton printLocomotives = new JButton("Print Locomotives");
        JButton addTrainSet = new JButton("Add Train");
        JButton printTrainSet = new JButton("Print Train");
        JButton addRailroadCars = new JButton("Add Random Railroad Car");


        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(3, 2));

        // adding buttons
        buttons.add(addLocomotive);
        buttons.add(printLocomotives);
        buttons.add(addTrainSet);
        buttons.add(addRailroadCars);
        buttons.add(printTrainSet);

        // buttons action

        showHideMovement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showHideMovement.getText().equals("Hide Movement")) {
                    showHideMovement.setText("Show Movement");
                    if (trainSets.get(0) != null) {
                        trainSets.get(0).setShowMovement(false);
                    } else {
                        console.append("No trains to show/hide.");
                    }
                }
                else if (showHideMovement.getText().equals("Show Movement")) {
                    showHideMovement.setText("Hide Movement");
                    if (trainSets.get(0) != null) {
                        trainSets.get(0).setShowMovement(true);
                    } else {
                        console.append("No trains to show/hide.");
                    }
                }
            }
        });

        addRailroadCars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterTrainId trainId = new enterTrainId(MyWindow.this);
                int id = Integer.parseInt(trainId.getText());
                if (trainSets.get(id).getCurrentRailroadCars() <= trainSets.get(id).getMaxRailroadCars()) {
                    int random = (int) (Math.random() * 11) + 1;
                    switch (random) {
                        case 1 -> {
                            PassengerRailroadCar car = new PassengerRailroadCar("Amazon International", 800, 1000,  "Passenger Railroad Car");
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Passenger Railroad car added successfully!");
                        }
                        case 2 -> {
                            RailroadBaggageCar car = new RailroadBaggageCar("Amazon International", 800, 1000,  50);
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Railroad Baggage car added successfully!");
                        }
                        case 3 -> {
                            RailroadMailCar car = new RailroadMailCar("Amazon International", 800, 1000);
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Railroad Mail car added successfully!");
                        }
                        case 4 -> {
                            RailroadPostOffice car = new RailroadPostOffice("Amazon International", 800, 1000,  3, 2000);
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Railroad Post Office car added successfully!");
                        }
                        case 5 -> {
                            HeavyRailroadFreightCar car = new HeavyRailroadFreightCar("Amazon International", 800, 1000, true,40000);
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Heavy Railroad Freight car added successfully!");
                        }
                        case 6 -> {
                            RailroadCarForExplosives car = new RailroadCarForExplosives("Amazon International", 800, 1000, true,40000,9,9);
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Railroad car for Explosives added successfully!");
                        }
                        case 7 -> {
                            RailroadCarForGaseousMaterials car = new RailroadCarForGaseousMaterials("Amazon International", 800, 1000, 12,"Dangerous");
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Railroad car for gaseous materials added successfully!");
                        }
                        case 8 -> {
                            RailroadCarForLiquidMaterials car = new RailroadCarForLiquidMaterials("Amazon International", 800, 1000, "Dangerous",40000);
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Railroad car for liquid materials added successfully!");
                        }
                        case 9 -> {
                            RailroadCarForLiquidToxicMaterial car = new RailroadCarForLiquidToxicMaterial("Amazon International", 800, 1000, true,40000);
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Railroad car for liquid toxic materials added successfully!");
                        }
                        case 10 -> {
                            RailroadCarForToxicMaterials car = new RailroadCarForToxicMaterials("Amazon International", 800, 1000, true,40000,"Low","Cool");
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Railroad car for toxic material added successfully!");
                        }
                        case 11 -> {
                            RefrigeratedRailroadCar car = new RefrigeratedRailroadCar("Amazon International", 800, 1000, 0,true);
                            trainSets.get(id).addRailroadCar(car);
                            console.append("Refrigerated Railroad car added successfully!");
                        }
                    }
                } else {
                    console.append("You can't add more than " + trainSets.get(id).getMaxRailroadCars() + " railroad cars to this train.");
                }
            }
        });

        printTrainSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (TrainSet trainSet : trainSets) {
                    trainSet.printTrain();
                }
            }
        });

        addLocomotive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (locomotives.size() < 30) {
                    Locomotive locomotive = new Locomotive();
                    locomotives.add(locomotive);
                    console.append("New locomotive added successfully. ID: " + locomotive.getId());
                    console.append("\n");
                } else {
                    console.setText("You can't create more than 30 locomotives.");
                }
            }
        });

        printLocomotives.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Locomotive locomotive : locomotives) {
                    console.append(String.valueOf(locomotive));
                }
                console.append("\n");
            }
        });

        clearConsole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                console.setText("");
            }
        });

        addTrainSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (trainSets.size() < 30) {
                    console.append("You need to add locomotive to your train, before creating it. Choose free one and enter id.");
                    console.append(String.valueOf(locomotives));
                    int id = -1;
                    try {
                        EnterIdWindow idWindow = new EnterIdWindow(MyWindow.this);
                        idWindow.setModal(true);
                        id = Integer.parseInt(idWindow.getIdTextArea());
                        console.append("id:" + id);
                    } catch (InputMismatchException error) {
                        System.out.println("Wrong id!");
                    }
                    if (locomotives.get(id).isBusy()) {
                        console.append("This locomotive being used, enter or create another one!");
                    }
                    stationsWindow choose = new stationsWindow(MyWindow.this);
                    boolean stationsTrue = choose.isStations();
                    boolean randomTrue = choose.isRandom();
                TrainSet train = null;
                if (stationsTrue) {
                    Windows.enterStationWindow window1 = new Windows.enterStationWindow(MyWindow.this);
                    int st1 = Integer.parseInt(window1.getText());
                    if (st1 < 0 || st1 > 99) {
                        console.append("Choose between 0 - 99");
                        dispose();
                    }
                    Windows.enterStationWindow window2 = new Windows.enterStationWindow(MyWindow.this);
                    int st2 = Integer.parseInt(window2.getText());
                    if (st2 < 0 || st2 > 99) {
                        console.append("Choose between 0 - 99");
                        dispose();
                    }
                    train = new TrainSet(locomotives.get(id), network.getStations().get(st1), network.getStations().get(st2), network, MyWindow.this);
                } else if (randomTrue) {
                    int st1 = (int) (Math.random() * 99);
                    int st2 = (int) (Math.random() * 99);
                    if (st2 == st1) {
                        while (st2 == st1) {
                            st2 = (int) (Math.random() * 99) + 1;
                        }
                    }
                    train = new TrainSet(locomotives.get(id), network.getStations().get(st1), network.getStations().get(st2), network, MyWindow.this);
                }
                trainSets.add(train);
                locomotives.get(id).setBusy(true);
                HeavyRailroadFreightCar heavyRailroadFreightCar = new HeavyRailroadFreightCar("Amazon", 800, 800, true, 20000);
                train.addRailroadCar(heavyRailroadFreightCar);
                PassengerRailroadCar passengerRailroadCar = new PassengerRailroadCar("Amazon", 800, 100, "Passenger");
                train.addRailroadCar(passengerRailroadCar);
                Thread thread = new Thread(train);
                thread.start();
                System.out.println("New train added and started successfully. ID: " + train.getId());
                } else {
                    System.out.println("Sorry, you can't add more than 30 trains.");
                }
            }
        });

        // add to window
        add(consoleArea, BorderLayout.WEST);
        add(buttons, BorderLayout.EAST);


        // window parameters
        setVisible(true);
        setSize(820, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Railway Simulator");
        ImageIcon icon = new ImageIcon("/Users/andrewdov4un/Documents/coding/JavaSchoolProjects/RailwaySimulator/image");
        setIconImage(icon.getImage());
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public void setjTextArea(String text) {
        this.jTextArea.append(text);
    }
}