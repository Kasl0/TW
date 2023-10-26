package Lab4.Zad1;

public class Main {

    public static void main(String[] args) {

        Buffer buffer = new Buffer(10);

        int NO_PROCESS = 5;
        for (int i=0; i<NO_PROCESS; i++) {
            Process process = new Process(buffer, i-1, i);
            process.start();
        }
        Process consumer = new Process(buffer, NO_PROCESS-1, -1);
        consumer.start();
    }
}
