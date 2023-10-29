package Lab4.Zad1;

public class Process extends Thread {
    Buffer buffer;
    int number;
    int component;
    int product;

    public Process(Buffer buffer, int number, int component, int product) {
        this.buffer = buffer;
        this.number = number;
        this.component = component;
        this.product = product;
    }

    public void run() {
        while (true) {
            try {
                buffer.process(number, component, product);
                sleep(1000 + 500 * number);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
