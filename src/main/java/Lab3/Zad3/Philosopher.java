package Lab3.Zad3;

public class Philosopher extends Thread {

    private int philosopher_number;
    private IForks forks;

    public Philosopher(int philosopher_number, IForks forks) {
        this.philosopher_number = philosopher_number;
        this.forks = forks;
    }

    public void run() {
        while (true) {
            try {
                sleep(2000);
                System.out.println("Philosopher " + philosopher_number + " gets forks");
                forks.get(philosopher_number);
                System.out.println("Philosopher " + philosopher_number + " eats");
                sleep(4000);
                forks.give_back(philosopher_number);
                System.out.println("Philosopher " + philosopher_number + " returned forks");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
