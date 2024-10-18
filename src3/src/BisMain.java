import java.util.Random;

public class BisMain {
    public  static  void main(String[] args) {

        final BisBAL bal =  new BisBAL() ;
        final Random rand =  new Random() ;

        BisProducer producer =  new BisProducer(rand,bal) ;

        BisCustomer customer =  new BisCustomer(rand,bal) ;

        Thread [] livreurs =  new Thread[5] ;
        Thread [] clients =  new Thread[5] ;

        for (int i =  0 ; i < livreurs.length ; i++) {
            livreurs[i] =  new Thread(producer) ;
        }

        for (int i =  0 ; i < clients.length ; i++) {
            clients[i] =  new Thread(customer) ;
        }

        for (int i =  0 ; i < livreurs.length ; i++) {
            livreurs[i].start() ;
        }

        for (int i =  0 ; i < clients.length ; i++) {
            clients[i].start() ;
        }
    }
}
