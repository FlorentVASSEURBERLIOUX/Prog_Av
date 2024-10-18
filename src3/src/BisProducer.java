import java.util.Random;

public class BisProducer implements Runnable {
    private BisBAL bal;
    private Random rand;

    public BisProducer(Random rand,BisBAL bal) {
        this.rand = rand;
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(3000);
                boolean added = bal.depose(new Lettre());

                if (added) {
                    System.out.println("[" + Thread.currentThread().getName() + "]" +
                            "[" + bal.getStock() + "] je livre.");
                } else {
                    System.out.println("[" + Thread.currentThread().getName() + "]" +
                            "[" + bal.getStock() + "] la boîte aux lettre est pleine.");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] je m'arrête");
        }
    }
}
