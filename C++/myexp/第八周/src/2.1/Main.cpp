#include <iostream> 
using namespace std;

class Base {
public: Base(int a) : val(a) { cout << "构造函数Base" << endl; }
	  int getval() { return val; }
private: int val;
};

class A {
public:
	A(int v) : p(v), b(v) { cout << "构造函数A" << endl; }
	void print_val() {
		cout << "hello:" << p << endl;
		cout << "base:" << b.getval() << endl;
	}
private:
	int p;
	Base b;
};

int main() {
	int pp = 45;
	A b(pp), c = b;

	b.print_val();
	c.print_val();
}