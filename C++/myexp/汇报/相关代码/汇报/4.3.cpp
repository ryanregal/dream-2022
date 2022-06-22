#include <iostream>
using namespace std;

int main() {
	int add(int x = 5, int y = 6);
	//有函数声明时，在声明中定义默认形参值
	cout << add(10, 20) << endl;//用实参来初始化形参
	cout << add(10) << endl;//形参x采用实参10，y采用默认值6
	cout << add() << endl;//x和y都采用默认值，分别为5和6
	return 0;
}

int add(int x, int y) {
	return x + y;
}