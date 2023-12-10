import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        if (m > 0) {
            int n = in.nextInt();
            if (n >= m && n < 100000) {
                long total = 0;
                boolean[] notPrime = new boolean[n + 1];
                for (int i = 2; i <= n / 2; i++) {
                    if (!notPrime[i]) {
                        for (int j = i * 2; j <= n; j += i) {
                            notPrime[j] = true;
                        }
                    }
                }
                for (int i = 2; i < Math.sqrt(n); i++) {
                    for (int j = i + 1; j <= n / 2; j++) {
                        if (!notPrime[i] && (!notPrime[j] || i * i == j) && i * j <= n && i * j >= m) {
                            total += (long)i * j;
                        }
                    }
                }
                System.out.println(total);
            }
        }
    }
}