import java.util.Scanner;

public class ProC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        Node[] arr = new Node[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = new Node(str.charAt(i));
        }
        long[] num = new long[arr.length / 2 + 1];
        int dep = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].val == '(' && i == 0 || arr[i].val == '(' && arr[i - 1].val == '(') {
                dep++;
            } else if (arr[i].val == ')' && arr[i - 1].val == '(') {
                num[dep] = (num[dep] + 1) % 514329;
            } else if (arr[i].val == ')' && arr[i - 1].val == ')') {
                long temp = (num[dep] * 2) % 514329;
                num[dep] = 0;
                dep--;
                num[dep] = (num[dep] + temp) % 514329;
            }
        }
        System.out.println(num[1] % 514329);
    }

    static class Node {
        char val;
        public Node(char val) {
            this.val = val;
        }
    }
}
