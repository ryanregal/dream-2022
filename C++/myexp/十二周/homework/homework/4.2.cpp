#include "4.2.h"
Teacher::Teacher(string nam, int a, char s, string tit, string ad, string t) :
	name(nam), age(a), sex(s), title(tit), addr(ad), tel(t) {
	cout << "调用Teacher构造函数" << endl;
}
void Teacher::display()
{
	cout << "姓名：" << name << endl;
	cout << "年龄：" << age << endl;
	cout << "性别：" << sex << endl;
	cout << "职称：" << title << endl;
	cout << "地址：" << addr << endl;
	cout << "电话：" << tel << endl;
}

Cadre::Cadre(string nam, int a, char s, string p, string ad, string t) :
	name(nam), age(a), sex(s), post(p), addr(ad), tel(t) {
	cout << "调用Cadre的构造函数" << endl;
}
void Cadre::display()
{
	cout << "姓名：" << name << endl;
	cout << "年龄：" << age << endl;
	cout << "性别：" << sex << endl;
	cout << "职务：" << post << endl;
	cout << "地址：" << addr << endl;
	cout << "电话：" << tel << endl;
}

Teacher_Cadre::Teacher_Cadre(string nam, int ag, char se, string tit, string pos, string add, string te, int wag) :
	Teacher(nam, ag, se, tit, add, te),
	Cadre(nam, ag, se, pos, add, te),
	wages(wag){
	cout << "调用Teacher_Cadre的构造函数" << endl;
}
void Teacher_Cadre::Show() {
	//在派生类Teacher_Cadre的成员函数show中调用Teacher类中的display函数
	//输出姓名、年龄、性别、职称、地址、电话，然后用cout语句输出职务和工资。
	//对两个基类中的姓名、年龄、性别、地址、电话等数据成员用相同的名字
	//在引用这些数据成员时，指定作用域；
	Teacher::display();
	cout << "职务：" << Cadre::post << endl;
	cout << "工资：" << wages << endl;
}

int main()
{
	Teacher_Cadre te_ca("Wangli", 50, 'f', "prof.", "president", "135 Beijing Road,Shanghai", "(021)61234567", 1534.5);
	te_ca.Show();
	return 0;
}