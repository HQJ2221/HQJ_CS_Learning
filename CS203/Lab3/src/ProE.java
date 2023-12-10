import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProE {
    public static void main(String[] args) {
        QReader in = new QReader();
        int n = in.nextInt();
        node[] arr = new node[n];
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            arr[i] = new node(val, i);
        }
        node[] arr1 = merge(arr, n);
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                arr1[i].next = arr1[i + 1];
            } else if (i == n - 1) {
                arr1[i].prev = arr1[i - 1];
            } else {
                arr1[i].next = arr1[i + 1];
                arr1[i].prev = arr1[i - 1];
            }
        }

        QWriter out = new QWriter();
        for (int i = 0; i < n - 1; i++) {
            node temp = arr[i];
            if (temp.next != null && temp.prev != null) {
                out.print(Math.min(temp.val - temp.prev.val, temp.next.val - temp.val) + " ");
                temp.next.prev = temp.prev;
                temp.prev.next = temp.next;
            } else if (temp.next == null && temp.prev != null) {
                out.print(temp.val - temp.prev.val + " ");
                temp.prev.next = null;
            } else if (temp.next != null && temp.prev == null) {
                out.print(temp.next.val - temp.val + " ");
                temp.next.prev = null;
            }
        }
        out.close();
    }

    static class node {
        node next;
        node prev;
        int val;
        int index;

        public node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    static node[] merge(node[] Node, int n) {
        if (n == 1) {
            return Node;
        } else {
            int mid = n / 2;
            node[] left = new node[mid];
            node[] right = new node[n - mid];
            for (int i = 0; i < mid; i++) {
                left[i] = Node[i];
            }
            for (int i = mid; i < n; i++) {
                right[i - mid] = Node[i];
            }
            left = merge(left, mid);
            right = merge(right, n - mid);
            return mergeSort(left, right);
        }
    }

    static node[] mergeSort(node[] left, node[] right) {
        node[] arr = new node[left.length + right.length];
        int j = 0, k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (k >= right.length || (j < left.length && left[j].val <= right[k].val)) {
                arr[i] = left[j];
                j++;
            } else {
                arr[i] = right[k];
                k++;
            }
        }
        return arr;
    }
}

