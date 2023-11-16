package Lab5;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.JFrame;

public class Mandelbrot extends JFrame {
    private final int MAX_ITER = 500;
    private final double ZOOM = 150;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;

    public Mandelbrot() throws ExecutionException, InterruptedException, IOException {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        FileWriter fw = new FileWriter("src/main/java/Lab5/processing_times.csv");
        System.out.println("Starting file");
        fw.write("iterations,no_threads,no_tasks,average,deviation\n");

        for (int no_threads : new int[]{1, 8, 16}) {
            for (int no_tasks : new int[]{no_threads, no_threads*10, getWidth() * getHeight()}) {

                long[] times;
                times = new long[10];
                for (int i = 0; i < 10; i++) {
                    long time1 = System.nanoTime();
                    ExecutorService pool = Executors.newFixedThreadPool(no_threads);
                    Set<Future<Void>> set = new HashSet<Future<Void>>();
                    for (int j = 0; j < no_tasks; j++) {
                        Callable<Void> callable = new Task(this, j, no_tasks);
                        Future<Void> future = pool.submit(callable);
                        set.add(future);
                    }
                    for (Future<Void> future : set) {
                        future.get();
                    }
                    long time2 = System.nanoTime();
                    times[i] = time2 - time1;
                }
                System.out.println("Adding data");
                double mean = calculateAverage(times);
                fw.write(MAX_ITER + "," + no_threads + "," + no_tasks + "," + mean + "," + calculateStandardDeviation(times, mean) + "\n");
            }
        }
        fw.close();
    }

    public static double calculateAverage(long[] array) {
        long sum = 0;

        for (long num : array) {
            sum += num;
        }

        return (double)sum / array.length;
    }

    public static double calculateStandardDeviation(long[] array, double mean) {
        double sumSquaredDiff = 0;

        for (long num : array) {
            double diff = num - mean;
            sumSquaredDiff += diff * diff;
        }

        double variance = sumSquaredDiff / array.length;
        return Math.sqrt(variance);
    }

    public void calculate_task(int task_number, int no_tasks) {
        int no_pixels = getWidth() * getHeight();
        int no_pixels_per_task = no_pixels / no_tasks;
        if(task_number == no_tasks-1) {
            no_pixels_per_task = no_pixels - no_pixels_per_task * no_tasks;
        }
        int no_used_pixels = no_pixels_per_task * task_number;

        int start_y = no_used_pixels / getWidth();
        int start_x = no_used_pixels - start_y * getWidth();

        for (int y = start_y; y < getHeight(); y++) {
            for (int x = start_x; x < getWidth(); x++) {
                calculate_pixel(x, y);
                no_pixels_per_task--;
                if (no_pixels_per_task == 0) return;
            }
            start_x = 0;
        }
    }

    public void calculate_pixel(int x, int y) {
        zx = zy = 0;
        cX = (x - 400) / ZOOM;
        cY = (y - 300) / ZOOM;
        int iter = MAX_ITER;
        while (zx * zx + zy * zy < 4 && iter > 0) {
            tmp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = tmp;
            iter--;
        }
        //I.setRGB(x, y, iter | (iter << 8));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        new Mandelbrot().setVisible(true);
    }
}
