package Lab3.Zad2;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Waiter waiter = new Waiter();

        int NO_PAIRS = 3;

        for (int i=0; i<NO_PAIRS; i++) {
            Client client1 = new Client(i, waiter);
            Client client2 = new Client(i, waiter);
            client1.start();
            client2.start();
        }
    }
}
