import java.io.*;
import java.util.*;

public class test {
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
        List<Node> allLeaves = new ArrayList<>();
        List<Node> targets = new ArrayList<>();
        for (Node son : root.son) {
            son.parent = root;
            son.son.remove(root);

            List<Node> leaves = construct(son);
            Node tar = leaves.get(0);
            for (int j = 1; j < leaves.size(); j++) {
                if (leaves.get(j).maxValue > tar.maxValue) {
                    tar = leaves.get(j);
                } else if (leaves.get(j).maxValue == tar.maxValue) {
                    tar = tar.value > leaves.get(j).value ? tar : leaves.get(j);
                }
            }
            targets.add(tar);
            leaves.remove(tar);
            allLeaves.addAll(leaves);
        }

        int total = 0;

        if (targets.size() == 1) {
            total += root.value * 2;
            findMax(targets.get(0));
            for (Node leaf : allLeaves) {
                total += findMax(leaf);
            }
        } else {
            Node first = targets.get(0), second = targets.get(0);
            for (int i = 1; i < targets.size(); i++) {
                if (targets.get(i).maxValue > first.maxValue) {
                    second = first;
                    first = targets.get(i);
                } else if (targets.get(i).maxValue >= second.maxValue) {
                    second = targets.get(i);
                }
            }
            total += root.value * 2;
            findMax(first);
            findMax(second);
            targets.remove(first);
            targets.remove(second);
            for (Node leaf : targets) {
                total += findMax(leaf);
            }
            for (Node leaf : allLeaves) {
                total += findMax(leaf);
            }
        }
        System.out.println(total);
    }

    static List<Node> construct(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<Node> leaf = new ArrayList<>();
        queue.add(root);
        root.maxValue = root.value;
        while (!queue.isEmpty()) {
            Node parent = queue.poll();
            parent.maxValue = Math.max(parent.parent.maxValue, parent.value);
            if (parent.son.isEmpty()) {
                leaf.add(parent);
            }
            for (Node son : parent.son) {
                queue.add(son);
                son.son.remove(parent);
                son.parent = parent;
            }
        }
        return leaf;
    }

    static int findMax(Node leaf) {
        int max = 0;
        Node tmp = leaf;
        while (tmp != null && !tmp.isCheck) {
            max = Math.max(max, tmp.value);
            tmp.isCheck = true;
            tmp = tmp.parent;
        }
        return max;
    }


    static class Node {
        List<Node> son = new ArrayList<>();
        Node parent = null;
        int value;
        int maxValue = 0;
        boolean isCheck = false;

        public Node() {
        }
    }
}

