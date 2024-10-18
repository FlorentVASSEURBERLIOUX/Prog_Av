import java.util.Random;

public class BisCustomer implements Runnable {
    private Random rand;
    private BisBAL bal;

    public BisCustomer(Random rand, BisBAL bal) {
        this.rand = rand;
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(rand.nextInt(3000));
                Lettre lettre = bal.achete();
                if (lettre != null) {
                    System.out.println("[" + Thread.currentThread().getName() + "]" +
                            "[" + bal.getStock() + "] Je lis la lettre : " + lettre.getTexte());
                } else {
                    System.out.println("[" + Thread.currentThread().getName() + "]" +
                            "[" + bal.getStock() + "] j'attends");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] je m'arrête");
        }
    }
}
