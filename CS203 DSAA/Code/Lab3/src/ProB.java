import java.io.*;
import java.util.StringTokenizer;

public class ProB {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();

        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            String s = in.next();
            char[] arr = s.toCharArray();

            node head = new node(-1);
            node temp = head;
            for (int j = 0; j < n; j++) {
                switch (arr[j]) {
                    case 'r': {
                        if (j < n - 1) {
                            node re = new node(Integer.parseInt(String.valueOf(arr[j + 1])));
                            if (temp.next != null && temp.next.next != null) {
                                re.next = temp.next.next;
                            }
                            temp.next = re;
                            re.prev = temp;
                            if (temp.next.next != null) {
                                temp.next.next.prev = re;
                            }
                            j += 1;
                        }
                        break;
                    }
                    case 'I': {
                        temp = head;
                        break;
                    }
                    case 'H': {
                        if (temp != head) {
                            temp = temp.prev;
                        }
                        break;
                    }
                    case 'L': {
                        if (temp.next != null) {
                            temp = temp.next;
                        }
                        break;
                    }
                    case 'x': {
                        if (temp.next != null) {
                            if (temp.next.next != null) {
                                temp.next = temp.next.next;
                                temp.next.prev = temp;
                            } else {
                                temp.next = null;
                            }
                        }
                        break;
                    }
                    default: {
                        int num = Integer.parseInt(String.valueOf(arr[j]));
                        node re = new node(num);
                        if (temp.next == null) {
                            temp.next = re;
                            re.prev = temp;
                        } else {
                            re.next = temp.next;
                            temp.next = re;
                            re.prev = temp;
                            re.next.prev = re;
                        }
                        temp = temp.next;
                        break;
                    }
                }
            }

            temp = head;
            while (temp.next != null) {
                out.print(temp.next.val);
                temp = temp.next;
            }
            out.println("");
        }
        out.close();
    }

    static class node {
        node next;
        node prev;
        int val;

        public node(int val) {
            this.val = val;
        }
    }
}

