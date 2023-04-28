import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stationsWindow extends JDialog {
    private boolean stations = false;
    private boolean random = false;

    stationsWindow(JFrame parent) {
        super(parent, "Stations or Random", true);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        JButton stations = new JButton("Stations");
        JButton random = new JButton("Random");

        main.add(stations, BorderLayout.WEST);
        main.add(random, BorderLayout.EAST);

        stations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStations(true);
                dispose();
            }
        });

        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRandom(true);
                dispose();
            }
        });

        add(main);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public boolean isStations() {
        return stations;
    }

    public void setStations(boolean stations) {
        this.stations = stations;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }
}
