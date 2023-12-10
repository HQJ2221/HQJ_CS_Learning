import java.util.Scanner;

public class ProD {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        if (T >= 1 && T <= 10) {
            for (int i = 0; i < T; i++) { // single test case
                int n = in.nextInt();
                int[] arr = new int[n];
                if (n >= 2 && n <= 200000) { // n numbers
                    for (int j = 0; j < n; j++) {
                        int a = in.nextInt();
                        if (a >= -100000 && a <= 100000)
                            arr[j] = a;
                    }
                    int key1 = testRight(arr, 0, n - 1);
                    int key2 = testLeft(arr, 0, key1);
                    int key3 = testLeft(arr, 0, n - 1);
                    int key4 = testRight(arr, key3, n - 1);
                    System.out.println(Math.max(arr[key2] - arr[key1], arr[key3] - arr[key4]));
                }
            }
        }
    }

    static int testRight(int[] arr, int start, int end) {
        int key = end, max = Integer.MIN_VALUE;
        for (int i = end; i > start; i--) {
            if (arr[start] - arr[i] > max) {
                key = i;
                max = arr[start] - arr[i];
            }
        }
        return key;
    }

    static int testLeft(int[] arr, int start, int end) {
        int key = start, max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (arr[i] - arr[end] > max) {
                key = i;
                max = arr[i] - arr[end];
            }
        }
        return key;
    }
}
