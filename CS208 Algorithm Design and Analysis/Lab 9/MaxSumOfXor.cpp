#include <iostream>
#include <algorithm>
#include <vector>
#include <chrono>

using namespace std;

inline int readInt();
inline long readLong();
inline void write(long x);
inline void write(int x);

/**
 * Input: n, k
 * Input: following n integers
 * Output: find the maximum sum of XOR for k blocks
 */

/**
 * solution: suppose dp[i][j] is the max sum of XOR when split [1, i] into j blocks
 */

long findXORSum(vector<vector<long>> dp, const long * arr, int n, int k) {
    for (int i = 1; i < k; ++i) {
        for (int j = i; j < n; ++j) {
            long num = 0;
            for (int l = j; l >= i; --l) {
                num = num ^ arr[l];
                dp[j][i] = max(dp[j][i], dp[l - 1][i - 1] + num);
            }
        }
    }
    return dp[n - 1][k - 1];
}

int main() {
    // input
    int n = readInt();
    int k = readInt();
    long * arr = new long[n];
    for (int i = 0; i < n; ++i) {
        arr[i] = readLong();
    }
    vector<vector<long>> dp(n, vector<long>(k, 0));
    dp[0][0] = arr[0];
    for (int i = 1; i < n; ++i) {
        dp[i][0] = dp[i - 1][0] ^ arr[i];
    }
    // output
    write(findXORSum(dp, arr, n, k));
    putchar('\n');
    delete [] arr;
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

