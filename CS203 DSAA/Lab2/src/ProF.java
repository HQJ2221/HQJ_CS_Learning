import java.util.Scanner;

public class ProF {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        if (n >= 1 && n <= 200000 && p >= 0 && p <= 20 && q >= 0 && q <= 200000) {
            Plant[] arr = new Plant[n];
            final int P = (int) Math.pow(2, p);
            long total = 0;
            long diff = Long.MIN_VALUE;
            int maxIndex = 0;
            for (int i = 0; i < n; i++) {
                long h = in.nextLong();
                long s = in.nextLong();
                if (h >= 0 && h <= 1000000000 && s >= 0 && s <= 1000000000) {
                    total += s;
                    arr[i] = new Plant(h - s, h * P - s);
                }
            }
            arr = mergeSort(arr, n);
            for (int i = 0; i < n; i++) {
                if (arr[i].getLast() >= diff) {
                    diff = arr[i].getLast();
                    maxIndex = i;
                }
            }
            if (maxIndex >= q - 1) {
                if (arr[maxIndex].getLast() > 0 && q > 0) {
                    total += arr[maxIndex].getLast();
                    q--;
                }
                for (int i = 0; q > 0; i++) {
                    if (arr[i].getInit() > 0) {
                        total += arr[i].getInit();
                        q--;
                    } else {
                        break;
                    }
                }
            } else {
                for (int i = maxIndex + 1; i < arr.length; i++) {
                    if (i < q) {
                        if (arr[i].getLast() > 0 &&
                                arr[maxIndex].getLast() + (arr[i].getInit() >= 0 ? arr[i].getInit() : 0) < arr[maxIndex].getInit() + arr[i].getLast()) {
                            maxIndex = i;
                            diff = arr[i].getLast();
                        }
                    } else {
                        if (arr[i].getLast() > 0 && arr[i].getLast() > diff) {
                            maxIndex = i;
                            diff = arr[i].getLast();
                        }
                    }
                }
                if (arr[maxIndex].getLast() > 0 && q > 0) {
                    total += arr[maxIndex].getLast();
                    q--;
                }

                for (int i = 0; i < arr.length; i++) {
                    if (q > 0 && arr[i].getInit() > 0) {
                        if (i != maxIndex ) {
                            total += arr[i].getInit();
                            q--;
                        }
                    } else {
                        break;
                    }
                }
            }
            System.out.println(total);
        }
    }

    static Plant[] mergeSort(Plant[] arr, int n) {
        if (n > 2) {
            int mid = n / 2;
            Plant[] left = new Plant[mid];
            Plant[] right = new Plant[n - mid];
            for (int i = 0; i < mid; i++) {
                left[i] = arr[i];
            }
            for (int i = mid; i < n; i++) {
                right[i - mid] = arr[i];
            }
            left = mergeSort(left, mid);
            right = mergeSort(right, n - mid);
            return merge(left, right);
        } else if (n == 2) {
            if (arr[0].getInit() < arr[1].getInit()) {
                Plant temp = arr[0];
                arr[0] = arr[1];
                arr[1] = temp;
            } else if (arr[0].getInit() == arr[1].getInit()) {
                if (arr[0].getLast() > arr[1].getLast()) {
                    Plant temp = arr[0];
                    arr[0] = arr[1];
                    arr[1] = temp;
                }
            }
            return arr;
        } else {
            return arr;
        }
    }

    static Plant[] merge(Plant[] L, Plant[] R) {
        Plant[] arr = new Plant[L.length + R.length];
        int i = 0, j = 0, k;
        for (k = 0; k < arr.length; k++) {
            if (i < L.length && (j >= R.length
                    || L[i].getInit() > R[j].getInit() || (L[i].getInit() == R[j].getInit()
                    && L[i].getLast() <= R[j].getLast()))) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
        }
        return arr;
    }
}

class Plant {
    long iniDiff, cDiff;

    public Plant(long iniDiff, long cDiff) {
        this.iniDiff = iniDiff;
        this.cDiff = cDiff;
    }

    public long getInit() {
        return this.iniDiff;
    }

    public long getLast() {
        return this.cDiff;
    }
}
