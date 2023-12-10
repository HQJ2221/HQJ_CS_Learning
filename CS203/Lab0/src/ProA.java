import java.util.Scanner;

public class ProA {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        long[] A = new long[n];

        for (int i = 0; i < n; i++) {
            A[i] = input.nextInt();
        }

        int T = input.nextInt();
        long[] B = new long[T];

        for (int i = 0; i < T; i++){
            B[i] = input.nextInt();
        }

        for (long value : B) {
            boolean isEqual = false;
            for (long value2 : A){
                if (value == value2) {
                    isEqual = true;
                    System.out.println("yes");
                    break;
                }
            }
            if (!isEqual) {
                System.out.println("no");
            }
        }
    }
}