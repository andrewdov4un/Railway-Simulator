import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class enterTrainId extends JDialog {

    JTextArea text;

    public enterTrainId(JFrame parent) {
        super(parent, "Enter train id", true);

        JPanel main = new JPanel();

        JTextArea textArea = new JTextArea();

        main.setPreferredSize(new Dimension(200,50));

        text = textArea;

        main.add(textArea, BorderLayout.NORTH);

        JButton enter = new JButton("Enter");

        main.add(enter, BorderLayout.SOUTH);

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enter.doClick();
                }
            }
        });

        add(main);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public String getText() {
        return text.getText();
    }
}
