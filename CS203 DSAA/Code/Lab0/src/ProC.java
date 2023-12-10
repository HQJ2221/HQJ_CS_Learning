import java.util.Scanner;

public class ProC {
    public static void main(String[] args) {
        // arrange mahjong.
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        if (T >= 1 && T <= 200) {
            String[][] str = new String[T][13];
            for (int i = 0; i < T; i++) {
                for (int j = 0; j < 13; j++) {
                    str[i][j] = in.next();
                }
            }
            for (int i = 0; i < T; i++) {
                compare(str[i]);
                for (int j = 0; j < 13; j++) {
                    System.out.print(str[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    static void compare(String[] s1) {
        for (int i = 0; i < 13; i++) {
            for (int j = i + 1; j < 13; j++) {
                int val1 = getVal(s1[i]);
                int val2 = getVal(s1[j]);
                if (val1 < val2) {
                    String temp = s1[i];
                    s1[i] = s1[j];
                    s1[j] = temp;
                }
            }
        }
    }

    static int getVal(String s) {
        if (s.length() != 1) {
            if (s.startsWith("W")) {
                return 35 - Integer.parseInt(s.substring(1));
            }
            if (s.startsWith("T")) {
                return 26 - Integer.parseInt(s.substring(1));
            }
            if (s.startsWith("Y")) {
                return 17 - Integer.parseInt(s.substring(1));
            }
        }
        if (s.startsWith("E")) {
            return 7;
        }
        if (s.startsWith("S")) {
            return 6;
        }
        if (s.startsWith("W")) {
            return 5;
        }
        if (s.startsWith("N")) {
            return 4;
        }
        if (s.startsWith("B")) {
            return 3;
        }
        if (s.startsWith("F")) {
            return 2;
        }
        if (s.startsWith("Z")) {
            return 1;
        }
        return 0;
    }
}
