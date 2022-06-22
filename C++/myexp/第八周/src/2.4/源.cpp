#include <iostream>
using namespace std;

class Sample {
	int x;
public:
	Sample() { cout << "调用无参构造函数" << endl; };
	Sample(int a) { 
		cout << "调用带参构造函数" << endl;
		x = a; 
	}
	Sample(Sample& a) { 
		cout << "调用拷贝构造函数" << endl;
		x = a.x + 1; 
	}
	void disp() { cout << "x =  " << x << endl; }
};

void main() {
	Sample s1(2), s2(s1);
	s2.disp();
}