#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
using namespace std;

class Mystring {
private:
	char* text;
public:
	//构造函数
	Mystring(char* ch) {
		cout << "调用构造函数" << endl;
		text = new char(strlen(ch) + 1);
		if (text) { strcpy(text, ch); }
	};
	//拷贝构造函数
	Mystring(const Mystring& s) {
		cout << "调用拷贝函数" << endl;
		text = new char(strlen(s.text) + 1);
		if (text) { strcpy(text, s.text); }
	};
	void print() {
		cout << "打印Stirng：" << text << endl;
	}
	//析构函数
	~Mystring(){
		cout << "调用析构函数" << endl;
	}
};

int main() {
	char* a = new char[6]{'d','a','t','e','\0','\0'};
	Mystring one(a);
	Mystring two(one);
	return 0;
}