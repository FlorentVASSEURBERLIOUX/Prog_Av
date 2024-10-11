import java.util.Scanner;

class Producer implements Runnable {
    private BAL bal;

    public Producer(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            String lettre = "";
            while(lettre != "q"){
                Scanner scan = new Scanner(System.in);
                lettre = scan.nextLine();
                if (lettre != "q"){
                    bal.deposer(lettre);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
