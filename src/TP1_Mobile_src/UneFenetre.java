import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame {
    UnMobile sonMobile;
    private final int LARG = 1500, HAUT = 1000;

    public UneFenetre() {
        JFrame frame = new JFrame("UneFenetre");
        frame.setSize(LARG, HAUT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Pause");
        frame.add(button, BorderLayout.SOUTH);

        sonMobile = new UnMobile(LARG, HAUT);
        frame.add(sonMobile);

        Thread thread = new Thread(sonMobile);
        thread.start();

        button.addActionListener(e -> {
            if (button.getText().equals("Pause")) {
                button.setText("Resume");
                sonMobile.suspend();
            } else {
                button.setText("Pause");
                sonMobile.resume();
            }
        });

        frame.setVisible(true);
    }
}