import java.io.*;
import java.util.StringTokenizer;

public class ProE {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int m = in.nextInt();
        int ai, i = 0;
        Node[] arr = new Node[2000000];
        while (i < 2000000 && (ai = in.nextInt()) != -1) {
            arr[i] = new Node(i, ai);
            i++;
        }
        int maxIndex = findMax(arr, 0, m);
        long res = arr[maxIndex].val;
        for (int j = 1; j <= i - m; j++) {
            if (arr[j + m - 1].val > arr[maxIndex].val) {
                maxIndex = j + m - 1;
                res = res ^ arr[maxIndex].val;
            } else if (maxIndex < j) {
                maxIndex = findMax(arr, j, m);
                res = res ^ arr[maxIndex].val;
            } else {
                res = res ^ arr[maxIndex].val;
            }
        }
        out.println(res);
        out.close();
    }

    static class Node {
        int index;
        int val;

        public Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    static int findMax(Node[] arr, int index, int m) {
        Node max = arr[index];
        for (int j = 1; j < m; j++) {
            if (arr[index + j].val > max.val) {
                max = arr[index + j];
            }
        }
        return max.index;
    }
}

