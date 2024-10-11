class Customer implements Runnable {
    private BAL bal;

    public Customer(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                bal.retirer();
                Thread.sleep((int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}