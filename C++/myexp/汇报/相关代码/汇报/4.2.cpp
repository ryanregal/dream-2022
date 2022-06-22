#include <iostream>
using namespace std;


//在函数定义和函数声明的函数头前面加关键字inline
inline int cube(int);
int main() {
	for (int i = 1; i <= 10; i++)
	{
		int p = cube(i);
		cout << i << '*' << i << '*' << i << '=' << p << endl;
	}
}

//程序中出现的内联函数的调用将用该函数的函数体代替，而不是转去调用该函数。
inline int cube(int n) {
	return n * n * n;
}