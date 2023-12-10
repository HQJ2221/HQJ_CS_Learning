import java.io.*;
import java.util.StringTokenizer;

public class ProF {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int k = in.nextInt();
        int n = in.nextInt();
        int max = 0, count = 0, firstIndex = 1;
        Node head1 = new Node(0, 2000000001); // decreasing queue
        Node head2 = new Node(0, -1); // increasing queue
        Node temp1 = head1, temp2 = head2;

        for (int i = 0; i < n; i++) {
            int value = in.nextInt();
            Node node1 = new Node(i + 1, value);
            Node node2 = new Node(i + 1, value);
            count++;
            if (temp1 == head1) { // decreasing in queue
                temp1.next = node1;
                temp1 = temp1.next;
                temp1.prev = head1;
            } else if (temp1.val > node1.val) {
                temp1.next = node1;
                node1.prev = temp1;
                temp1 = temp1.next;
            } else {
                do {
                    temp1 = temp1.prev;
                } while (temp1.val <= node1.val);
                temp1.next = node1;
                node1.prev = temp1;
                temp1 = temp1.next;
            }

            if (temp2 == head2) { // increasing in queue
                temp2.next = node2;
                temp2 = temp2.next;
                temp2.prev = head2;
            } else if (temp2.val < node2.val) {
                temp2.next = node2;
                node2.prev = temp2;
                temp2 = temp2.next;
            } else {
                do {
                    temp2 = temp2.prev;
                } while (temp2.val >= node2.val);
                temp2.next = node2;
                node2.prev = temp2;
                temp2 = temp2.next;
            }

            if (head1.next.val - head2.next.val > k) {
                max = Math.max(max, count - 1);
                if (head1.next.index > head2.next.index) {
                    do {
                        firstIndex = head2.next.index + 1;
                        head2.next = head2.next.next;
                        head2.next.prev = head2;
                    } while (head1.next.val - head2.next.val > k);
                    count = (i + 1) - firstIndex + 1;
                } else {
                    do {
                        firstIndex = head1.next.index + 1;
                        head1.next = head1.next.next;
                        head1.next.prev = head1;
                    } while (head1.next.val - head2.next.val > k);
                    count = (i + 1) - firstIndex + 1;
                }
            }
        }
        max = Math.max(max, count);
        out.println(max);
        out.close();
    }

    static class Node {
        Node next;
        Node prev;
        int index;
        int val;

        public Node(int index, int val) {
            this.index = index;
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