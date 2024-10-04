import java.util.concurrent.Semaphore;

class Affichage extends Thread {

    String texte;
    SemaphoreBinaireP sem;

    public Affichage (String txt, SemaphoreBinaireP parSem) {
        texte = txt;
        sem = parSem;
    }
    public void run()
    {
        /*
        synchronized(System.out) {
            for (int i = 0; i < texte.length(); i++) {
                System.out.print(texte.charAt(i));
                try {
                    sleep(100);
                } catch (InterruptedException e) {

                }
            }
        }
         */

        sem.syncWait();
        for (int i = 0; i < texte.length(); i++) {

            System.out.print(texte.charAt(i));

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sem.syncSignal();
    }
}