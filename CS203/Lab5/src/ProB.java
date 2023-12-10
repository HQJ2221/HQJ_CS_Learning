import java.util.Scanner;

public class ProB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int[] next = new int[s.length() + 1];
        next[0] = -1;
        int k = -1, j = 0;
        while (j < s.length()) {
            if (k == -1 || s.charAt(k) == s.charAt(j)) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        for (int i = 1; i < next.length; i++) {
            System.out.println(next[i]);
        }
    }
}
