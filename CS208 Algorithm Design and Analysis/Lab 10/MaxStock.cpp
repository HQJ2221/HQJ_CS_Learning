#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

inline int readInt();

inline long readLong();

inline void write(long x);

inline void write(int x);

/**
 * Input: n, m, money, stock[n][m]
 * Output: find the maximum sum of XOR for k blocks
 */
int maxStock(vector<vector<long>> stock, int n, int m, long money) {
    for (int i = 0; i < m - 1; ++i) {
        vector<vector<long>> dp = vector<vector<long>>(n + 1, vector<long>(money + 1, 0));
        for (int j = 1; j < n + 1; ++j) {
            for (int k = 1; k < money + 1; ++k) {
                if (stock[j - 1][i] > k) {
                    dp[j][k] = dp[j - 1][k];
                } else {
                    dp[j][k] = max(dp[j - 1][k], dp[j][k - stock[j - 1][i]] + stock[j - 1][i + 1] - stock[j - 1][i]);
                }
            }
        }
        money += dp[n][money];
    }
    return money;
}

int main() {
    // input
    int n = readInt();
    int m = readInt();
    long money = readLong();
    vector<vector<long>> stock = vector<vector<long>>(n, vector<long>(m));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            stock[i][j] = readLong();
        }
    }
    // output
    write(maxStock(stock, n, m, money));
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

