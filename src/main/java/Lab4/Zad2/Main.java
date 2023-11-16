package Lab4.Zad2;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{

        FileWriter fw = new FileWriter("src/main/java/Lab4/Zad2/waiting_times.csv");
        fw.write("function,portion,time\n");

        int M = 1000;
        int NO_PRODUCERS = 100;
        int NO_CONSUMERS = 100;

        IBuffer buffer = new Buffer(2*M);
        // IBuffer buffer = new FairBuffer(2*M);

        for (int i=0; i<NO_PRODUCERS; i++) {
            Producer producer = new Producer(buffer, M, fw);
            producer.start();
        }
        for (int i=0; i<NO_CONSUMERS; i++) {
            Consumer consumer = new Consumer(buffer, M, fw);
            consumer.start();
        }
    }
}
