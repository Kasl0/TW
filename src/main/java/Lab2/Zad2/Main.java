package Lab2.Zad2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);

        Consumer c1 = new Consumer(semaphore);
        Consumer c2 = new Consumer(semaphore);
        Consumer c3 = new Consumer(semaphore);
        c1.start();
        c2.start();
        c3.start();

        c1.join();
        c2.join();
        c3.join();
    }
}
