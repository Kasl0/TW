package Lab5;

import java.util.concurrent.Callable;

public class Task implements Callable {
    private Mandelbrot mandelbrot;
    private int task_number;
    private int no_tasks;

    public Task(Mandelbrot mandelbrot, int task_number, int no_tasks) {
        this.mandelbrot = mandelbrot;
        this.task_number = task_number;
        this.no_tasks = no_tasks;
    }

    public Void call() {
        mandelbrot.calculate_task(task_number, no_tasks);
        return null;
    }
}
