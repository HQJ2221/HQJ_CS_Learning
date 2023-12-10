import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.StringTokenizer;


public class ProA {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        if (T >= 1 && T <= 10) {
            for (int j = 0; j < T; j++) {
                int n = in.nextInt(), m = in.nextInt();
                if (n >= 1 && n <= 100000 && m >= 1 && m <= 100000) {
                    Queue<Long> queue1 = new LinkedList<>();
                    Queue<Long> queue2 = new LinkedList<>();
                    for (int i = 0; i < n; i++) {
                        long num = in.nextLong();
                        if (num >= 0 && num <= 1000000000) {
                            queue1.add(num);
                        }
                    }
                    for (int i = 0; i < m; i++) {
                        long num = in.nextLong();
                        if (num >= 0 && num <= 1000000000) {
                            queue2.add(num);
                        }
                    }
                    while (!queue1.isEmpty() && !queue2.isEmpty()) {
                        if (queue1.peek() < queue2.peek()) {
                            out.print(queue1.poll() + " ");
                        } else {
                            out.print(queue2.poll() + " ");
                        }
                    }
                    if (queue1.isEmpty()) {
                        while (!queue2.isEmpty()) {
                            out.print(queue2.poll() + " ");
                        }
                    } else {
                        while (!queue1.isEmpty()) {
                            out.print(queue1.poll() + " ");
                        }
                    }
                    out.println("");

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
