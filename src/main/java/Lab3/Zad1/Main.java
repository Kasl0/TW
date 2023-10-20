package Lab3.Zad1;

public class Main {

    public static void main(String[] args) {

        PrinterMonitor monitor = new PrinterMonitor(3);

        int NO_THREADS = 5;

        for (int i=0; i<NO_THREADS; i++) {
            Thread thread = new Thread(monitor);
            thread.start();
        }
    }
}
