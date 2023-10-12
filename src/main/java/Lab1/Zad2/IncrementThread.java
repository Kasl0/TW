package Lab1.Zad2;

public class IncrementThread extends Thread {

    private Counter counter;

    public IncrementThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i=0; i<100000000; i++) {
            counter.increment();
        }
    }
}
