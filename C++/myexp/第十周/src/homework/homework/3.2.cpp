#include <iostream>
using namespace std;

class Rectangle {
private:
	double w, h;
	double area;
public:
	Rectangle(double width = 0, double height = 0)  {
		w = width, h = height, area = height * width;
	}
	operator double(){
		return area;
	}
	//面积比较
	double operator>(Rectangle& a){
		if (w * h <= a.w * a.h) return 0;
		else  return a.w * a.h;
	}
	double operator>(double a){
		if (w * h <= a) return 0;
		else return a;
	}
};
int main()
{
	Rectangle a(4, 5), b(4, 4),c(1,2);
	if (a > b) cout << "a>b" << endl;
	else cout << "a<=b" << endl;
	
	bool one = (10.1 > a);
	bool two = (a > 10.1);
	cout << one << endl<< two << endl<<endl;

	bool three = (a > b > c);
	bool four = (a > c > b);
	bool five = (b > c > a);
	bool six = (b > a > c);
	bool seven = (c > a > b);
	bool eight = (c > b > a);
	cout << three<<four<<five<<six<<seven<<eight << endl;
	return 0;
}