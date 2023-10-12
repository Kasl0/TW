package Lab1.Zad3;

public class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for(int i = 0;  i < 5;   i++) {
            String message = null;
            try {
                message = buffer.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(message);
        }

    }
}
