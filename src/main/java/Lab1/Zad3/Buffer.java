package Lab1.Zad3;

public class Buffer {

    String value;

    public Buffer() {
    }

    public synchronized void put(String value) throws InterruptedException {
        while (this.value != null) {
            wait();
        }
        this.value = value;
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (this.value == null) {
            wait();
        }
        String val = this.value;
        this.value = null;
        notifyAll();
        return val;
    }
}
