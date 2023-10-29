package Lab4.Zad1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    int[] processing_line;
    int[] process_position;

    private final Lock lock = new ReentrantLock();
    private final Condition[] component;

    public Buffer(int N, int no_process) {
        this.processing_line = new int[N];
        for (int i=0;i<N;i++) {
            this.processing_line[i] = -1;
        }
        this.process_position = new int[no_process];
        this.component = new Condition[no_process];
        for (int i=0;i<no_process;i++) {
            this.process_position[i] = 0;
            this.component[i] = lock.newCondition();
        }
    }

    public void process(int process, int comp, int product) throws InterruptedException {
        lock.lock();
        try {
            while (processing_line[process_position[process]] != comp) {
                component[process].await();
            }
            processing_line[process_position[process]] = product;
            process_position[process]++;

            if (processing_line.length == process_position[process]) {
                process_position[process] = 0;
            }
            System.out.println(bufferToString(processing_line));

            if (process+1 < component.length) {
                component[process+1].signal();
            }
            else {
                component[0].signal();
            }

        } finally {
            lock.unlock();
        }
    }

    public String bufferToString(int[] array) {
        StringBuilder sb = new StringBuilder();

        sb.append(array[0]);

        for (int i = 1; i < array.length; i++) {
            sb.append(", ").append(array[i]);
        }

        return sb.toString();
    }
}
