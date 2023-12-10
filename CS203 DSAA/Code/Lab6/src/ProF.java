import java.util.*;

public class ProF {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Node[] arr = new Node[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = new Node();
        }
        for (int i = 0; i < n - 1; i++) {
            int index1 = in.nextInt();
            int index2 = in.nextInt();
            arr[index1].son.add(arr[index2]);
            arr[index2].son.add(arr[index1]);
        }
        int max = -1;
        int rootIndex = 0;
        for (int i = 1; i < n + 1; i++) {
            int val = in.nextInt();
            if (val > max) {
                max = val;
                rootIndex = i;
            }
            arr[i].value = val;
        }

        Node root = arr[rootIndex];
        long total = 0;
        if (root.son.size() == 1) {
            root.son.get(0).parent = root;
            root.son.get(0).son.remove(root);
            dfs1(root.son.get(0));
            dfs2(root.son.get(0));
            for (int i = 1; i < n + 1; i++) {
                if (arr[i].son.isEmpty()) {
                    total += arr[i].maxValue;
                }
            }
            System.out.println(total + 2 * root.value - root.son.get(0).maxValue);
        } else {
            long first = 0, second = 0;
            for (Node son : root.son) {
                son.parent = root;
                son.son.remove(root);
                dfs1(son);
                dfs2(son);
                if (son.maxValue > first) {
                    second = first;
                    first = son.maxValue;
                } else if (son.maxValue > second) {
                    second = son.maxValue;
                }
            }
            for (int i = 1; i < n + 1; i++) {
                if (arr[i].son.isEmpty()) {
                    total += arr[i].maxValue;
                }
            }
            System.out.println(total + 2 * root.value - first - second);
        }
    }

    static void dfs1(Node root) {
        root.maxValue = root.value;
        for (Node son : root.son) {
            son.parent = root;
            son.son.remove(root);
            dfs1(son);
            if (son.maxValue > root.maxValue) {
                root.maxValue = son.maxValue;
            }
        }
    }

    static void dfs2(Node root) {
        if (!root.son.isEmpty()) {
            Node maxNode = root.son.get(0);
            for (int i = 1; i < root.son.size(); i++) {
                if (root.son.get(i).maxValue > maxNode.maxValue) {
                    maxNode = root.son.get(i);
                }
            }
            maxNode.maxValue = root.maxValue;
            for (Node son : root.son) {
                dfs2(son);
            }
        }
    }

    static class Node {
        List<Node> son = new ArrayList<>();
        Node parent = null;
        long value;
        long maxValue = 0;

        public Node() {
        }
    }
}

