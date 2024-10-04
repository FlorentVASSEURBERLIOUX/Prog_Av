import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

class UneFenetre extends JFrame {
    UnMobile mobile;
    private final int LARG = 1500, HAUT = 1000;
    public UneFenetre(int nombre) {
        JFrame frame = new JFrame("UneFenetre");
        frame.setSize(LARG, HAUT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Pause");
        frame.add(button, BorderLayout.SOUTH);

        Container leConteneur = getContentPane();
        leConteneur.setLayout (new GridLayout(nombre,1));
        for (int i=0; i<nombre; i++) {
            mobile = new UnMobile(LARG, HAUT/nombre);
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