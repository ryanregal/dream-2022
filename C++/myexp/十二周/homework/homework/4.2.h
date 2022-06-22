#include <iostream>
#include<string>
using namespace std;
//在类体中声明成员函数，在类外定义成员函数

class Teacher
{
public:
	Teacher() {}
	Teacher(string nam, int a, char s, string tit, string ad, string t);
	void display();
protected:
	//在两个基类中都包含姓名、年龄、性别、地址、电话等数据成员；
	//在Teacher类中还包含数据成员，职称；
	string name;
	int age;
	char sex;
	string title;
	string addr;
	string tel;
};


class Cadre
{
public:
	Cadre() {}
	Cadre(string nam, int a, char s, string p, string ad, string t);
	void display();
protected:
	//在两个基类中都包含姓名、年龄、性别、地址、电话等数据成员；
	//在Cadre类中还包含数据成员，职务，
	string name;
	int age;
	char sex;
	string post;
	string addr;
	string tel;
};


class Teacher_Cadre :public Teacher, public Cadre
{
private:
	//在Teacher_Cadre类中还包含数据成员，工资
	int wages;
public:
	Teacher_Cadre() {}
	Teacher_Cadre(string nam, int ag, char se, string tit, string pos, string add, string te, int wag);
	void Show();
};