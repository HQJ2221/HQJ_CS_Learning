import java.util.Scanner;

public class ProC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            node head = new node(-1);
            node temp = head;
            node[] arr = new node[n];
            for (int j = 0; j < n; j++) {
                int val = in.nextInt();
                node node = new node(val);
                arr[node.val] = node;
                temp.next = node;
                node.prev = temp;
                temp = temp.next;
            }
            for (int j = 0; j < m; j++) {
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();
                exchange(arr, x1, y1, x2, y2);
            }

            temp = head.next;
            while (temp != null) {
                System.out.print(temp.val + " ");
                temp = temp.next;
            }
            System.out.println();
        }

    }

    static class node {
        node prev;
        node next;
        int val;
        public node(int val) {
            this.val = val;
        }
    }

    static void exchange(node[] arr, int x1, int y1, int x2, int y2) {
        if (arr[y1].next == arr[x2]) {
            arr[x1].prev.next = arr[x2];
            arr[y1].next = arr[y2].next;
            arr[x2].prev = arr[x1].prev;
            if (arr[y2].next != null) {
                arr[y2].next.prev = arr[y1];
            }
            arr[y2].next = arr[x1];
            arr[x1].prev = arr[y2];
        } else {
            node temp1 = arr[y1].next;
            node temp2 = arr[x2].prev;
            arr[x1].prev.next = arr[x2];
            arr[y1].next = arr[y2].next;
            arr[x2].prev = arr[x1].prev;
            if (arr[y2].next != null) {
                arr[y2].next.prev = arr[y1];
            }
            arr[y2].next = temp1;
            temp1.prev = arr[y2];
            arr[x1].prev = temp2;
            temp2.next = arr[x1];
        }
    }
}
