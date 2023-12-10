import java.util.Scanner;

public class ProC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            Node[] preorder = new Node[n];
            Node[] inorder = new Node[n];
            for (int j = 0; j < n; j++) {
                int val = in.nextInt();
                preorder[j] = new Node(val);
            }
            for (int j = 0; j < n; j++) {
                int val = in.nextInt();
                inorder[j] = new Node(val);
            }
            Node root = findOrder(preorder, inorder);
            printPost(root);
            System.out.println();
        }
    }

    static Node findOrder(Node[] pre, Node[] in) {
        if (in.length == 0) {
            return null;
        }
        if (in.length == 1) {
            return in[0];
        }
        int i = 0;
        while (i < in.length && in[i].val != pre[0].val) {
            i++;
        }
        Node[] preFront = new Node[i];
        Node[] preBack = new Node[in.length - i - 1];
        Node[] front = new Node[i];
        Node[] back = new Node[in.length - i - 1];
        for (int j = 0; j < i; j++) {
            preFront[j] = new Node(pre[j + 1].val);
            front[j] = new Node(in[j].val);
        }
        for (int j = 0; j < in.length - i - 1; j++) {
            preBack[j] = new Node(pre[j + i + 1].val);
            back[j] = new Node(in[j + i + 1].val);
        }
        pre[0].left = findOrder(preFront, front);
        pre[0].right = findOrder(preBack, back);
        return pre[0];
    }

    static void printPost(Node root) {
        if (root.left != null) {
            printPost(root.left);
        }
        if (root.right != null) {
            printPost(root.right);
        }
        System.out.print(root.val + " ");
    }

    static class Node {
        Node left, right;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }
}


//1
//5
//1 2 4 3 5
//2 4 1 5 3