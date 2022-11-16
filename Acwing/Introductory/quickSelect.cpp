#include <iostream>

using namespace std;

const int N = 1e5 + 10;
int n, k;
int q[N];

void quick_select(int q[], int l, int r){
    if(l == r) {
        cout << q[l];
        return;
    }
    int i = l - 1, j = r + 1;
    int x = q[rand() % (r - l + 1) + l];
    
    while(i < j){
        do(i++); while(q[i] < x);
        do(j--); while(q[j] > x);
        if(i < j) swap(q[i], q[j]);
    }
    if(j >= k) quick_select(q, l ,j);
    else{
        quick_select(q, j + 1 ,r);
        return;
    }
    
}
int main(){
    scanf("%d%d",&n,&k);
    k = k -1;
    for(int i = 0;i<n;i++){
        scanf("%d", &q[i]);
    }
    
    quick_select(q, 0, n-1);
    return 0;
}