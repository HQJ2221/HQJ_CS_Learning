import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            node head = new node(-1);
            node temp = head;
            node[] arr = new node[n / 3 + 1];
            int index = 0;
            for (int j = 0; j < n; j++) {
                temp.next = new node(in.nextInt());
                temp.next.prev = temp;
                temp = temp.next;

            }
            if (n > 0) {
                node pt = head.next;
                while (pt != null) {
                    pt = searchLeft(pt);
                    if (pt.next == null) {
                        break;
                    }
                    if (pt.prev != head && (index == 0 || index > 0 && pt.prev != arr[index - 1])) {
                        arr[index++] = pt.prev;
                    }
                    while (pt.next != null && pt.val > pt.next.val) {
                        pt = pt.next;
                    }
                    if (arr[0] == null) {
                        delete(head.next, pt);
                    } else {
                        delete(arr[index - 1].next, pt);
                    }
                    pt = pt.next;
                }
                if (arr[0] != null) {
                    nonDecrease(head, arr);
                }
            }

            temp = head.next;
            while (temp != null) {
                System.out.print(temp.val + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    static node searchLeft(node left) {
        while (left.next != null && left.val <= left.next.val) {
            left = left.next;
        }
        return left;
    }

    static void nonDecrease(node head, node[] arr) {
        node pt = arr[0], pre = arr[0];
        node[] temp1 = new node[arr.length / 3 + 1];
        int newIndex = 0, index = 0;
        while (pt != null) {
            if (pt.next != null && pt.val > pt.next.val) {
                if (pt.prev.val >= 0 && (newIndex == 0 || newIndex > 0 && pt.prev != temp1[newIndex - 1])) {
                    temp1[newIndex++] = pt.prev;
                }
                while (pt.next != null && pt.val > pt.next.val) {
                    if (pt == arr[index]) {
                        index++;
                    }
                    pt = pt.next;
                }
                if (temp1[0] == null) {
                    delete(pre, pt);
                } else {
                    delete(temp1[newIndex - 1].next, pt);
                }
                if (index >= arr.length) break;
                pt = arr[index];
            } else {
                index++;
                if (index >= arr.length) break;
                pt = arr[index];
            }
        }
        if (temp1[0] != null) {
            nonDecrease(head, temp1);
        }
    }

    static void delete(node left, node right) {
        left.prev.next = right.next;
        if (right.next != null) {
            right.next.prev = left.prev;
        }
    }


    static class node {
        Main.node next;
        Main.node prev;
        int val;

        public node(int val) {
            this.val = val;
        }
    }
}

