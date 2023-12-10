import java.util.*;

public class ProE {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Node[] arr = new Node[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = new Node(false);
        }
        for (int i = 0; i < n - 1; i++) {
            int index1 = in.nextInt();
            int index2 = in.nextInt();
            arr[index1].son.add(arr[index2]);
            arr[index2].son.add(arr[index1]);
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(arr[1]);
        while(!queue.isEmpty()) {
            Node tmp = queue.poll();
            for (Node son : tmp.son) {
                for (int i = 0; i < son.son.size(); i++) {
                    if (son.son.get(i) == tmp) {
                        son.parent = son.son.remove(i);
                        break;
                    }
                }
                queue.add(son);
            }
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int tmp = in.nextInt();
            arr[tmp].hasGiant = true;
        }
        int minDay = 0;
        for (Node son : arr[1].son) {
            minDay = Math.max(minDay, findMin(son));
        }
        System.out.println(minDay);
    }

    static int findMin(Node root) {
        List<Integer> list = new ArrayList<>();
        int level = 1;
        if (root.hasGiant) {
            list.add(level);
        } else {
            list.add(0);
        }
        Queue<Node> queue = new LinkedList<>();
        int n = 0, time = 0;
        for (Node son : root.son) {
            queue.add(son);
            time++;
        }
        while (!queue.isEmpty()) {
            if (n == 0) {
                level++;
                n = time;
                time = 0;
            }
            Node son = queue.poll();
            if (!son.son.isEmpty()) {
                for (Node node : son.son) {
                    queue.add(node);
                    time++;
                }
            }
            if (son.hasGiant) {
                list.add(level);
            }
            n--;
        }
        for (int i = 1; i < list.size(); i++) {
            list.set(i, Math.max(list.get(i), list.get(i - 1) + 1));
        }
        return list.get(list.size() - 1);
    }

    static class Node {
        ArrayList<Node> son = new ArrayList<>();
        Node parent;
        boolean hasGiant;

        public Node(boolean b) {
            this.hasGiant = b;
        }
    }
}