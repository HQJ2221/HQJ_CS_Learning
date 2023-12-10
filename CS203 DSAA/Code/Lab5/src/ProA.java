import java.util.Scanner;

public class ProA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            String str = in.next();
            int m = str.length();
            System.out.println(m * (m + 1) / 2);
        }
    }
}