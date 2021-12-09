#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <stdbool.h>

int f(const void *a, const void *b) {
    int n = *((int *)a);
    int m = *((int *)b);

    if (n < m) {
        return 1;
    } else if (n > m) {
        return -1;
    } else {
        return 0;
    }
}

int l[10000] = {0};
int uf[10000] = {0};

int find(int i) {
    int v = uf[i];
    while (v != uf[v]) {
        v = uf[v];
    }
    return v;
}

void merge(int l, int r) {
    int a = find(l);
    int b = find(r);

    if (a != b) {
        uf[b] = a;
    }
}

int main() {
    for (int i = 0; i < 10000; i++) {
        uf[i] = i;
    }

    for (int i = 0; i < 10000; i++) {
        int d;
        scanf("%1d", &d);
        l[i] = d;
    }

    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
            bool low = true;
            int ind = 100 * i + j;
            int val = l[ind];

            if (val == 9) continue;

            if (i > 0 && l[ind - 100] < 9) {
                merge(ind, ind - 100);
            }

            if (i < 99 && l[ind + 100] < 9) {
                merge(ind, ind + 100);
            }

            if (j > 0 && l[ind - 1] < 9) {
                merge(ind, ind - 1);
            }

            if (j < 99 && l[ind + 1] < 9) {
                merge(ind, ind + 1);
            }
        }
    }

    int sz[10000] = {0};
    for (int i = 0; i < 10000; i++) {
        sz[find(i)]++;
    }

    qsort(sz, 10000, sizeof(int), f);
    printf("%d %d %d\n", sz[0], sz[1], sz[2]);
    printf("%d\n", sz[0] * sz[1] * sz[2]);
    return 0;

}
