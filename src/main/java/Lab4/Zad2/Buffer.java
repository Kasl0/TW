package Lab4.Zad2;

public class Buffer implements IBuffer {

    int count;
    int size;

    public Buffer(int size) {
        this.count = 0;
        this.size = size;
    }

    public synchronized int get(int count) throws InterruptedException {
        while (this.count < count) {
            wait();
        }
        this.count -= count;
        notifyAll();

        System.out.println(this.count);
        return count;
    }

    public synchronized void insert(int count) throws InterruptedException {
        while (this.count + count > size) {
            wait();
        }
        this.count += count;
        notifyAll();

        System.out.println(this.count);
    }
}
