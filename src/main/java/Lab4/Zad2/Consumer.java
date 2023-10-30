package Lab4.Zad2;

import java.util.Random;

public class Consumer extends Thread {

    IBuffer buffer;
    int M;
    Timer timer;

    public Consumer(IBuffer buffer, int M, Timer timer) {
        this.buffer = buffer;
        this.M = M;
        this.timer = timer;
    }

    public void run() {
        Random random = new Random();

        while (true) {
            try {
                int randomCount = random.nextInt(M) + 1;
                long time1 = System.nanoTime();
                buffer.get(randomCount);
                long time2 = System.nanoTime();
                timer.time_sum += (double)(time2 - time1) / 1000000;
                timer.iterations++;
                System.out.println("Avg get time: " + (timer.time_sum / timer.iterations) + " ms");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}