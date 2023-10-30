package Lab4.Zad2;

public class Main {

    public static void main(String[] args) {

        int M = 1000;
        int NO_PRODUCERS = 10;
        int NO_CONSUMERS = 10;

        IBuffer buffer = new Buffer(2*M);
        // IBuffer buffer = new FairBuffer(2*M);

        Timer ProducerTimer = new Timer();
        Timer ConsumerTimer = new Timer();

        for (int i=0; i<NO_PRODUCERS; i++) {
            Producer producer = new Producer(buffer, M, ProducerTimer);
            producer.start();
        }
        for (int i=0; i<NO_CONSUMERS; i++) {
            Consumer consumer = new Consumer(buffer, M, ConsumerTimer);
            consumer.start();
        }
    }
}
