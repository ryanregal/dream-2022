#include <iostream>
using namespace std;

//某个类中有const成员
class A {
public:
	A(int size) : SIZE(size) { }
private:
	
	const int SIZE;
}; 
//某个类中有引用类型成员
class B{
public:
	B(int& v) : i(v), p(v), j(v) { }
	void print_val() {
		cout << "hello:" << i << " " << j << endl;
	}
private:
	const int i;
	int p;
	int& j; 
};

int main() { 
	A a(100); 
	int r = 45; B b(r); b.print_val();
}


