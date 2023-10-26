package Lab4.Zad1;

public class Buffer {
    int[] processing_line;

    public Buffer(int N) {
        this.processing_line = new int[N];
        for (int i=0;i<N;i++) {
            this.processing_line[i] = -1;
        }
    }

    public int get(int position) {
        return  processing_line[position];
    }
}
