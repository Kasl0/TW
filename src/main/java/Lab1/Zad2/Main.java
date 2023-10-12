package Lab1.Zad2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        IncrementThread t1 = new IncrementThread(counter);
        DecrementThread t2 = new DecrementThread(counter);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter.getValue());
    }
}
