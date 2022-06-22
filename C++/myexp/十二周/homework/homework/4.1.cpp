#include<iostream>
using namespace std;
class A {
public:
	A() {
		a = 5; cout << "A=" << a << endl;
	}
protected:
	int a;
};
class A1 :virtual public A {
public:
	A1() { a += 10; cout << "A1=" << a << endl; }
};
class A2 :virtual public A {
public:
	A2() { a += 20; cout << "A2=" << a << endl; }
};
class B :public A1, public A2 {
public:
	B() { cout << "B a =" << a << endl; }
};
int main() {
	B obj;
	return 0;
}