import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProE {
    private static final int BASE = 131;
    private static final int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        if (s1.length() < s2.length()) {
            String tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
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
        Map<Long, Integer> map = new HashMap<>();
        long h = 0, p = 1;
        for (int i = 0; i < len; i++) {
            h = (h * BASE + s1.charAt(i)) % MOD;
            p = p * BASE % MOD;
        }
        map.put(h, 0);
        for (int i = len; i < s1.length(); i++) {
            h = (h * BASE + s1.charAt(i) - s1.charAt(i - len) * p % MOD + MOD) % MOD;
            map.put(h, i - len + 1);
        }
        h = 0;
        for (int i = 0; i < len; i++) {
            h = (h * BASE + s2.charAt(i)) % MOD;
        }
        if (map.containsKey(h) && s1.substring(map.get(h), map.get(h) + len).equals(s2.substring(0, len))) {
            return true;
        }
        for (int i = len; i < s2.length(); i++) {
            h = (h * BASE + s2.charAt(i) - s2.charAt(i - len) * p % MOD + MOD) % MOD;
            if (map.containsKey(h) && s1.substring(map.get(h), map.get(h) + len).equals(s2.substring(i - len + 1, i + 1))) {
                return true;
            }
        }
        return false;
    }
}
