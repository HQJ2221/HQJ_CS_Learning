#include <iostream>
#include <algorithm>
#include <cmath>

using namespace std;
inline int readInt();
inline long readLong();
inline void write(long x);
inline void write(int x);

/**
 * Input: n, m, long * arr
 * In following m lines, input: l, r
 * Output: for each query, find the maximum sum of subarray
 */

// build tree to store the information of each node
// for each query, use binary search to confirm the area

struct node {
    int l, r;
    long sum, lmax, rmax, tmax;
};

void merge(node& n, node& l, node& r) {
    n.sum = l.sum + r.sum;
    n.lmax = max(l.lmax, l.sum + r.lmax);
    n.rmax = max(r.rmax, r.sum + l.rmax);
    n.tmax = max(l.tmax, max(r.tmax, l.rmax + r.lmax));
    n.l = l.l;
    n.r = r.r;
}

node * buildTree(node* tree, long arr[], int l, int r, int i) {
    if (l == r) {
        tree[i].l = l;
        tree[i].r = r;
        tree[i].sum = arr[l - 1];
        tree[i].lmax = arr[l - 1];
        tree[i].rmax = arr[l - 1];
        tree[i].tmax = arr[l - 1];
        return tree + i;
    }
    int mid = (l + r) / 2;
    node left = *buildTree(tree, arr, l, mid, 2 * i);
    node right = *buildTree(tree, arr, mid + 1, r, 2 * i + 1);
    merge(tree[i], left, right);
    return tree + i;
}

node* search(int left, int right, node* tree, int i) {
    if (left <= tree[i].l && right >= tree[i].r) {
        node * n = new node;
        *n = tree[i];
        return n;
    } else if (left > tree[i].r || right < tree[i].l) {
        return nullptr;
    } else {
        node * l = search(left, right, tree, 2 * i);
        node * r = search(left, right, tree, 2 * i + 1);
        node * n = new node;
        if (l == nullptr) {
            *n = *r;
            delete r;
            return n;
        } else if (r == nullptr) {
            *n = *l;
            delete l;
            return n;
        } else {
            merge(*n, *l, *r);
        }
        delete l;
        delete r;
        return n;
    }
}

int main() {
    // input
    int n = readInt();
    int m = readInt();
    long * arr = new long[n];
    for (int i = 0; i < n; ++i) {
        arr[i] = readLong();
    }
    // calculate max size of array
    int p = log(n + 1) / log(2) + 1;
    int k = pow(2, p + 1);
    node * tree = new node[k];
    buildTree(tree, arr, 1, n, 1);
    // input and calculate for m cases
    for (int i = 0; i < m; ++i) {
        int l = readInt();
        int r = readInt();
        node * n = search(l, r, tree, 1);
        write(n->tmax);
        putchar('\n');
        delete n;
    }
    delete [] arr;
    delete [] tree;
    return 0;
}

inline int readInt(){
    int x = 0, f = 1;
    char ch = getchar();
    while(ch < '0' || ch > '9'){
        if(ch == '-'){
            f = -1;
        }
        ch = getchar();
    }
    while(ch >= '0' && ch <= '9'){
        x = x * 10 + (ch - '0');
        ch = getchar();
    }
    return x * f;
}

inline long readLong(){
    long x = 0;
    int f = 1;
    char ch = getchar();
    while(ch < '0' || ch > '9'){
        if(ch == '-'){
            f = -1;
        }
        ch = getchar();
    }
    while(ch >= '0' && ch <= '9'){
        x = x * 10 + (ch - '0');
        ch = getchar();
    }
    return x * f;
}

inline void write(long x){
    if(x < 0){
        putchar('-');
        x = -x;
    }
    if(x > 9){
        write(x / 10);
    }
    putchar(x % 10 + '0');
}

inline void write(int x) {
    write((long)x);
}

