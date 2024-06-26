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
 * Input: n size array
 * Output: find the min value changes with min operations to make the array increase
 */

pair<int, int> minOperationsToIncreaseArray(vector<int> &A, int * arr, int n) {
    vector<vector<pair<int, int>>> dp(A.size(), vector<pair<int, int>>(n, {INT_MAX, INT_MAX}));
    dp[0][0] = A[0] == arr[0] ? pair<int, int>{0, 0} : pair<int, int>{1, abs(A[0] - arr[0])};
    for (int i = 1; i < n; ++i) {
        if (A[0] == arr[i]) {
            dp[0][i] = {0, 0};
        } else if (dp[0][i - 1].first == 0) {
            dp[0][i] = {0, 0};
        } else {
            dp[0][i] = {1, abs(A[0] - arr[i])};
        }
    }
    for (int i = 1; i < A.size(); ++i) {
        int val = arr[0];
        dp[i][0] = val == A[i] ? dp[i - 1][0] : pair<int, int>{dp[i - 1][0].first + 1,
                                                               dp[i - 1][0].second + abs(A[i] - val)};
        for (int j = 1; j < n; ++j) {
            val = arr[j];
            if (A[i] == val) {
                dp[i][j] = dp[i - 1][j];
            } else {
                if (dp[i - 1][j].first + 1 > dp[i][j - 1].first) {
                    dp[i][j] = dp[i][j - 1];
                } else if (dp[i - 1][j].first + 1 < dp[i][j - 1].first) {
                    dp[i][j] = {dp[i - 1][j].first + 1, dp[i - 1][j].second + abs(A[i] - val)};
                } else if (dp[i - 1][j].second + abs(A[i] - val) < dp[i][j - 1].second) {
                    dp[i][j] = {dp[i - 1][j].first + 1, dp[i - 1][j].second + abs(A[i] - val)};
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
    }
    return dp[A.size() - 1][n - 1];
}

int main() {
    // input
    int n = readInt();
    vector<int> A(n);
    int arr[n];
    for (int i = 0; i < n; ++i) {
        A[i] = readInt() - (i + 1);
        arr[i] = A[i];
    }
    sort(arr, arr + n, less<int>());
    n = unique(arr, arr + n) - arr;
    pair<int, int> result = minOperationsToIncreaseArray(A, arr, n);
    write(result.first);
    putchar('\n');
    write(result.second);
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
