import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int lengthA = in.nextInt();
        int lengthB = in.nextInt();
        int K = in.nextInt();
        Node[] A = new Node[lengthA];
        Node[] B = new Node[lengthB];
        for (int i = 0; i < lengthA; i++) {
            A[i] = new Node(in.nextLong(), i + 1);
        }
        for (int i = 0; i < lengthB; i++) {
            B[i] = new Node(in.nextLong(), i + 1);
        }
        A = mergeSort(A, lengthA);
        Node[] arr = new Node[lengthB];
        for (int i = 0; i < lengthB; i++) {
            arr[i] = new Node(A[0].value * B[i].value, i + 1);
            arr[i].indexA = 0;
            arr[i].indexB = i;
        }
        arr = mergeSort(arr, arr.length);
        int num = 0, index = 0;
        while(num < K) {
            out.print(arr[0].value + " ");
            if (arr[0].indexA + 1 < lengthA) {
                arr[0].value = A[arr[0].indexA + 1].value * B[arr[0].indexB].value;
                arr[0].indexA++;
            } else {
                arr[0].value =Long.MAX_VALUE;
            }
            while((index + 1) * 2 - 1 < arr.length) {
                if ((index + 1) * 2 < arr.length) {
                    if (arr[index].value <= Math.min(arr[(index + 1) * 2 - 1].value, arr[(index + 1) * 2].value)) {
                        break;
                    }
                    if (arr[(index + 1) * 2 - 1].value > arr[(index + 1) * 2].value) {
                        Node tmp = arr[(index + 1) * 2];
                        change(arr[index], tmp);
                        index = (index + 1) * 2;
                    } else {
                        Node tmp = arr[(index + 1) * 2 - 1];
                        change(arr[index], tmp);
                        index = (index + 1) * 2 - 1;
                    }
                } else {
                    if (arr[index].value <= arr[(index + 1) * 2 - 1].value) {
                        break;
                    } else {
                        Node tmp = arr[(index + 1) * 2 - 1];
                        change(arr[index], tmp);
                        index = (index + 1) * 2 - 1;
                    }
                }
            }
            num++;
            index = 0;
        }

        out.close();
    }

    static void change(Node a, Node b) {
        long temp = a.value;
        a.value = b.value;
        b.value = temp;
        int index = a.indexA;
        a.indexA = b.indexA;
        b.indexA = index;
        index = a.indexB;
        a.indexB = b.indexB;
        b.indexB = index;
    }

    static Node[] mergeSort(Node[] arr, int n) {
        if (n > 2) {
            int mid = n / 2;
            Node[] left = new Node[mid];
            Node[] right = new Node[n - mid];
            for (int i = 0; i < mid; i++) {
                left[i] = arr[i];
            }
            for (int i = mid; i < n; i++) {
                right[i - mid] = arr[i];
            }
            left = mergeSort(left, mid);
            right = mergeSort(right, n - mid);
            return merge(left, right);
        } else if (n == 2) {
            if (arr[0].value > arr[1].value) {
                Node temp = arr[0];
                arr[0] = arr[1];
                arr[1] = temp;
            }
            return arr;
        } else {
            return arr;
        }
    }

    static Node[] merge(Node[] L, Node[] R) {
        Node[] arr = new Node[L.length + R.length];
        int i = 0, j = 0, k = 0;
        for (k = 0; k < arr.length; k++) {
            if (i < L.length && (j >= R.length || L[i].value <= R[j].value)) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
        }
        return arr;
    }

    static class Node {
        long value;
        Node left, right;
        int index;
        int indexA = -1, indexB = -1;

        public Node(long value, int index) {
            this.value = value;
            this.index = index;
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