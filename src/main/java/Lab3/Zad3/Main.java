package Lab3.Zad3;

public class Main {

    public static void main(String[] args) {

        // IForks forks = new Forks();
        IForks forks = new ForksWithWaiter();

        for (int i=0; i<5; i++) {
            Philosopher philosopher = new Philosopher(i, forks);
            philosopher.start();
        }
    }
}
