package Lab3.Zad1;

import java.util.concurrent.locks.*;

public class PrinterMonitor {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull  = lock.newCondition();

    private int count;
    private int no_printers;
    private boolean[] printers;

    public PrinterMonitor(int no_printers) {
        this.count = 0;
        this.no_printers = no_printers;
        this.printers = new boolean[no_printers];
    }

    public int reserve() throws InterruptedException {
        lock.lock();
        try {
            while (count == no_printers)
                notFull.await();
            count++;
            for (int i=0;i<no_printers;i++) {
                if (!printers[i]) {
                    printers[i] = true;
                    return i;
                }
            }
        } finally {
            lock.unlock();
        }
        return -1;
    }

    public void release(int printer) {
        lock.lock();
        try {
            count--;
            printers[printer] = false;
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }
}
