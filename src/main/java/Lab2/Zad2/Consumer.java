package Lab2.Zad2;

public class Consumer extends Thread {
    private Semaphore semaphore;

    public Consumer(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            semaphore.P();
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        semaphore.V();
    }
}
