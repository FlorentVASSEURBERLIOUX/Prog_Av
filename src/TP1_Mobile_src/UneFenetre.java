import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=800, HAUT=400;
    
    public UneFenetre()
    {
	    JFrame frame = new JFrame("UneFenetre");
        frame.setSize(LARG, HAUT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        sonMobile = new UnMobile(LARG, HAUT);
        frame.add(sonMobile);
        Thread thread = new Thread(sonMobile);
        thread.start();

        // TODO
        // ajouter sonMobile a la fenetre
        // creer une thread laThread avec sonMobile
        // afficher la fenetre
        // lancer laThread
    }
}
