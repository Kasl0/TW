package Lab3.Zad1;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        PrinterMonitor monitor = new PrinterMonitor(3);

        int NO_THREADS = 5;
        Thread[] threads = new Thread[NO_THREADS];

        for (int i=0; i<NO_THREADS; i++) {
            threads[i] = new Thread(monitor);
            threads[i].start();
        }

        for (int i=0; i<NO_THREADS; i++) {
            threads[i].join();
        }

        System.out.println("End");
    }
}
