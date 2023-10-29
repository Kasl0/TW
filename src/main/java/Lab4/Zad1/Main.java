package Lab4.Zad1;

public class Main {

    public static void main(String[] args) {

        int NO_PROCESS = 5;

        Buffer buffer = new Buffer(10, NO_PROCESS);

        for (int i=0; i<NO_PROCESS-1; i++) {
            Process process = new Process(buffer, i,i-1, i);
            process.start();
        }
        Process consumer = new Process(buffer, NO_PROCESS-1, NO_PROCESS-2, -1);
        consumer.start();
    }
}
