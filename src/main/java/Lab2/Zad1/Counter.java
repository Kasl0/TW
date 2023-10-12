package Lab2.Zad1;

public class Counter {
    private int i;
    private Semaphore semaphore;

    public Counter() {
        this.i = 0;
        this.semaphore = new Semaphore();
    }

    public int getValue() {
        return i;
    }

    public void increment() throws InterruptedException {
        semaphore.P();
        i++;
        semaphore.V();
    }

    public void decrement() throws InterruptedException {
        semaphore.P();
        i--;
        semaphore.V();
    }
}
