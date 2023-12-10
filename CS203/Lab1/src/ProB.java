import java.util.Scanner;

public class ProB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        if (T >= 1 && T <= 100000) {
            for (int i = 0; i < T; i++) {
                long n = in.nextInt();
                if (n >= 1 && n <= 1000000) {
                    System.out.println(n * (n + 1) * (n + 2) / 6);
                }
            }
        }
    }
}
