import java.util.Scanner;

public class ProC {
    static long count = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        if (T >= 1 && T <= 10) {
            for (int i = 0; i < T; i++) {
                count = 0;
                int n = in.nextInt();
                if (n >= 1 && n <= 100000) {
                    long[] arr = new long[n];
                    for (int j = 0; j < n; j++) {
                        arr[j] = in.nextLong();
                    }
                    mergeSort(arr, n);
                    System.out.println(count);
                }
            }
        }
    }

    static long[] mergeSort(long[] arr, int n) {
        if (n > 2) {
            int mid = n / 2;
            long[] left = new long[mid];
            long[] right = new long[n - mid];
            for (int i = 0; i < mid; i++) {
                left[i] = arr[i];
            }
            for (int i = mid; i < n; i++) {
                right[i - mid] = arr[i];
            }
            left = mergeSort(left, mid);
            right = mergeSort(right, n - mid);
            return merge(left, right);
        } else if (n == 2) {
            if (arr[0] > arr[1]) {
                long temp = arr[0];
                arr[0] = arr[1];
                arr[1] = temp;
                count++;
            }
            return arr;
        } else {
            return arr;
        }
    }

    static long[] merge(long[] L, long[] R) {
        long[] arr = new long[L.length + R.length];
        int i = 0, j = 0, k;
        for (k = 0; k < arr.length; k++) {
            if (i < L.length && j < R.length && L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else if (i < L.length && j >= R.length) {
                arr[k] = L[i];
                i++;
            } else if (i >= L.length && j < R.length) {
                arr[k] = R[j];
                j++;
            } else {
                arr[k] = R[j];
                count += j + L.length - k;
                j++;
            }
        }
        return arr;
    }
}
