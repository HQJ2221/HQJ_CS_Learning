import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CutTheArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int[] b = new int[a];

        for (int i = 0; i < a; i++) {
            b[i] = input.nextInt();
        }

        int max = 0;//假设最大元素为第一个
        int min = 0;//假设最小元素为最后一个
        for (int i = 1; i < a - 1; i++) {

            max = b[0];
            for (int j = 1; j <= i; j++) {
                if (b[j] > max) {
                    max = b[j];
                }
            }

            min = b[i + 1];
            for (int j = i + 1; j < a; j++) {
                if (b[j] < min) {
                    min = b[j];
                }
            }

            if (max < min) {

                int[] d = new int[i + 1];
                for (int j = 0; j < i + 1; j++) {
                    d[j] = b[j];
                }

                Set set = new HashSet();//定义一个set集合
                int count = 0;//set集合元素个数
                for (int k = 0; k < d.length; k++) {
                    set.add(d[k]);
                }
                Object arr[] = set.toArray();
                for (int k = 0; k < arr.length; k++) {
                    System.out.print(arr[k] + " ");
                }

                break;

            } else if (i == a - 2) {
                Set set = new HashSet();

                for (int k = 0; k < b.length; k++) {
                    set.add(b[k]);
                }
                Object arr[] = set.toArray();
                for (int k = 0; k < arr.length; k++) {
                    System.out.print(arr[k] + " ");

                }

            }
//            else if (i == a-2) {
//                int [] d = new int [i+1];
//                for (int j = 0; j < i+1; j++) {
//                    d[j] = b[j];
//                }
//                int temp = 0;
//                for (int j = 0; j <i; j++) {
//                    for (int k = 0; k < i-j; k++) {
//                        if(d[k] > d[k+1]){
//                            temp = d[k];
//                            d[k] = d[k+1];
//                            d[k+1] = temp;
//                        }
//                    }
//                }
//                for (int j = 0; j < i; j++) {
//                    if (d[j] != d[j+1]) {
//                        System.out.print(d[j] + " ");
//                    }
//                }
//                if (d[i] != d[i-1]){
//                    System.out.println(d[i]);
//                }
//            }


        }
    }


}

//    第三题WA case1的可以试试这个数据：
//        Input
//        12
//        8 6 4 2 0 0 1 3 5 8 9 11
//
//        Output
//        0 1 2 3 4 5 6 8
