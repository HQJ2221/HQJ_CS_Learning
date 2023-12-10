import java.util.Scanner;

public class ProA {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            boolean legal = true;
            int index = 0;
            int n = in.nextInt();
            char[] Stack = new char[n];
            String str = in.next();
            for (int j = 0; j < n; j++) {
                if (str.charAt(j) == '{' || str.charAt(j) == '[' || str.charAt(j) == '(') {
                    Stack[index++] = str.charAt(j);
                } else if (str.charAt(j) == '}' || str.charAt(j) == ']' || str.charAt(j) == ')') {
                    if (index <= 0) {
                        legal = false;
                        break;
                    } else {
                        if (str.charAt(j) == '}' && Stack[index - 1] == '{') {
                            index--;
                        } else if (str.charAt(j) == ']' && Stack[index - 1] == '[') {
                            index--;
                        } else if (str.charAt(j) == ')' && Stack[index - 1] == '(') {
                            index--;
                        } else {
                            legal = false;
                            break;
                        }
                    }
                }
            }
            if (index == 0 && legal) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}