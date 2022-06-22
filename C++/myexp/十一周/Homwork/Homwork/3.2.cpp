#include<iostream>
using namespace std;

//A为基类
class A {
protected:
	int x, y;
public:
	void f() {
		cout << x << endl;//可以被类内部的成员函数访问
	};
};

class B :public A {
public:
	void h() {
		cout << x << y << endl; //能够被派生类的成员函数访问
		f();
	}
	A::x;
	A::y;
};

int main() {
	A a;
	//cout << a.x << endl;//ERROR:派生类对象（main函数）不能进行访问
	B b;
	cout << b.x << endl;//可以访问
	a.f();
	b.h();
}