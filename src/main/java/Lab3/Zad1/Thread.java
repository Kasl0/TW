package Lab3.Zad1;

public class Thread extends java.lang.Thread {

    private PrinterMonitor monitor;

    public Thread(PrinterMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (true) {
            int printer;
            try {
                // create_task_to_print();
                printer = monitor.reserve();
                print(printer);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            monitor.release(printer);
        }
    }

    private void print(int printer) throws InterruptedException {
        System.out.println("Printer " + printer + " starts");
        sleep(1000);
        System.out.println("Printer " + printer + " ends");
    }
}
