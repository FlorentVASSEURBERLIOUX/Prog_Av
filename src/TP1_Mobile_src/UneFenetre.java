import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=400, HAUT=250;
    
    public UneFenetre()
    {
	JFrame frame = new JFrame("UneFenetre");
        frame.setSize(LARG, HAUT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	// TODO 
	// ajouter sonMobile a la fenetre
	// creer une thread laThread avec sonMobile
	// afficher la fenetre
	// lancer laThread 
    }
}
