import java.io.*;
import java.util.StringTokenizer;

public class ProB {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        Node[] queue1 = new Node[p + 1];
        queue1[0] = new Node(-1);
        Node[] queue2 = new Node[q + 1];
        queue2[0] = new Node(-1);
        for (int i = 0; i < p; i++) {
            queue1[i + 1] = new Node(in.nextInt());

        }
        for (int i = 0; i < q; i++) {
            queue2[i + 1] = new Node(in.nextInt());
        }

        int top1 = 1, top2 = 1;
        int[] time = new int[n];
        int i = 0;
        while (top1 <= p && top2 <= q) {
            while (top1 <= p && time[queue1[top1].val - 1] != 0) {
                top1++;
            }
            while (top2 <= q && time[queue2[top2].val - 1] != 0) {
                top2++;
            }
            if (top1 > p || top2 > q) {
                break;
            } else if (queue1[top1].val == queue2[top2].val) {
                time[queue1[top1].val - 1] = i + 1;
                top1++;
                top2++;
            } else {
                time[queue1[top1].val - 1] = i + 1;
                top1++;
                time[queue2[top2].val - 1] = i + 1;
                top2++;
            }
            i++;
        }
        if (top1 > p) {
            while (top2 <= q) {
                while (top2 <= q && time[queue2[top2].val - 1] != 0) {
                    top2++;
                }
                if (top2 <= q) {
                    time[queue2[top2].val - 1] = i + 1;
                    top2++;
                    i++;
                }
            }
        } else {
            while (top1 <= p) {
                while (top1 <= p && time[queue1[top1].val - 1] != 0) {
                    top1++;
                }
                if (top1 <= p) {
                    time[queue1[top1].val - 1] = i + 1;
                    top1++;
                    i++;
                }
            }
        }
        for (i = 0; i < time.length; i++) {
            out.print(time[i] + " ");
        }
        out.close();
    }

    static class Node {
        Node next;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }
}

