/*宏定义属于预处理命令，在编译过程中的预处理阶段处理。宏定义只是单纯的替换
所以对a，b进行比较，输出的Max就是1，对a取绝对值，输出的ABS即是1，将a，b进行交换，输出a=0，b=1。*/

#include <iostream>
using namespace std;
#define Max(a,b) ((a)>(b)? (a):(b))
#define ABS(a) ((a)>= 0)? (a):(0-(a))
#define Swap(t,x,y) t=x;x=y;y=t;

int main() {
	int a = 1, b = 0, t = 0;
	cout << "Max=" << Max(a, b) << endl;
	cout << "ABS=" << ABS(a);
	Swap(t, a, b)
		cout << endl << "a=" << a << endl << "b=" << b << endl;

	/*	Max(a++,b)的值为2，同时a被增值。Max(a++,b+10)的值为10，同时a的值被增值一次。
	作为改进，可以通过一个内联函数得到所有宏的替换效能，和所有可预见的状态，以及常规函数的类型检查。*/

	Swap(t, a, b)
		cout << endl << "a=" << a << endl << "b=" << b << endl;
	cout << "max=" << Max(a++, b) << "  a=" << a << endl;
	//a被增值两次
	cout << "max=" << Max(a++, b + 10) << "  a=" << a << endl;
	//a被增值一次

}