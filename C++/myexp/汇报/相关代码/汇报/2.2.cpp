#include <iostream>
using namespace std;

int add(int x, int y, int z)
{
	int sum;
	sum = x + y + z;
	return sum;
}
double add(int x, int y)
{
	double sum;
	sum = x + y;
	return sum;
}
int main() {
	int add(int x = 1, int y = 2, int z = 3);
	double add(int x, int y);
	int sum; double sum2;

	/*这里int add（int x = 1，int y = 2，int z = 3）的形参都是int型，
	  double add（int x，int y）的形参也都是int型，当调用函数传入10和20时，编译器无法识别是要调用哪一个函数，故会报错。
	  所以，一个函数不能既作为重载函数，又作为有默认参数的函数。
	  当调用函数时，如果少写一个参数，编译器可能无法判断是调用重载函数还是调用带默认参数的函数。*/
	sum = add(10, 20);
	sum = add(10, 20,20);
	cout << sum;
	return 1;
}