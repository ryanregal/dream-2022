#include "iostream"  //这是一道较为典型的由静态绑定向动态绑定的迁移
using namespace std;

class Base {
public:
	//虚函数定义
	virtual void disp() { cout << "Base class" << endl; }
};
class Derive1 :public Base {
public:
	//虚函数重定义
	void disp() { cout << "Derive1 class" << endl; }
};
class Derive2 :public Base {
public:
	//虚函数重定义
	void disp() { cout << "Derive2 class" << endl; }
};
void main() {
	Base obj, * p;
	Derive1 obj1;
	Derive2 obj2;
	p = &obj;
	p->disp();
	p = &obj1;
	p->disp();
	p = &obj2;
	p->disp();
	obj1.disp();
	obj2.disp();
}