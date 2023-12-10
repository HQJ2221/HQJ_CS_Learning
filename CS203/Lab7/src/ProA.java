import java.util.*;

public class ProA {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            boolean isHeap = true;
            boolean isLarger = false, isCheck = false;
            int N = in.nextInt();
            Node[] arr = new Node[N + 1];
            for (int j = 1; j < N + 1; j++) {
                long val = in.nextLong();
                arr[j] = new Node(val);
            }
            for (int j = 0; j < N - 1; j++) {
                int index1 = in.nextInt();
                int index2 = in.nextInt();
                arr[index1].son.add(arr[index2]);
                arr[index2].parent = arr[index1];
                if (arr[index1].son.size() > 2) {
                    isHeap = false;
                }
                if (!isCheck) {
                    if (arr[index1].value > arr[index2].value) {
                        isLarger = true;
                        isCheck = true;
                    } else if (arr[index1].value < arr[index2].value) {
                        isCheck = true;
                    }
                } else {
                    if (isLarger && arr[index1].value < arr[index2].value) {
                        isHeap = false;
                    } else if (!isLarger && arr[index1].value > arr[index2].value) {
                        isHeap = false;
                    }
                }
            }

            Node root = null;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j].parent == null) {
                    root = arr[j];
                    root.index = 1;
                    break;
                }
            }

            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()) {
                Node tmp = queue.poll();
                if (tmp.son.isEmpty()) {
                    continue;
                } else {
                    int temp = tmp.index * 2;
                    if (temp > N) {
                        isHeap = false;
                        break;
                    } else {
                        tmp.son.get(0).index= temp;
                        queue.add(tmp.son.get(0));
                    }
                    if (tmp.son.size() == 2) {
                        temp++;
                        if (temp > N) {
                            isHeap = false;
                            break;
                        } else {
                            tmp.son.get(1).index= temp;
                            queue.add(tmp.son.get(1));
                        }
                    }
                }
            }

            if (!isHeap) {
                out.println("Case #" + (i + 1) + ": NO");
            } else {
                out.println("Case #" + (i + 1) + ": YES");
            }
        }
        out.close();
    }

    static class Node {
        List<Node> son = new ArrayList<>();
        Node parent = null;
        long value;
        int index;

        public Node(long val) {
            this.value = val;
        }
    }
}

