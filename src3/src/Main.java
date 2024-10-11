public class Main {
    public static void main(String[] args) {
        BAL bal = new BAL();
        Thread producteurThread = new Thread(new Producer(bal));
        Thread consommateurThread = new Thread(new Customer(bal));

        producteurThread.start();
        consommateurThread.start();
    }
}