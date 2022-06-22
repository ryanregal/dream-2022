#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
using namespace std;

class Person {
private:
	char* pName;
public:
	Person(const char* pN = "noName") {
		cout << "构造中  " << pN << "\n";
		pName = new char[strlen(pN) + 1];
		if (pName) strcpy(pName, pN);
	}
	Person(const Person& p) {
		pName = new char[strlen(p.pName) + 1];
		if (p.pName) strcpy(pName, p.pName);
		cout << "调用复制构造函数" << endl;
	}
	void print() {
		cout << pName << endl;
	}
};

int main() {
	Person p1("John"); //使用构造函数来创建p1
	p1.print();
	Person p2(p1);
	p2.print();
}