import java.util.Scanner;

public class ProF {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String alp = in.nextLine();
        StringBuilder s = new StringBuilder(in.next());
        int l = s.length();
        String[] arr = alp.split(" ");
        s.append("$");
        for (int i = 0; i < l; i++) {
            int index = s.charAt(i) - 'a';
            s.append(arr[index]);
        }
        System.out.println(l - findNext(s.toString()));
    }

    static int findNext(String s) {
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
        return next[next.length - 1];
    }
}
