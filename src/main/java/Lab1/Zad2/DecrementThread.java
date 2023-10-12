package Lab1.Zad2;

public class DecrementThread extends Thread {

    private Counter counter;

    public DecrementThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        for (int i=0; i<100000000; i++) {
            counter.decrement();
        }
    }
}
