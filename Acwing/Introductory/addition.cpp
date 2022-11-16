#include <iostream>

using namespace std;

const int N = 1e5 + 10;
string m, n;
char result[N];
int carry[N];
void add(string m, string n){
    carry[0] = 0;
    int i = 0;
    while(i < m.size() && i < n.size()){
        cout << "A";
        result[i] = (char)(((int)m.at(i) + (int)n.at(i) + carry[i]) % 10);
        cout << "A";
        carry[i + 1] = ((int)m.at(i) + (int)n.at(i) + carry[i]) / 10;
        cout << "A";
        i++;
    }
    cout << "A";
    while(i < m.size()){
        result[i] = (char)(((int)m.at(i) + carry[i]) % 10);
        carry[i + 1] = (((int)m.at(i) + carry[i]) / 10);
        i++;
    }
    cout << "B";
    while(i < n.size()){
        result[i] = (char)(((int)n.at(i) + carry[i]) % 10);
        carry[i + 1] = (((int)n.at(i) + carry[i]) / 10);
        i++;
    }
    cout << "C";
    if (carry[i] == 1){
        cout << "1";
    }
    while(i-- > 0){
        cout << result[i];
    }
    
}

int main(){
    scanf("%s", &m);
    scanf("%s", &n);
    add(m, n);
    return 0;
}