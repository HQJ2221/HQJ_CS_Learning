import java.util.Scanner;

public class ProB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n >= 1 && n <= 5000000) {
            long[] arr = new long[n];
            for (int i = 0; i < arr.length; i++) {
                int temp = in.nextInt();
                if (temp >= 0) {
                    arr[i] = temp;
                }
            }
            if (n % 2 == 0) {
                long[] newArr = mergeSort(arr, n);
                System.out.println((newArr[n / 2 - 1] + newArr[n / 2]));
            } else {
                System.out.println((mergeSort(arr, n)[n / 2] * 2));
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
            }
            return arr;
        } else {
            return arr;
        }
    }

    static long[] merge(long[] L, long[] R) {
        long[] arr = new long[L.length + R.length];
        int i = 0, j = 0, k = 0;
        for (k = 0; k < arr.length; k++) {
            if (i < L.length && (j >= R.length || L[i] <= R[j])) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
        }
        return arr;
    }
}