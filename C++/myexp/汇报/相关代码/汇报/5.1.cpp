//函数重载实现正方形、长方形、三角形（两边一夹角）、梯形（上下底和高）的面积；
//程序使用重载函数area()，相同函数名重载函数area()的四个函数具有不同个数的参数
//四次调用area函数的参数个数不同，系统会根据参数的个数找到与之匹配的函数并调用它。

#include <iostream>
#include <cmath>
using namespace std;

// 给定正方形边长，求面积
inline double area(double length) {
	return length * length;
}
// 给矩形宽高，求矩形面积
inline double area(double width, double height) {
	return width * height;
}
// 给三角形的两边一角，求三角形面积
inline double area(double Degrees, double length1, double length2) {
	Degrees = Degrees * 3.14159 / 180;
	double s = 0.5 * length1 * length2 * sin(Degrees);
	return s;
}

//由于梯形和三角形所需要的参数都是3，类型也都是double
//所以在求梯形面积的area函数参数中加了一个int类型的参数flag以便区分
// 给梯形的上下底和高，求梯形面积
inline double area(double a, double b, double c, int flag) {
	double s = (a + b) * c / 2;
	return s;
}

int main() {
	double square, width, heigth, triangle1, triangle2, degree, a, b, c;
	cout << "请输入正方形边长：" << endl;
	cin >> square;
	cout << "该正方形边长为" << area(square) << endl;
	cout << "请输入长方形的长和宽" << endl;
	cin >> width >> heigth;
	cout << "长方形面积为 " << area(width, heigth) << endl;
	cout << "请输入三角形的两条边" << endl;
	cin >> triangle1 >> triangle2;
	cout << "请输入三角形的两边夹角（角度）" << endl;
	cin >> degree;
	cout << "三角形面积为 " << area(degree, triangle1, triangle2) << endl;
	cout << "请输入梯形的上底、下底、高" << endl;
	cin >> a >> b >> c;
	cout << "梯形面积为 " << area(a, b, c, 1) << endl;
	return 0;
}