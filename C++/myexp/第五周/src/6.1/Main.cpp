#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>         
using namespace std;

class Student {
public:
	void Register(const char* n, char s = 'M', int a = 19, float sc = 90);
	void showme();
private:
	char name[20];
	char sex;
	int age;
	float score;
};
void  Student::Register(const char* n, char s, int a, float sc) {
	strcpy(name, n);
	sex = s;
	age = a;
	score = sc;
}
inline  void   Student::showme() { //内联
	cout << name << '\t' << sex << '\t' << age << '\t' << score << endl;
}
int main() {
	Student Stu1, Stu2;
	//注册name为Tom的同学，sex、age、score采用default值M、19、90
	Stu1.Register("Tom");
	//注册name为Lucy,sex为F，age为18，score为98的同学
	Stu2.Register("Lucy", 'F', 18, 98);
	cout << "输出" << endl;
	Stu1.showme();//输出TOM的信息
	Stu2.showme();//输出LUCY的信息
	return 0;
}