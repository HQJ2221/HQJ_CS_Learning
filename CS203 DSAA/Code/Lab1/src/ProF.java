import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long x1 = in.nextInt(), y1 = in.nextInt(), x2 = in.nextInt(), y2 = in.nextInt();
        if (x1 >= 0 && x1 <= 1000000000 && y1 >= 0 && y1 <= 1000000000 &&
                x2 >= 0 && x2 <= 1000000000 && y2 >= 0 && y2 <= 1000000000 && !(x1 == x2 && y1 == y2)) {
            int N = in.nextInt();
            if (N >= 1 && N <= 100000) {
                String s = in.next();
                char[] arr = s.toCharArray();
                System.out.println(getMinSteps(arr, x1, y1, x2, y2));
            }
        }
    }

    static long getMinSteps(char[] arr, long x1, long y1, long x2, long y2) {
        long turn = 0, left = 1, right = (long) Math.pow(10, 14) + 1;
        while (left < right) {
            turn = (left + right) / 2;
            if (Math.abs(getFinal(arr, x2, y2, turn)[0] - x1) + Math.abs(getFinal(arr, x2, y2, turn)[1] - y1) <= turn) {
                right = turn;
            } else {
                left = turn + 1;
            }
        }
        return left > (long) Math.pow(10, 14) ? -1 : left;
    }

    static long[] getFinal(char[] arr, long x2, long y2, long turn) {
        long[] finalPos = {x2, y2};
        long cir = turn / arr.length;
        long rem = turn % arr.length;
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case 'U':
                    finalPos[1] += i < rem ? cir + 1 : cir;
                    break;
                case 'D':
                    finalPos[1] -= i < rem ? cir + 1 : cir;
                    break;
                case 'L':
                    finalPos[0] -= i < rem ? cir + 1 : cir;
                    break;
                case 'R':
                    finalPos[0] += i < rem ? cir + 1 : cir;
                    break;
            }
        }
        return finalPos;
    }
}
