package Lab2.Zad1;

public class DecrementThread extends Thread {

    private Counter counter;

    public DecrementThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i=0; i<100000000; i++) {
            try {
                counter.decrement();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
