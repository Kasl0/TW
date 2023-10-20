package Lab3.Zad3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ForksWithWaiter implements IForks {
    private final Lock lock = new ReentrantLock();
    private final boolean[] forks;
    private final Condition[] forks_available;
    private final Condition blocked_philosopher;
    private int blocked_philosopher_number;

    public ForksWithWaiter() {
        this.forks = new boolean[5];
        this.forks_available = new Condition[5];
        this.blocked_philosopher = lock.newCondition();
        this.blocked_philosopher_number = 0;

        for (int i=0; i<5; i++) {
            this.forks[i] = false;
            this.forks_available[i] = lock.newCondition();
        }
    }

    public void get(int philosopher_number) throws InterruptedException {
        lock.lock();
        while(philosopher_number == blocked_philosopher_number) {
            System.out.println("Philosopher " + philosopher_number + " BLOCKED");
            blocked_philosopher.await();
        }
        try {
            while (forks[philosopher_number]) {
                forks_available[philosopher_number].await();
            }
            forks[philosopher_number] = true;
            while (forks[get_left_fork_number(philosopher_number)]) {
                forks_available[get_left_fork_number(philosopher_number)].await();
            }
            forks[get_left_fork_number(philosopher_number)] = true;
        } finally {
            lock.unlock();
        }
    }

    public void give_back(int philosopher_number) {
        lock.lock();
        try {
            forks[philosopher_number] = false;
            forks[get_left_fork_number(philosopher_number)] = false;
            forks_available[philosopher_number].signal();
            forks_available[get_left_fork_number(philosopher_number)].signal();

            blocked_philosopher_number = philosopher_number;
            blocked_philosopher.signal();
        } finally {
            lock.unlock();
        }
    }

    private int get_left_fork_number(int philosopher_number) {
        if (philosopher_number - 1 >= 0) {
            return philosopher_number - 1;
        }
        else {
            return 4;
        }
    }
}
