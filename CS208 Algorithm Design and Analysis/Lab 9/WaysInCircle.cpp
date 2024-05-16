#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <vector>
#include <chrono>

using namespace std;

inline int readInt();
inline long readLong();
inline void write(long x);
inline void write(int x);

/**
 * Input: T
 * Input: T test cases in which each test case contains n, m
 * Output: find the ways to return from 0 to 0 (in a circle from 0 to n-1) in m steps
 */

void countWay(int n, int m, std::vector<std::vector<long>> &dp) {
    for (int i = 1; i < m + 1; ++i) {
        for (int j = 0; j < n; ++j) {
            dp[j][i] = (dp[(j - 1 + n) % n][i - 1] + dp[(j + 1) % n][i - 1]) % 1000000007;
        }
    }
}

int main() {
    // input
    int T = readInt();
    for (int t = 0; t < T; t++) {
        int n = readInt();
        int m = readInt();
        // output
        std::vector<std::vector<long>> dp(n, std::vector<long>(m + 1, 0));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = 0;
            }
        }
        dp[0][0] = 1;
        countWay(n, m, dp);
        write(dp[0][m]);
        putchar('\n');
    }
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

