package Lab3.Zad2;

public class Client extends Thread {

    private int pair_number;
    private Waiter waiter;

    public Client(int pair_number, Waiter waiter) {
        this.pair_number = pair_number;
        this.waiter = waiter;
    }

    public void run() {
        while (true) {
            try {
                sleep(2000);
                System.out.println("Client of pair " + pair_number + " reserves table");
                waiter.reserve(pair_number);
                System.out.println("Client of pair " + pair_number + " got table");
                sleep(4000);
                waiter.release();
                System.out.println("Client of pair " + pair_number + " released table");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
