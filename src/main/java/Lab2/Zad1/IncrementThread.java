package Lab2.Zad1;

public class IncrementThread extends Thread {

    private Counter counter;

    public IncrementThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i=0; i<100000000; i++) {
            try {
                counter.increment();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
