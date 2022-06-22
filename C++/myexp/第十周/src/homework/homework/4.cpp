#include <iostream>
using namespace std;

class Sample {
private:
	int x;
public:
	Sample() { x = 0; } //考虑为何要写这个
	Sample(int a) { x = a; }
	void disp() { cout << "x = " << x << endl; }
	Sample& operator =(const Sample& s);
};
Sample& Sample::operator =(const Sample& s) {
	x = s.x;
	return *this;
}
void main() {
	Sample obj1(1),obj2(2), obj3(3);
	obj1 = obj2=obj3;
	obj1.disp();
	obj2.disp();
	obj3.disp();
	Sample obj4(1), obj5(2), obj6(3);
	(obj4 = obj5) = obj6;
	obj4.disp();
	obj5.disp();
	obj6.disp();
	return;
}