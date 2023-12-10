import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        if (s1.length() < s2.length()) {
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        // use binary search to find the longest common substring of the two players' names
        int l = 0, r = s2.length();
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (check(s1, s2, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(l);
    }

    static boolean check(String s1, String s2, int len) {
        for (int i = 0; i < s1.length() - len + 1; i++) {
            for (int j = 0; j < s2.length() - len + 1; j++) {
                if (s1.charAt(i + j) != s2.charAt(j)) {
                    break;
                }
            }
        }
        return false;
    }
}
