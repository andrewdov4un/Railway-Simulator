import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterIdWindow extends JDialog {
    private JTextArea idTextArea = new JTextArea();
    private boolean send = false;

    public EnterIdWindow(JFrame parent) {
        super(parent, "Enter ID", true);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        idTextArea.setPreferredSize(new Dimension(200, 50));
        main.add(idTextArea, BorderLayout.CENTER);

        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSend(true);
                dispose();
            }
        });
        main.add(enter, BorderLayout.SOUTH);

        add(main);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public String getIdTextArea() {
        return idTextArea.getText();
    }

    public void setIdTextArea(String text) {
        this.idTextArea.setText(text);
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }
}
