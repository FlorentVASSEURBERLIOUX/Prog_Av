import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
	int saLargeur, saHauteur, sonDebDessin;
	final int sonPas = 10, sonTemps = (int) ((Math.random() * 20)) + 20, sonCote = 40;
	private static boolean paused = false;
	SemaphoreGen semaphore;

	UnMobile(int telleLargeur, int telleHauteur, SemaphoreGen parSemaphore) {
		super();
		saLargeur = telleLargeur;
		saHauteur = telleHauteur;
		setSize(telleLargeur, telleHauteur);
		semaphore = parSemaphore;
	}

	public void run() {
		while (true) {
			Thread th = new Thread(this);
			for (sonDebDessin = 0; sonDebDessin < saLargeur / 3; sonDebDessin += sonPas) {
				if (paused) {
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			semaphore.syncWait();

			for (sonDebDessin = saLargeur / 3; sonDebDessin < saLargeur / 3 * 2; sonDebDessin += sonPas) {
				if (paused) {
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			semaphore.syncSignal();

			for (sonDebDessin = saLargeur / 3 * 2; sonDebDessin + sonCote < saLargeur - sonPas; sonDebDessin += sonPas) {
				if (paused) {
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			for (sonDebDessin = saLargeur - sonCote; sonDebDessin > saLargeur / 3 * 2; sonDebDessin -= sonPas) {
				if (paused) {
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			semaphore.syncWait();

			for (sonDebDessin = saLargeur / 3 * 2; sonDebDessin > saLargeur / 3; sonDebDessin -= sonPas) {
				if (paused) {
					th.suspend();
				}

				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			semaphore.syncSignal();

			for (sonDebDessin = saLargeur / 3; sonDebDessin > 0; sonDebDessin -= sonPas) {
				if (paused) {
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

	public void paintComponent(Graphics elem) {
		super.paintComponent(elem);

		if (sonDebDessin > (saLargeur / 3) && sonDebDessin < (saLargeur / 3 * 2)) {
			elem.setColor(Color.RED);
		} else {
			elem.setColor(Color.LIGHT_GRAY);
		}

		elem.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
	}
}
