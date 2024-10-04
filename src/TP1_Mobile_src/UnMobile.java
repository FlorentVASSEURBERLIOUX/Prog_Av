import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
	int saLargeur, saHauteur, sonDebDessin;
	final int sonPas = 10, sonTemps = (int) (Math.random()*50), sonCote = 40;
	private static boolean paused = false;

	UnMobile(int telleLargeur, int telleHauteur) {
		super();
		saLargeur = telleLargeur;
		saHauteur = telleHauteur;
		setSize(telleLargeur, telleHauteur);
	}

	public void run() {
		while (true) {
			Thread th = new Thread(this);
			for (sonDebDessin = 0; sonDebDessin + sonCote < saLargeur - sonPas; sonDebDessin += sonPas) {
				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			for (sonDebDessin = saLargeur - sonCote; sonDebDessin > 0; sonDebDessin -= sonPas) {
				if (paused){
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
		}
	}

	public void suspend() {
		paused = true;
	}


	public void paintComponent(Graphics telCG) {
		super.paintComponent(telCG);
		telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
	}
}