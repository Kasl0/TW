package Lab4.Zad2;

public interface IBuffer {
    int get(int count) throws InterruptedException;
    void insert(int count) throws InterruptedException;
}
