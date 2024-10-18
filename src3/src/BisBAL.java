import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BisBAL {
    private BlockingQueue<Lettre> queue = new ArrayBlockingQueue<>(20);

    public boolean depose(Lettre lettre) throws InterruptedException {
        return queue.offer(lettre, 200, TimeUnit.MILLISECONDS);
    }

    public Lettre achete() throws InterruptedException {
        return queue.poll(200, TimeUnit.MILLISECONDS);
    }

    public int getStock() {
        return queue.size();
    }
}
