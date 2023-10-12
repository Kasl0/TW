package Lab1.Zad2;

public class Counter {
    private int i;

    public Counter() {
        this.i = 0;
    }

    public int getValue() {
        return i;
    }

    public synchronized void increment() {
        i++;
    }

    public synchronized void decrement() {
        i--;
    }
}
