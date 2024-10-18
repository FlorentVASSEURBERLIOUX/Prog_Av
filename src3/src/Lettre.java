import java.util.Random;

public class Lettre {
    private String texte;
    private static final String[] mots = {
            "Bonjour", "Merci", "Au revoir", "Félicitations", "Bienvenue", "Hello", "Désolé", "Coucou", "Salut"
    };

    public Lettre() {
        Random random = new Random();
        int index = random.nextInt(mots.length);
        this.texte = mots[index];
    }

    public String getTexte() {
        return texte;
    }
}