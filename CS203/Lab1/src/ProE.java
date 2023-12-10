import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProE {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int L = in.nextInt(); // whole length
            int n = in.nextInt(); // point between
            int m = in.nextInt(); // nums of racer
            if (L >= 1 && L <= 1000000000 && n >= 0 && n <= 500000 && m >= 1 && m <= n + 1) {
                int[] arr = new int[n + 1];
                for (int i = 0; i < n; i++) {
                    arr[i] = in.nextInt(); // points to the start line
                }
                arr[n] = L;
                Arrays.sort(arr);
                System.out.println(getMinLongest(arr, L, m));
            }
        }
    }

    static int getMinLongest(int[] arr, int L, int m) {
        if (m == 1 || arr.length == 1) {
            return L;
        } else if (m == arr.length + 1) {
            int[] distances = new int[arr.length];
            int last = 0;
            for (int i = 0; i < distances.length; i++) {
                distances[i] = arr[i] - last;
                last = arr[i];
            }
            Arrays.sort(distances);
            return distances[distances.length - 1];
        } else {
            return test(arr, L, m);
        }
    }

    static int test(int[] arr, int L, int m) {
        List<Integer> list = new ArrayList<>();
        for (int j : arr) {
            list.add(j);
        }
        int left = L / m, right = L, mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (check(list, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    static boolean check(List<Integer> list, int mid, int m) {
        int count = 0;
        int last = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) - last > mid) {
                count++;
                if (last == list.get(i - 1)){
                    return false;
                } else {
                    last = list.get(i - 1);
                    i--;
                }
            }
        }
        if (list.get(list.size() - 1) - last <= mid) {
            count++;
        }
        return count <= m;
    }
}
