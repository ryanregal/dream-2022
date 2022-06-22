#include<iostream> 
using namespace std;

class A{ //定义基类
private:
	int a ; 
public:
	A(int x) { 
		a = x ;
		cout<<"A's constructor called."<<endl ; 
	}
	void show( ) { 
		cout<<a<<endl;
	} 
};

class C : public A {//定义派生类 
private:
	int c;
public:
	// 派生类构造函数 
	//当基类声明有带形参的构造函数时，派生类也应声明带形参的构造函数，并将参数传递给基类构造函数。
	C(int x, int y, int z) :A(x){ 
		c = z;
		cout << "C's constructor called." << endl;
	}
	void show() {
		A::show();
		cout << c << endl;
	}
};

//定义另一个类，将作为类成员 
class B{ 
private:
	int b ; 
public: 
	int get( ) { return b;} 
};

class D :public B {
private:
	int d;
public:
	D(int d1) {
		d = d1;//当基类中声明有默认形式的构造函数或未声明构造函数时，派生类构造函数可以不向基类构造函数传递参数。
	}
};

class E :public B {
private:
	int e;
	//若基类中未声明构造函数，派生类中也可以不声明，全采用默认形式构造函数。
};

