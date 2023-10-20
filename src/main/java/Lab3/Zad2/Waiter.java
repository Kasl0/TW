package Lab3.Zad2;

import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {
    private final Lock lock = new ReentrantLock();
    private final Condition empty  = lock.newCondition();
    private HashMap<Integer,Condition> waiting_pairs;
    private int no_at_table;

    public Waiter() {
        this.waiting_pairs = new HashMap<>();
        this.no_at_table = 0;
    }

    public void reserve(int pair_number) throws InterruptedException {
        lock.lock();
        try {
            if (waiting_pairs.containsKey(pair_number)) {
                while (no_at_table != 0) {
                    empty.await();
                }
                waiting_pairs.get(pair_number).signal();
                waiting_pairs.remove(pair_number);
            }
            else {
                Condition new_wait_condition = lock.newCondition();
                waiting_pairs.put(pair_number, new_wait_condition);
                new_wait_condition.await();
            }
            no_at_table++;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            no_at_table--;
            if (no_at_table == 0) {
                empty.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
