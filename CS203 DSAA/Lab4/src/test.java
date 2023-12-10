import java.util.Random;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Random ran = new Random();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String a = in.next();
            int k = ran.nextInt(0, 2000000001);
            int n = ran.nextInt(1, 100);
            System.out.println(k + " " + n);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = ran.nextInt(1, 2000000001);
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            int len = 0;
            for (int i = 0; i < n; i++) {
                int temp = 1;
                int min = arr[i];
                int max = arr[i];
                for (int j = i + 1; j < n; j++) {
                    min = Math.min(min, arr[j]);
                    max = Math.max(max, arr[j]);
                    if (max - min > k) {
                        break;
                    }
                    temp++;
                }
                len = Math.max(len, temp);
            }
            System.out.println(len);
        }
    }
}
