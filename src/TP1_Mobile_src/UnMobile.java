import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
	int saLargeur, saHauteur, sonDebDessin;
	final int sonPas = 10, sonTemps = 5, sonCote = 40;
	private volatile int paused = 0;

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
				if (paused == 1){
					th.suspend();
				} else if (paused == 2) {
					th.resume();
				}
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			for (sonDebDessin = saLargeur - sonCote; sonDebDessin > 0; sonDebDessin -= sonPas) {
				if (paused == 1){
					th.suspend();
				} else if (paused == 2) {
					th.resume();
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
		paused = 1;
	}

	public void resume() {
		paused = 2;
	}

	public void paintComponent(Graphics telCG) {
		super.paintComponent(telCG);
		telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
	}
}