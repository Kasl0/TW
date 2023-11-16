package Lab4.Zad2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Producer extends Thread {

    private IBuffer buffer;
    private int M;
    private FileWriter fw;

    public Producer(IBuffer buffer, int M, FileWriter fw) {
        this.buffer = buffer;
        this.M = M;
        this.fw = fw;
    }

    public void run() {
        Random random = new Random();

        while (true) {
            try {
                int randomCount = random.nextInt(M) + 1;
                long time1 = System.nanoTime();
                buffer.insert(randomCount);
                long time2 = System.nanoTime();
                fw.write("insert," + randomCount + "," + (time2 - time1) + '\n');
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
