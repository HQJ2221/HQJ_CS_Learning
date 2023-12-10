import java.util.Scanner;

public class ProE {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        if (T >= 1 && T <= 100) {
            for (int i = 0; i < T; i++) {
                int l = in.nextInt(); // length
                int w = in.nextInt(); // width
                int h = in.nextInt(); // height
                if (l >= 1 && l <= 30 && w >= 1 && w <= 30 && h >= 1 && h <= 30) {
                    char[][] cube = new char[2 * h + 2 * w + 1][2 * l + 2 * w + 1];
                    for (int j = 0; j < 2 * h + 2 * w + 1; j++) {
                        for (int k = 0; k < 2 * l + 2 * w + 1; k++) {
                            if (((k <= 2 * l && j >= 2 * w) || (k > 2 * l && j >= 2 * w + 2 * l - k && j <= 2 * w + 2 * l + 2 * h - k)
                                    || (j < 2 * w && k >= 2 * w - j && k <= 2 * w + 2 * l - j)) && j % 2 == 0 && k % 2 == 0) {
                                cube[j][k] = '+';
                                System.out.print(cube[j][k]);
                            } else if ((j < 2 * w - 1 && k >= 2 * w - j && k <= 2 * w - j + 2 * l && k % 2 == 1 && j % 2 == 0) ||
                                    (j >= 2 * w - 1 && k <= 2 * l && k % 2 == 1 && j % 2 == 0)) {
                                cube[j][k] = '-';
                                System.out.print(cube[j][k]);
                            } else if (((k <= 2 * l && j > 2 * w) || (k > 2 * l && j > 2 * w + 2 * l - k && j < 2 * w + 2 * l + 2 * h - k))
                                    && k % 2 == 0 && j % 2 == 1) {
                                cube[j][k] = '|';
                                System.out.print(cube[j][k]);
                            } else if (((j < 2 * w && k >= 2 * w - j && k <= 2 * w + 2 * l - j)
                                    || (k > 2 * l && j >= 2 * w + 2 * l - k && j <= 2 * w + 2 * l + 2 * h - k))
                                    && k % 2 == 1 && j % 2 == 1) {
                                cube[j][k] = '/';
                                System.out.print(cube[j][k]);
                            } else {
                                cube[j][k] = '.';
                                System.out.print(cube[j][k]);
                            }
                        }
                        System.out.println();
                    }
                }
            }
        }
    }
}
