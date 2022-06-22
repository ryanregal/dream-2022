#include<iostream>
using namespace std;
//单继承
class A1 {	// 定义基类A1 
	int a1;
public:
	A1(int i) { a1 = i; cout << "constructor A1." << a1 << endl; }
	void print() { cout << a1 << endl; }
};
class B : public virtual A1 { // 定义派生类 B，基类为A1和A2 
	int b;
public:
	B(int i,int l) : A1(i){//派生类构造函数 
		b = l;
		cout << "constructor B." << b << endl;
	}
	void print() {
		A1::print();
	}
};
void main() {
	B bb(1, 2);
	bb.print();
}
