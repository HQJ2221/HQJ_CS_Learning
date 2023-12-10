import java.io.*;
import java.util.*;

public class ProA {
    public static void main(String[] args) {
        QWriter out = new QWriter();
        QReader in = new QReader();
        int n = in.nextInt();
        int m = in.nextInt();

        node headn = new node(-1, -1);
        node itern = headn;
        for (int i = 0; i < n; i++) {
            node tmp = new node(in.nextInt(), in.nextInt());
            itern.next = tmp;
            itern = itern.next;
        }
        node tailn = new node(-1, -1);
        itern.next = tailn;

        node headm = new node(-1, -1);
        node iterm = headm;
        for (int i = 0; i < m; i++) {
            node tmp = new node(in.nextInt(), in.nextInt());
            iterm.next = tmp;
            iterm = iterm.next;
        }
        node tailm = new node(-1, -1);
        iterm.next = tailm;

        itern = headn.next;
        iterm = headm.next;

        int count = 0;
        while (itern != tailn && iterm != tailm) {
            if (itern.exp > iterm.exp) {
                count++;
                itern = itern.next;
            } else if (itern.exp < iterm.exp) {
                count++;
                iterm = iterm.next;
            } else {
                iterm = iterm.next;
            }
        }
        if (itern == tailn) {
            while (iterm != tailm) {
                count++;
                iterm = iterm.next;
            }
        } else {
            while (itern != tailn) {
                count++;
                itern = itern.next;
            }
        }

        out.println(count);
        itern = headn.next;
        iterm = headm.next;

        while (itern != tailn && iterm != tailm) {
            if (itern.exp > iterm.exp) {
                out.println(itern.coe + " " + itern.exp);
                itern = itern.next;
            } else if (itern.exp < iterm.exp) {
                out.println(iterm.coe + " " + iterm.exp);
                iterm = iterm.next;
            } else {
                if (itern.coe + iterm.coe != 0) {
                    out.println((itern.coe + iterm.coe) + " " + itern.exp);
                }
                itern = itern.next;
                iterm = iterm.next;
            }
        }

        if (itern == tailn) {
            while (iterm != tailm) {
                out.println(iterm.coe + " " + iterm.exp);
                iterm = iterm.next;
            }
        } else {
            while (itern != tailn) {
                out.println(itern.coe + " " + itern.exp);
                itern = itern.next;
            }
        }
        out.close();
    }

    static class node {
        node next;
        int coe;
        int exp;
        public node(int coe, int exp) {
            this.coe = coe;
            this.exp = exp;
        }
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}