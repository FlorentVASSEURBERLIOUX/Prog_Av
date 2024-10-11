class BAL {
    private String lettre = null; // le buffer
    private boolean available = false;

    public synchronized void deposer(String parLettre) throws InterruptedException {
        if (available){
            wait();
        }
        lettre = parLettre;
        System.out.println("Producteur a déposé une lettre ");
        available = true;
        notifyAll();
    }

    public synchronized String retirer() throws InterruptedException {
        if (!available){
            wait();
        }
        System.out.println("Consommateur a retiré une lettre : " + lettre);
        lettre = null;
        available = false;
        notifyAll();
        return lettre;
    }
}