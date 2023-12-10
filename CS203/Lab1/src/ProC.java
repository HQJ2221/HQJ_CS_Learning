import java.util.Scanner;

public class ProC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int Q = in.nextInt();
        if (N >= 1 && N <= 100000 && Q >= 1 && Q <= 100000) {
            long[] arr = new long[N];
            for (int i = 0; i < arr.length; i++) {
                long a = in.nextInt();
                if (a >= 1 && a <= 1000000000) {
                    arr[i] = a;
                }
            }
            for (int j = 0; j < Q; j++) {
                int x = in.nextInt();
                int y = in.nextInt();
                if (x >= 1 && y >= x && y <= 1000000000) {
                    int low = binarySearch1(arr, x);
                    int high = binarySearch2(arr, y);
                    if (y == x || high - low <= 1) {
                        System.out.println("NO");
                    } else {
                        System.out.println("YES " + (high - low - 1));
                    }
                }
            }
        }
    }

    static int binarySearch1(long[] arr, int x) {
        int l = 0, r = arr.length - 1;
        int mid;
        while (r - l > 1) {
            mid = (l + r) / 2;
            if (x < arr[mid]) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return (x >= arr[l] && x <= arr[r]) ? ((x == arr[r]) ? r : l) : ((x < arr[0]) ? -1 : arr.length);
    }

    static int binarySearch2(long[] arr, int y) {
        int l = 0, r = arr.length - 1;
        int mid;
        while (r - l > 1) {
            mid = (l + r) / 2;
            if (y <= arr[mid]) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return (y >= arr[l] && y <= arr[r]) ? ((y == arr[l]) ? l : r) : (y > arr[arr.length - 1] ? arr.length : -1);
    }
}
