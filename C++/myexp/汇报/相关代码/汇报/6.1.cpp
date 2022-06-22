#include<iostream>
using namespace std;

int Myadd(int x, int y) {
	return x + y;
}
float Myadd(float x, float y) {
	return x + y;
}
double Myadd(double x, double y) {
	return x + y;
}

//使用模板
//其中T是函数所使用的数据类型的占位符名称
template<typename T> T Myadd2(T a, T b) {
	return a + b;
}

int main() {
	cout << Myadd(1, 1) << endl;//整数相加
	cout << Myadd(1.2f, 1.2f) << endl;//float相加
	cout << Myadd(1.5, 1.2) << endl;//double相加

	cout << Myadd2(1, 1) << endl;//使用模板
	cout << Myadd2(1.2f, 1.2f) << endl;//使用模板
	cout << Myadd2(1.5, 1.2) << endl;//使用模板
}

