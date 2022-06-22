#include <iostream>
using namespace std;

template <class T>
T mymin(T a, T b)
{
    if (a < b)
        return a;
    else
        return b;
}
void main() {
    int i1 = 10, i2 = 20;
    double d1 = 3.5, d2 = 1.2;
    char c1 = 'b', c2 = 'x';
    cout << mymin(i1, i2) << endl;
    cout << mymin(d1, d2) << endl;
    cout << mymin(c1, c2) << endl;
    //cout << mymin(i1, c2) << endl;//没有与参数列表匹配的函数模板实例
    //cout << mymin(i1, d2) << endl;
}