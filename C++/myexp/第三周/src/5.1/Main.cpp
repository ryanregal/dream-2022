#include <iostream>
using namespace std;

int main() {
	using namespace std;
	//动态分配大小为3的数组
	double* p3 = new double[3];
	//给该数组赋值
	p3[0] = 0.2;    p3[1] = 0.5;   p3[2] = 0.8;
	cout << "p3[1] is " << p3[1] << ".\n";
	p3 = p3 + 1;
	cout << "Now p3[0] is " << p3[0] << " and ";
	cout << " p3[1] is " << p3[1] << " .\n";
	p3 = p3 - 1;
	delete[] p3;
	return 0;
}