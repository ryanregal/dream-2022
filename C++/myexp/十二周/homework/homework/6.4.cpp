#include <iostream>
#include<string>
using namespace std;
//多重派生
class CBase1
{
public:
	CBase1() :a(1){
		cout << "base1 structure..." << endl;
	}
	~CBase1(){
		cout << "base1 destructure ..." << endl;
	}
	void print(){
		cout << "a=" << a << endl;
	}
protected:
	int a;
};
class CDerive1 :public CBase1
{
public:
	CDerive1():b(2) {
		cout << "base2 structure..." << endl;
	}
	~CDerive1(){
		cout << "base2 destructure..." << endl;
	}
	void print(){
		CBase1::print();
		b2.print();
		cout << "b=" << b << endl;
	}
private:
	CBase1 b2;
	int b;
};
class CDerive2 :public CBase1
{
public:
	CDerive2():c(3) {
		cout << "derive structure..." << endl;
	}
	~CDerive2(){
		cout << "derive destructure..." << endl;
	}
	void print(){
		CBase1::print();
		b1.print();
		cout << "c=" << c << endl;
	}
private:
	CBase1 b1;
	int c;
};

int main(){
	CDerive1 d1;
	d1.print();//调用函数时应加上括号
	CDerive2 d;
	d.print();//调用函数时应加上括号
}
