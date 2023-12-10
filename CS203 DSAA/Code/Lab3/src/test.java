import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String base_str = in.nextLine();
        int n = in.nextInt();
        StringBuilder result = new StringBuilder(base_str);
        for (int i = 0; i < n; i++) {
            String x = in.next();
            if (x.equals("I")) {
                String y = in.next();
                int z = in.nextInt();
                result.insert(z, y);
            }
            if (x.equals(("D"))) {
                String original = in.nextLine();
                String trim = original.trim();
                if (trim.contains(" ")) {
                    String[] v = trim.split(" ");
                    int y = Integer.parseInt(v[0]);
                    int z = Integer.parseInt(v[1]);
                    result.delete(y, z);
                } else {
                    int y = Integer.parseInt(trim);
                    result.deleteCharAt(y);
                }
            }
            if (x.equals("U")) {
                String y = in.next();
                String z = in.next();
                String u = result.toString();
                if (u.contains(z)) {
                    result = new StringBuilder(u.replace(z, y));
                } else {
                    int p = Integer.parseInt(z);
                    result.replace(p, p + 1, y);
                }
            }
            System.out.printf("%s\n", result);
        }
    }
}