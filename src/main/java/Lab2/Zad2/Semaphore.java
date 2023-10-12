package Lab2.Zad2;

public class Semaphore {
    private int value;

    public Semaphore(int value) {
        this.value = value;
    }

    public synchronized void P() throws InterruptedException {
        System.out.println("Requesting cart. Number of carts: " + getValue());
        while (value == 0) {
            wait();
        }
        value--;
        System.out.println("Got cart. Number of carts: " + getValue());
    }

    public synchronized void V() {
        value++;
        System.out.println("Returned cart. Number of carts: " + getValue());
        notify();
    }

    public int getValue() {
        return value;
    }
}
