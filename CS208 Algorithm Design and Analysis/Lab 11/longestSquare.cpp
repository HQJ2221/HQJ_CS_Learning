#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#define INT_MAX 0x7fffffff

using namespace std;

inline int readInt();

inline long readLong();

inline void write(long x);

inline void write(int x);

/**
 * Input: n * m matrix
 * Output: find the longest square with 1
 */
int findLongestSquare(vector<vector<int>> mat) {
    vector<vector<int>> dp(mat.size(), vector<int>(mat[0].size(), 0));
    for (int i = 0; i < mat[0].size(); ++i) {
        dp[0][i] = mat[0][i];
    }
    int max = 0;
    for (int i = 1; i < mat.size(); ++i) {
        dp[i][0] = mat[i][0];
        for (int j = 1; j < mat[0].size(); ++j) {
            if (mat[i][j] == 0) {
                dp[i][j] = 0;
            } else {
                dp[i][j] = min(min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
    }
    return max;
}

int main() {
    // input
    int n = readInt();
    int m = readInt();
    vector<vector<int>> matrix(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            matrix[i][j] = readInt();
        }
    }
    // output
    write(findLongestSquare(matrix));
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
