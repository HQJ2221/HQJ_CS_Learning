#include <iostream>
#include <algorithm>

using namespace std;
inline int readInt();
inline long readLong();
inline void write(long x);

long merge(long *arr,int start, int mid, int end, long L, long R) {
    long count = 0;
    int i = start, jL = mid + 1, jR = mid + 1;
    for (; i <= mid; i++) {
        while (jR <= end && arr[jR] - arr[i] <= R) {
            jR++;
        }
        while (jL <= end && arr[jL] - arr[i] < L) {
            jL++;
        }
        count += jR - jL;
    }
    sort(arr + start, arr + end + 1);
    return count;
}

long findPair(long *arr,int start, int end, long L, long R) {
    if (start == end && arr[start] >= L && arr[start] <= R) {
        return 1;
    } else if (start == end) {
        return 0;
    }
    int mid = (start + end) / 2;
    long l_count = findPair(arr, start, mid, L, R);
    long r_count = findPair(arr, mid + 1, end, L, R);
    return l_count + r_count + merge(arr, start, mid, end, L, R);
}


int main() {
    int n = readInt();
    long L = readLong();
    long R = readLong();
    long *arr = new long[n];
    arr[0] = readLong();
    for(int i = 1; i < n; i++){
        arr[i] = readLong() + arr[i - 1];
    }
    write(findPair(arr, 0, n - 1, L, R));
    putchar('\n');
    delete [] arr;
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
