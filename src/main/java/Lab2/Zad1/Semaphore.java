package Lab2.Zad1;

public class Semaphore {
    private boolean state;

    public Semaphore() {
        this.state = true;
    }

    public synchronized void P() throws InterruptedException {
        while (!state) {
            wait();
        }
        state = false;
    }

    public synchronized void V() throws InterruptedException {
        state = true;
        notify();
    }
}
