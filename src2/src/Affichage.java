class Affichage extends Thread {

    String texte;
    public Affichage (String txt){
        texte = txt;
    }
    public void run()
    {
        synchronized(System.out) {
            for (int i = 0; i < texte.length(); i++) {
                System.out.print(texte.charAt(i));
                try {
                    sleep(100);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}