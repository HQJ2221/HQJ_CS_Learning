import java.io.*;
import java.util.StringTokenizer;

public class ProB {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter out = new QWriter();
        int max = 100001;
        int[] A = new int[max];
        int n = input.nextInt();
        if (n <= 1000000) {
            for (int i = 0; i < n; i++) {
                int a = input.nextInt();
                if (a <= max && a >= 0) {
                    A[a]++;
                }
            }
            int T = input.nextInt();
            if (T <= 1000000) {
                int[] B = new int[T];
                for (int i = 0; i < T; i++) {
                    B[i] = input.nextInt();
                }

                for (int i = 0; i < T; i++) {
                    if (B[i] > 100000 || A[B[i]] == 0 || B[i] < 0) {
                        out.println("no");
                    } else {
                        out.println("yes");
                    }
                }
            }
            out.close();
        }
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}