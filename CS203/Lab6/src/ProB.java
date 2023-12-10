import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProB {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int num = in.nextInt();
        boolean[] arr = new boolean[n + 1];
        Node[] arr1 = new Node[n + 1];
        for (int i = 1;i < n+1;i++) {
            arr1[i] = new Node(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int index1 = in.nextInt();
            int index2 = in.nextInt();
            int w = in.nextInt();
            if (index1 == 1 || arr1[index1].parent != null) {
                arr1[index1].son.add(arr1[index2]);
                arr1[index1].sw.add(w);
                arr1[index2].parent = arr1[index1];
                arr1[index2].pw = w;
                arr[index1] = true;
            } else {
                arr1[index2].son.add(arr1[index1]);
                arr1[index2].sw.add(w);
                arr1[index1].parent = arr1[index2];
                arr1[index1].pw = w;
                arr[index2] = true;
            }
        }

        int count = 0;
        for (int i = 1; i < n + 1; i++) {
            if (!arr[i]) {
                Node node = arr1[i];
                long weight = 0;
                while (node.parent != null) {
                    weight += node.pw;
                    node = node.parent;
                }
                if (weight == num) {
                    count++;
                }
            }
        }
        out.println(count);
        out.close();
    }

    static class Node {
        Node parent;
        ArrayList<Node> son = new ArrayList<>();
        int pw = 0;
        ArrayList<Integer> sw = new ArrayList<>();
        int val;

        public Node(int val) {
            this.val = val;
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