import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProF {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        if (T >= 1 && T <= 10000) {
            for (int i = 0; i < T; i++) {
                List<String> WList = new ArrayList<>(), BList = new ArrayList<>(),
                        SList = new ArrayList<>(), ZList = new ArrayList<>();
                String s = in.next();
                for (int j = 0; j < 14; j++) {
                    if (s.charAt(2 * j + 1) == 'w') {
                        WList.add(s.substring(2 * j, 2 * j + 2));
                    } else if (s.charAt(2 * j + 1) == 'b') {
                        BList.add(s.substring(2 * j, 2 * j + 2));
                    } else if (s.charAt(2 * j + 1) == 's') {
                        SList.add(s.substring(2 * j, 2 * j + 2));
                    } else {
                        ZList.add(s.substring(2 * j, 2 * j + 2));
                    }
                }
                List<Integer> list = new ArrayList<>();
                list.add(WList.size() % 3);
                list.add(BList.size() % 3);
                list.add(SList.size() % 3);
                list.add(ZList.size() % 3);
                boolean test = list.remove(Integer.valueOf(0)) && list.remove(Integer.valueOf(0))
                        && list.remove(Integer.valueOf(0)) && list.remove(Integer.valueOf(2));
                if (test && testOne(WList) && testOne(BList) && testOne(SList) && testTwo(ZList)) {
                    System.out.println("Blessing of Heaven");
                } else {
                    System.out.println("Bad luck");
                }
            }
        }
    }

    static boolean haveSZ(int[] arr, int i) {
        return Arrays.binarySearch(arr, i + 1) > 0 && Arrays.binarySearch(arr, i + 2) > 0;
    }


    static int KZNum(int[] arr, int i) {
        int num = 0;
        for (int j : arr) {
            if (j == i) num++;
        }
        return num;
    }

    static boolean testOne(List<String> list) {
        int[] arr = new int[list.size()];
        if (list.isEmpty()) {
            return true;
        }
        for (int i = 0; i < arr.length; i++) { // 用数字代替
            arr[i] = Integer.parseInt(list.get(i).substring(0, 1));
        }
        Arrays.sort(arr);
        if (arr.length == 2) {
            return arr[0] == arr[1];
        } else if (arr.length == 3) {
            return haveSZ(arr, arr[0]) || KZNum(arr, arr[0]) == 3;
        } else if (list.size() % 3 == 2) { // 5, 8, 11, 14
            if (arr.length == 5) {
                return test5(arr);
            } else if (arr.length == 8) {
                for (int i = 0; i < arr.length - 1; i++) {
                    int num = KZNum(arr, arr[i]);
                    if (num >= 2) {
                        int[] newArr = new int[arr.length - 2];
                        System.arraycopy(arr, 0, newArr, 0, i);
                        System.arraycopy(arr, i + 2, newArr, i, arr.length - 2 - i);
                        if (test6(newArr)) {
                            return true;
                        } else {
                            i += num - 1;
                        }
                    }
                }
                return false;
            } else if (arr.length == 11) {
                return test11(arr);
            } else {
                for (int i = 0; i < arr.length; i++) {
                    int num = KZNum(arr, arr[i]);
                    if (num >= 2) {
                        int[] newArr = new int[arr.length - 2];
                        System.arraycopy(arr, 0, newArr, 0, i);
                        System.arraycopy(arr, i + 2, newArr, i, arr.length - 2 - i);
                        if (test12(newArr)) {
                            return true;
                        } else {
                            i += num - 1;
                        }
                    }
                }
                return false;
            }
        } else if (list.size() % 3 == 0) { // 6, 9, 12
            if (arr.length == 6) {
                return test6(arr);
            } else if (arr.length == 9) {
                return test9(arr);
            } else if (arr.length == 12) {
                return test12(arr);
            }
        }
        return false;
    }

    static boolean testTwo(List<String> list) {
        int[] arr = new int[list.size()];
        if (list.isEmpty()) {
            return true;
        }
        for (int i = 0; i < arr.length; i++) { // 用数字代替
            arr[i] = Integer.parseInt(list.get(i).substring(0, 1));
        }
        Arrays.sort(arr);
        if (arr.length % 3 == 1) {
            return false;
        } else if (arr.length % 3 == 0) {
            for (int j = 0; j < arr.length - 2; j++) {
                if (arr[j] == arr[j + 1] && arr[j] == arr[j + 2]) {
                    j += 2;
                } else {
                    return false;
                }
            }
            return true;
        } else {
            if (arr.length == 2) {
                return arr[0] == arr[1];
            }
            for (int j = 0; j < arr.length - 1; j++) {
                if (KZNum(arr, arr[j]) % 3 == 2) {
                    int[] newArr = new int[arr.length - 2];
                    System.arraycopy(arr, 0, newArr, 0, j);
                    System.arraycopy(arr, j + 2, newArr, j, arr.length - 2 - j);
                    for (int k = 0; k < newArr.length - 2; k++) {
                        if (newArr[k] == newArr[k + 1] && newArr[k] == newArr[k + 2]) {
                            k += 2;
                        } else {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    static boolean test5(int[] arr) {
        return (arr[0] == arr[1] && (haveSZ(arr, arr[2]) || KZNum(arr, arr[2]) == 3))
                || (arr[3] == arr[4] && (haveSZ(arr, arr[0]) || KZNum(arr, arr[0]) == 3))
                || (haveSZ(arr, arr[0]) && KZNum(arr, arr[1]) == 3) || KZNum(arr, arr[0]) == 5;
    }

    static boolean test6(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int num = KZNum(arr, arr[i]);
            boolean b = haveSZ(arr, arr[i]);
            if (num == 6) {
                return true;
            }
            if (num == 4) {
                return haveSZ(arr, arr[0]);
            } else if (num == 3) {
                int[] newArr = new int[arr.length - 3];
                System.arraycopy(arr, 0, newArr, 0, i);
                System.arraycopy(arr, i + 3, newArr, i, arr.length - 3 - i);
                return haveSZ(newArr, newArr[0]) || KZNum(newArr, newArr[0]) == 3;
            } else if (num == 2 && b) {
                List<Integer> list = new ArrayList<>();
                for (int j : arr) {
                    list.add(j);
                }
                list.remove(Integer.valueOf(arr[i]));
                list.remove(Integer.valueOf(arr[i] + 1));
                list.remove(Integer.valueOf(arr[i] + 2));
                return list.get(0) + 1 == list.get(1) && list.get(1) + 1 == list.get(2);
            }
        }
        return haveSZ(arr, arr[0]) && haveSZ(arr, arr[3]);
    }

    static boolean test9(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int num = KZNum(arr, arr[i]);
            boolean b = haveSZ(arr, arr[i]);
            if (num >= 3) {
                if (b) {
                    List<Integer> list = new ArrayList<>();
                    for (int j : arr) {
                        list.add(j);
                    }
                    list.remove(Integer.valueOf(arr[i]));
                    list.remove(Integer.valueOf(arr[i] + 1));
                    list.remove(Integer.valueOf(arr[i] + 2));
                    int[] newArr = new int[arr.length - 3];
                    for (int j = 0; j < list.size(); j++) {
                        newArr[j] = list.get(j);
                    }
                    if (test6(newArr)) {
                        return true;
                    }
                }
                int[] newArr1 = new int[arr.length - 3];
                System.arraycopy(arr, 0, newArr1, 0, i);
                System.arraycopy(arr, i + 3, newArr1, i, arr.length - 3 - i);
                if (test6(newArr1)) {
                    return true;
                } else {
                    i += num - 1;
                }
            } else if (num == 2) {
                if (b) {
                    List<Integer> list = new ArrayList<>();
                    for (int j : arr) {
                        list.add(j);
                    }
                    list.remove(Integer.valueOf(arr[i]));
                    list.remove(Integer.valueOf(arr[i] + 1));
                    list.remove(Integer.valueOf(arr[i] + 2));
                    int[] newArr = new int[arr.length - 3];
                    for (int j = 0; j < list.size(); j++) {
                        newArr[j] = list.get(j);
                    }
                    if (test6(newArr)) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
        for (int j = 0; j < arr.length - 1; j++) {
            if (arr[j] == arr[j + 1]) {
                return false;
            }
        }
        return true;
    }

    static boolean test11(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int num = KZNum(arr, arr[i]);
            if (i < arr.length - 1 && num >= 2) {
                int[] newArr = new int[arr.length - 2];
                System.arraycopy(arr, 0, newArr, 0, i);
                System.arraycopy(arr, i + 2, newArr, i, arr.length - 2 - i);
                if (test9(newArr)) {
                    return true;
                } else {
                    i += num - 1;
                }
            }
        }
        return false;
    }

    static boolean test12(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int num = KZNum(arr, arr[i]);
            boolean b = haveSZ(arr, arr[i]);
            if (num >= 3) {
                if (b) {
                    List<Integer> list = new ArrayList<>();
                    for (int j : arr) {
                        list.add(j);
                    }
                    list.remove(Integer.valueOf(arr[i]));
                    list.remove(Integer.valueOf(arr[i] + 1));
                    list.remove(Integer.valueOf(arr[i] + 2));
                    int[] newArr = new int[arr.length - 3];
                    for (int j = 0; j < newArr.length; j++) {
                        newArr[j] = list.get(j);
                    }
                    if (test9(newArr)) {
                        return true;
                    }
                }
                int[] newArr = new int[arr.length - 3];
                System.arraycopy(arr, 0, newArr, 0, i);
                System.arraycopy(arr, i + 3, newArr, i, arr.length - 3 - i);
                if (test9(newArr)) {
                    return true;
                } else {
                    i += num - 1;
                }
            } else if (num == 2 && b) {
                List<Integer> list = new ArrayList<>();
                for (int j : arr) {
                    list.add(j);
                }
                list.remove(Integer.valueOf(arr[i]));
                list.remove(Integer.valueOf(arr[i] + 1));
                list.remove(Integer.valueOf(arr[i] + 2));
                int[] newArr = new int[arr.length - 3];
                for (int j = 0; j < list.size(); j++) {
                    newArr[j] = list.get(j);
                }
                return test9(newArr);
            }
        }
        return false;
    }
}
