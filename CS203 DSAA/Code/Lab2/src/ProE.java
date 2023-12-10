import java.util.Scanner;

public class ProE {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n >= 3 && n <= 1000000) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                long num = in.nextLong();
                if (num >= 0 && num <= 1000000000) {
                    arr[i] = num;
                }
            }
            arr = mergeSort(arr, n);
            System.out.println(arr[n / 3]);
            long[] newArr = new long[n];
            int i, index = 0;
            for (i = 0; i < n / 3; i++) {
                if (arr[i] != arr[n / 3]) {
                    newArr[i * 3] = arr[i];
                } else {
                    break;
                }
            }
            for (int j = i; j < n; j++) {
                if (newArr[index] != 0 && index < n - 1) {
                    index++;
                }
                newArr[index++] = arr[j];
            }
            for (int j = 0; j < n; j++) {
                System.out.print(newArr[j] + " ");
            }
        }
    }

    static long[] mergeSort(long[] arr, int n) {
        if (n > 1) {
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
        } else {
            return arr;
        }
    }

    static long[] merge(long[] L, long[] R) {
        long[] arr = new long[L.length + R.length];
        int i = 0, j = 0, k;
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
