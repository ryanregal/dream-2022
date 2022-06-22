#include <iostream>
using namespace std;

//C++语言允许定义或声明函数时，为函数参数设定默认值。
//不过规范起见，一般在声明函数时为函数参数设定默认值。
//int add(int x = 1, int y = 2, int z = 3)
int add(int x, int y, int z)
{
	int sum;
	sum = x + y + z;
	return sum;
}

double add(double x, int y)
{
	double sum;
	sum = x + y;
	return sum;
}

int main() {
	int add(int = 1, int = 2, int = 3);
	double add(double x, int y);
	int sum; double sum2;

	sum = add(10, 20);
	//编译器根据实参的类型判断是调用int add(int x,int y,int z)
	//x和y被10和20初始化，而由于z没有给给出实参，则采用预先的默认形参值
	//将得到的结果10+20+3=33返回给sum，即最后输出sun为33。

	sum2 = add(1.0, 2);
	//编译器根据实参的类型判断是调用double add（double x，int y）
	//实参1.0和2被传入函数中，将计算得到的结果3返回给sum2，最后输出sum2为3。

	cout << sum << endl;
	cout << sum2 << endl;
	return 1;
}