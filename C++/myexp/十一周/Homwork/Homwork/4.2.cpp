#include <iostream>
using namespace std;

class Parent {
private:     
	int m_nPrt;
protected:     
	int m_nPtd;
public:     
	int m_nPub;
public:
	Parent(int var = -1) {
		m_nPub = var;
		m_nPtd = var;
		m_nPrt = var;
	}
};

class Child1 : public Parent {
public:
	int getPub() { return m_nPub; }
	int getPtd() { return m_nPtd; }
	//int getPrt() { return m_nPrt; }   //private成员派生类不可访问
};

class Child2 : protected Parent {
public:
	int getPub() { return m_nPub; }
	int getPtd() { return m_nPtd; }
	//int getPrt() { return m_nPrt; }   //private成员派生类不可访问
};

class Child3 : private Parent {
public:
	int getPub() { return m_nPub; }
	int getPtd() { return m_nPtd; }
	//int getPrt() { return m_nPrt; }   //private成员派生类不可访问
};

int main() {
	Child1 cd1;
	Child2 cd2;
	Child3 cd3;
	int nVar = 0;

	Parent p;
	p.m_nPub;
	cd1.m_nPub = nVar;  // public 继承，可以访问
	//cd1.m_nPtd = nVar;   //父类的protected成员，派生类对象不能访问
	nVar = cd1.getPtd();   //可以通过调用Child1的成员函数访问

	//cd2.m_nPub = nVar;  // protected 继承，派生类对象不能访问
	nVar = cd2.getPtd();   //可以通过调用Child2的成员函数访问

	//cd3.m_nPub = nVar;  // private 继承，派生类对象不能访问 
	nVar = cd3.getPtd();   //可以通过调用Child3的成员函数访问

	return 0;
}