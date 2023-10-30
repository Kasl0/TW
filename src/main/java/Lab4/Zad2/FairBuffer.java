package Lab4.Zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FairBuffer implements IBuffer {

    int count;
    int size;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition first_producer;
    private final Condition rest_producers;
    private final Condition first_consumer;
    private final Condition rest_consumers;

    public FairBuffer(int size) {
        this.count = 0;
        this.size = size;

        this.first_producer = lock.newCondition();
        this.rest_producers = lock.newCondition();
        this.first_consumer = lock.newCondition();
        this.rest_consumers = lock.newCondition();
    }

    public int get(int count) throws InterruptedException {
        lock.lock();
        try {
            if (lock.hasWaiters(first_consumer)) {
                rest_consumers.await();
            }

            while (this.count < count) {
                first_consumer.await();
            }

            this.count -= count;

            rest_consumers.signal();
            first_producer.signal();

            System.out.println(this.count);
            return count;
        } finally {
            lock.unlock();
        }
    }

    public void insert(int count) throws InterruptedException {
        lock.lock();
        try {
            if (lock.hasWaiters(first_producer)) {
                rest_producers.await();
            }

            while (this.count + count > size) {
                first_producer.await();
            }

            this.count += count;

            rest_producers.signal();
            first_consumer.signal();

            System.out.println(this.count);
        } finally {
            lock.unlock();
        }
    }
}
