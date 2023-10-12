package Lab1.Zad3;

public class Producer extends Thread {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for(int i = 0;  i < 5;   i++) {
            try {
                buffer.put("message "+i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
