import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProC {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        String s = in.next();
        int[][] next = FSA(s);
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < 26; j++) {
                out.print(next[i][j] + " ");
            }
            out.println("");
        }
        out.close();
    }

    static int[][] FSA(String s) { // FSA
        int[][] matrix =  new int[s.length()][26];
        char[] P = s.toCharArray();
        int x = 0;
        for (int i = 0; i < 26; i++) {
            if (P[0] - 'a' == i) {
                matrix[0][i] = 1;
            } else {
                matrix[0][i] = 0;
            }
        }
        for (int j = 1; j < P.length; j++) {
            for (int i = 0; i < 26; i++) {
                if (P[j] - 'a' == i) {
                    matrix[j][i] = j + 1;
                } else {
                    matrix[j][i] = matrix[x][i];
                }
            }
            x = matrix[x][P[j] - 'a'];
        }
        return matrix;
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