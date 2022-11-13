#include <iostream>

using namespace std;

int main(){
    double x;
    cin >> x;

    double l = 0;
    double r = x;

    while(r - l > 1e-6){
        double mid = (r + l) / 2.0;
        if(mid * mid > x) r = mid;
        else l = mid;
    }
    cout << l;
    return 0;
}