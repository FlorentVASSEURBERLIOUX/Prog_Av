public class Main {
    public static void main(String[] args) {

        SemaphoreBinaireP sem = new SemaphoreBinaireP(1);

        Affichage TA = new Affichage("AAAA\n",sem);
        TA.start();

        Affichage TB = new Affichage("BBBBB\n",sem);
        TB.start();

        Affichage TC = new Affichage("CCCCCC\n",sem);
        TC.start();

        Affichage TD = new Affichage("DDDDDDD\n",sem);
        TD.start();
    }
}