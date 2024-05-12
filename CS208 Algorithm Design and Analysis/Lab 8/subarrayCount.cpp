#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <chrono>

using namespace std;

inline int readInt();
inline long readLong();
inline void write(long x);
inline void write(int x);

/**
 * Input: n
 * Input: n integers
 * Output: find count of Max[l, r] - Min[l, r] = r - l
 */

/**
 * Key method: to find cross pair in O(n)
 */
long cross(long *arr, int l, int mid, int r, int n) {
    long count = 0;
    int i = mid, j1 = mid + 1, j2 = mid + 1;
    // left_min, left_max, right_min, right_max
    long *LL = new long[mid - l + 1];
    long *LG = new long[mid - l + 1];
    long *RL = new long[r - mid];
    long *RG = new long[r - mid];
    unordered_map<long, int> values;
    LL[mid - l] = arr[mid];
    LG[mid - l] = arr[mid];
    RL[0] = arr[mid + 1];
    RG[0] = arr[mid + 1];
    for (int j = mid - 1; j >= l; --j) {
        LL[j - l] = min(arr[j], LL[j - l + 1]);
        LG[j - l] = max(arr[j], LG[j - l + 1]);
    }
    for (int j = mid + 2; j <= r; ++j) {
        RL[j - mid - 1] = min(arr[j], RL[j - mid - 2]);
        RG[j - mid - 1] = max(arr[j], RG[j - mid - 2]);
    }
    // first loop
    while (i >= l) {
        while (j1 <= r && LL[i - l] < RL[j1 - mid - 1]) {
            if (values.find(RG[j1 - mid - 1] - j1) == values.end()) {
                values[RG[j1 - mid - 1] - j1] = 0;
            }
            values[RG[j1 - mid - 1] - j1]++; // count
            j1++;
        }
        while (j2 < j1 && LG[i - l] > RG[j2 - mid - 1]) {
            values[RG[j2 - mid - 1] - j2]--; // count
            j2++;
        }
        long tmp = LG[i - l] - LL[i - l] + i;
        if (j2 != mid + 1 && LG[i - l] > RG[j2 - mid - 2] && LL[i - l] < RL[j2 - mid - 2]) {
            if (tmp > mid && tmp < j2) {
                count++;
            }
        }
        if (j2 > r) {
            i--;
            while (i >= l) {
                tmp = LG[i - l] - LL[i - l] + i;
                if (tmp > mid && tmp < j2) {
                    count++;
                }
                i--;
            }
            break;
        }
        if (LL[i - l] < RL[j2 - mid - 1] && LG[i - l] < RG[j2 - mid - 1] ||
            LL[i - l] > RL[j2 - mid - 1] && LG[i - l] > RG[j2 - mid - 1]) {
            if (values.find(LL[i - l] - i) != values.end()) {
                count += values[LL[i - l] - i];
            }
            i--;
        }
    }
    i = mid + 1;
    j1 = mid;
    j2 = mid;
    values.clear();
    // second loop
    while (i <= r) {
        while (j1 >= l && RL[i - mid - 1] < LL[j1 - l]) {
            if (values.find(LG[j1 - l] + j1) == values.end()) {
                values[LG[j1 - l] + j1] = 0;
            }
            values[LG[j1 - l] + j1]++;
            j1--;
        }
        while (j2 > j1 && RG[i - mid - 1] > LG[j2 - l]) {
            values[LG[j2 - l] + j2]--;
            j2--;
        }
        long tmp = i - RG[i - mid - 1] + RL[i - mid - 1];
        if (j2 != mid && RG[i - mid - 1] > LG[j2 - l + 1] && RL[i - mid - 1] < LL[j2 - l + 1]) {
            if (tmp > j2 && tmp <= mid) {
                count++;
            }
        }
        if (j2 < l) {
            i++;
            while (i <= r) {
                tmp = i - RG[i - mid - 1] + RL[i - mid - 1];
                if (tmp > j2 && tmp <= mid) {
                    count++;
                }
                i++;
            }
            break;
        }
        if (RL[i - mid - 1] < LL[j2 - l] && RG[i - mid - 1] < LG[j2 - l] ||
            RL[i - mid - 1] > LL[j2 - l] && RG[i - mid - 1] > LG[j2 - l]) {
            if (values.find(RL[i - mid - 1] + i) != values.end()) {
                count += values[RL[i - mid - 1] + i];
            }
            i++;
        }
    }
    delete[] LL;
    delete[] LG;
    delete[] RL;
    delete[] RG;
    return count;
}

/**
 * divide and conquer
 */
long count(long *arr, int l, int r, int n) {
    if (l == r) {
        return 1;
    } else if (l + 1 == r) {
        long temp = abs(arr[r] - arr[l]);
        return temp == r - l ? 3 : 2;
    }
    int mid = (l + r) / 2;
    long left = count(arr, l, mid, n);
    long right = count(arr, mid + 1, r, n);
    return left + right + cross(arr, l, mid, r, n);
}

int main() {
    // input
    int n = readInt();
    long *arr = new long[n + 1];
    for (int i = 1; i < n + 1; ++i) {
        arr[i] = readLong();
    }
    // output
    long cnt = count(arr, 1, n, n);
    write(cnt);
    putchar('\n');
    delete[] arr;
    return 0;
}

inline int readInt() {
    int x = 0, f = 1;
    char ch = getchar();
    while (ch < '0' || ch > '9') {
        if (ch == '-') {
            f = -1;
        }
        ch = getchar();
    }
    while (ch >= '0' && ch <= '9') {
        x = x * 10 + (ch - '0');
        ch = getchar();
    }
    return x * f;
}

inline long readLong() {
    long x = 0;
    int f = 1;
    char ch = getchar();
    while (ch < '0' || ch > '9') {
        if (ch == '-') {
            f = -1;
        }
        ch = getchar();
    }
    while (ch >= '0' && ch <= '9') {
        x = x * 10 + (ch - '0');
        ch = getchar();
    }
    return x * f;
}

inline void write(long x) {
    if (x < 0) {
        putchar('-');
        x = -x;
    }
    if (x > 9) {
        write(x / 10);
    }
    putchar(x % 10 + '0');
}

inline void write(int x) {
    write((long) x);
}

