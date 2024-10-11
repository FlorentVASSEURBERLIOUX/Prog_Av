import java.lang.reflect.Array;
import java.util.ArrayList;

class Producer implements Runnable {
    private BAL bal;

    public Producer(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                String lettre = "Lettre " + i;
                bal.deposer(lettre);
                Thread.sleep((int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
