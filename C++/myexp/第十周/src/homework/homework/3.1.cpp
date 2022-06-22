#include <iostream>
using namespace std;

class Complex
{
private:
	double real;
	double im;
public:
	Complex& operator++();//前置++a
	Complex operator++(int);//后置a++
	Complex(double r, double i) {
		real = r;
		im = i;
	}
	//拷贝构造函数
	Complex(const Complex& t) {
		real = t.real;
		im = t.im;
		cout << "HAHA    " << endl;
	}
	Complex() {
		real = 0;
		im = 0;
	};
	friend ostream& operator <<(ostream&, Complex&);
	friend istream& operator >>(istream&, Complex&);
	//要求写函数，实现复数的加，对“+”进行重载
	//最后的const表明调用函数对象不会被修改
	//括号中的const表示参数对象不会被修改
	Complex operator +(const Complex& x)const {
		Complex temp;
		temp.real = real + x.real;
		temp.im = im + x.im;
		return temp;
	}
	double getReal()const {
		return real;
	}
	double getIm()const {
		return im;
	}
	Complex& operator+=(const Complex& c) {
		real += c.real;
		im += c.im;
		return *this;
	}
};

Complex& Complex::operator++() {
	real++;//先增量
	return *this;//再返回对象
}
Complex Complex::operator++(int) {
	Complex temp(*this);//复制构造函数，对象存放原有对象值
	real++;
	return temp;
}

//要求写函数，实现对Complex对象的>>和<<操作
ostream& operator <<(ostream& output, Complex& c) {
	output << "(" << c.real << "+" << c.im << "i)" << endl;
	return output;
};
istream& operator >>(istream& input, Complex& c) {
	cout << "请输入复数的实数部分和虚数部分" << endl;
	input >> c.real >> c.im;
	return input;
};

int main() {
	Complex c1(2,3), c2(3,4), c3(5,6),c4;
	c4 = c1 + c2 + c3;
	cout << c4 << endl;

	Complex c5, c6;
	cin >> c5 >> c6;
	cout << c5 << c6 << endl;

	c2 = c1++;
	c2 = ++(++c1);
	cout << c2 <<c3<< endl;

	c1 += c2 += c3 += c4;
	cout << c4 << endl;
	return 0;
}
