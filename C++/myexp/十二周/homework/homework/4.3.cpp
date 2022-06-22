#include<iostream>
using namespace std;
//定义抽象类shape，在其中说明一个纯虚函数area()作为接口。在派生类中定义具体的函数实现。

class Shape // 抽象基类
{
public:
	virtual void printArea() = 0; // 纯虚函数
};
class Circle :public Shape // 定义 Circle 类
{
public:
	Circle(float r) :radius(r) {}; // 定义构造函数
	virtual void printArea() // 对虚函数再定义
	{
		cout << "Area of Circle：" << endl << 3.14159 * radius * radius << endl;
	}
private:
	float radius;
};

class Rectangle :public Shape // 定义 Rectangle 类
{
public:
	Rectangle(float w, float h) :width(w), height(h) {}; // 定义构造函数
	virtual void printArea() // 对虚函数再定义
	{
		cout << "Area of Rectangle：" << endl << width * height << endl;
	}
private:
	float width;
	float height;
};

class Square:public Shape // 定义 Square 类
{
public:
	Square(float w) :length(w) {}; // 定义构造函数
	virtual void printArea() // 对虚函数再定义
	{
		cout << "Area of Square：" << endl << length * length << endl;
	}
private:
	float length;
};

class Triangle :public Shape // 定义 Triangle 类
{
public:
	Triangle(float w, float h) :width(w), height(h) {}; // 定义构造函数
	virtual void printArea() // 对虚函数再定义
	{
		cout << "Area of Triangle：" << endl << 0.5 * width * height << endl;
	}
private:
	float width;
	float height;
};

int main()
{
	Circle circle(4.5);        // 建立 Circle 类对象 circle
	circle.printArea();        // 输出 Circle 的面积
	Rectangle rectangle(2, 6); // 建立 Rectangle 类对象 rectangle
	rectangle.printArea();     // 输出 rectangle 的面积
	Triangle triangle(3, 5);   // 建立 Triangle 类对象 
	triangle.printArea();      // 输出 triangle 的面积
	Square square(3);   // 建立 Triangle 类对象 
	square.printArea();      // 输出 triangle 的面积
	return 0;
}
