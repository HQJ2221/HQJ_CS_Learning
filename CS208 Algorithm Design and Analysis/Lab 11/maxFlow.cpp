#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <cstdio>
#include <queue>

#define INF 0x7fffffff

using namespace std;

inline int readInt();

inline long readLong();

inline void write(long x);

inline void write(int x);

/**
 * Input: n projects with m prerequisites, each project has a revenue
 * Output: find the min value changes with min operations to make the array increase
 */
int min(int a, int b) { // compare two numbers and return the smaller one
    return a > b ? b : a;
}

int n, m, s, t, ans;
queue<int> q;

int bfs(vector<vector<int>> &g, vector<int> &pre, vector<int> &dis) { // find the shortest path from s to t
    for (int i = 0; i <= n + 1; i++) pre[i] = -1;
    while (!q.empty()) q.pop();
    q.push(s);
    dis[s] = INF;
    while (!q.empty()) {
        int x = q.front();
        q.pop();
        if (x == t) break; // find the sink
        for (int i = 0; i <= n + 1; i++) {
            if (pre[i] == -1 && g[x][i]) { // if the point is not visited and there is a path from x to i
                pre[i] = x;
                dis[i] = min(dis[x], g[x][i]); // update the distance
                q.push(i);
            }
        }
    }
    if (pre[t] == -1) return -1; // if there is no path from s to t
    else return dis[t];
}

void EK(vector<vector<int>> &g, vector<int> &pre, vector<int> &dis) {
    int inc;
    while (true) {
        inc = bfs(g, pre, dis);
        if (inc == -1) break;
        int k = t;
        while (k != s) {
            g[k][pre[k]] += inc;
            g[pre[k]][k] = max(g[pre[k]][k] - inc, 0);
            k = pre[k];
        }
        ans += inc;
    }
}

int main() {
    // input
    n = readInt();
    m = readInt();
    vector<vector<int>> graph(n + 2, vector<int>(n + 2, 0));
    vector<int> pre(n + 2, 0);
    vector<int> dis(n + 2, 0);
    s = 0; t = n + 1;
    for (int i = 0; i < n; i++) {
        int rev = readInt();
        if (rev > 0) {
            graph[0][i + 1] = rev;
        } else {
            graph[i + 1][n + 1] = -rev;
        }
    }
    for (int i = 0; i < m; i++) {
        int a = readInt();
        int b = readInt();
        graph[b][a] = INF;
    }
    int c = 0;
    for (int i = 0; i < n + 2; ++i) {
        c += graph[0][i];
    }
    // solve
    EK(graph, pre, dis);
    write(c - ans);
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
