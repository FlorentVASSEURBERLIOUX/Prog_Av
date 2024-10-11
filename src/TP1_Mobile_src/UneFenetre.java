import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.swing.*;

class UneFenetre extends JFrame {
    UnMobile mobile;
    private final int LARG = 1000, HAUT = 1000;
    public UneFenetre(int nombre, int value) {
        JFrame frame = new JFrame("UneFenetre");
        frame.setSize(LARG, HAUT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Pause");
        frame.add(button, BorderLayout.SOUTH);

        Container leConteneur = getContentPane();
        leConteneur.setLayout (new GridLayout(nombre,1));
        SemaphoreGen semaphore = new SemaphoreGen(value);
        for (int i=0; i<nombre; i++) {
            mobile = new UnMobile(LARG, HAUT/nombre,semaphore);
            leConteneur.add(mobile);

            Thread thread = new Thread(mobile);
            thread.start();
        }
        frame.add(leConteneur);


        button.addActionListener(e -> {
            if (button.getText().equals("Pause")) {
                button.setText("Resume");
                mobile.suspend();
            }
        });

        frame.setVisible(true);
    }
}