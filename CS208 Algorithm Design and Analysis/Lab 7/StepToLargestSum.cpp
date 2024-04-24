#include <iostream>
#include <algorithm>

using namespace std;
inline int readInt();
inline long readLong();
inline void write(long x);

/**
 * Input: n, A, B
 * Output: step to make sum of Ai * Bi is maximum
 */

struct Arr {
    long index;
    long value;
};

long merge(long *arr, const long *left, int leftCount, const long *right, int rightCount) {
    long i = 0, j = 0, k = 0, m = leftCount;
    long inv = 0;
    while (i < leftCount && j < rightCount) {
        if (left[i] <= right[j]) {
            arr[k++] = left[i++];
            m--;
        } else {
            inv += m;
            arr[k++] = right[j++];
        }
    }
    while (i < leftCount) arr[k++] = left[i++];
    while (j < rightCount) arr[k++] = right[j++];
    return inv;
}

long mergeSort(long *arr, int n) {
    if (n < 2) {
        return 0;
    }
    int mid = n / 2;
    long *left = new long[mid];
    long *right = new long[n - mid];
    for (int i = 0; i < mid; i++) left[i] = arr[i];
    for (int i = mid; i < n; i++) right[i - mid] = arr[i];
    long inv = mergeSort(left, mid) + mergeSort(right, n - mid);
    inv += merge(arr, left, mid, right, n - mid);
    delete[] left;
    delete[] right;
    return inv;
}

int main() {
    // input
    int n = readInt();
    Arr *A = new Arr[n];
    Arr *B = new Arr[n];
    for (int i = 0; i < n; i++) {
        A[i].index = i;
        A[i].value = readLong();
    }
    for (int i = 0; i < n; i++) {
        B[i].index = i;
        B[i].value = readLong();
    }
    // preprocessing
    sort(A, A + n, [](Arr a, Arr b) {
        return a.value < b.value;
    });
    sort(B, B + n, [](Arr a, Arr b) {
        return a.value < b.value;
    });
    long *arr = new long[n];
    for (int i = 0; i < n; ++i) {
        arr[A[i].index] = B[i].index;
    }
    delete[] A;
    delete[] B;
    write(mergeSort(arr, n));
    putchar('\n');
    // check inversion
    delete[] arr;
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
