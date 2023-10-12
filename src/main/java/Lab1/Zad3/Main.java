package Lab1.Zad3;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Buffer buffer = new Buffer();

        Producer p1 = new Producer(buffer);
        Producer p2 = new Producer(buffer);

        Consumer c1 = new Consumer(buffer);
        Consumer c2 = new Consumer(buffer);

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        p1.join();
        p2.join();
        c1.join();
        c2.join();
    }
}
