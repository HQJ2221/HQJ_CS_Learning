import java.util.Scanner;

public class ProB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] arr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = in.nextInt();
            int time = 0;
            int index = i;
            while(index > 1) {
                if (arr[index] > arr[index / 2]) {
                    time++;
                    int tmp = arr[index];
                    arr[index] = arr[index / 2];
                    arr[index / 2] = tmp;
                    index /= 2;
                } else {
                    break;
                }
            }
            System.out.print(time + " ");
        }
    }
}