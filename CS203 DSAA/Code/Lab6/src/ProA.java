import java.io.*;
import java.util.StringTokenizer;

public class ProA {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        boolean[] arr = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            int index = in.nextInt();
            int val = in.nextInt();
            if (index < n) {
                arr[index - 1] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!arr[i]) {
                out.print((i + 1) + " ");
            }
        }
        out.close();
    }
}

