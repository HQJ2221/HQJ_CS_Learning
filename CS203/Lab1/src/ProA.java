import java.util.Scanner;

public class ProA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n >= 1 && n <= 100000) {
            long[] arr = new long[n];
            for (int i = 0; i < arr.length; i++) {
                long a = in.nextLong();
                if (a >= 0 && a <= Math.pow(10, 9)) {
                    arr[i] = a;
                }
            }
            int T = in.nextInt();
            if (T >= 1 && T <= 100000) {
                for (int j = 0; j < T; j++) {
                    long test = in.nextLong();
                    if (test >= 0 && test <= Math.pow(10, 9)) {
                        if (contain(arr, test)) {
                            System.out.println("YES");
                        } else {
                            System.out.println("NO");
                        }
                    }
                }
            }
        }
    }

    static boolean contain(long[] arr, long test) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] == test) {
                return true;
            }
            if (arr[m] < test) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }
}