#include <iostream>
#include <stdlib.h>
#include <string>
using namespace std;

class vehicle //汽车类
{
protected:
	int wheels; // 车轮数
	double weight; // 重量
	bool isrun;//是否启动
public:
	vehicle(int a, double b); //构造函数
	int GetWheels() { return wheels; }
	double GetWeight() { return weight; }
	void GetStart() { isrun = 1; cout << "车辆启动" << endl; }//启动
	void GetStop() { isrun = 0; cout << "车辆停止" << endl; }//停止
	void show();
};
vehicle::vehicle(int a, double b) //构造函数
{
	isrun = 0;//初始未启动
	wheels = a;
	weight = b;
}
void vehicle::show()
{
	cout << "车轮数：" << wheels << endl;
	cout << "重量：" << weight << endl;
}

class car :public vehicle //小汽车类
{
	int passenger;//载人数
public:
	car(int wheels1, double weight1, int passenger1);//构造函数
	void show();//显示车属性信息
};
//构造函数
car::car(int wheels1, double weight1, int passenger1) :vehicle(wheels1, weight1)
{
	passenger = passenger1;
}
//显示车属性信息
void car::show()
{
	cout << "小车类：" << endl;
	vehicle::show();
	cout << "载人数：" << passenger << endl;
}
//显示车属性信息
class truck :public vehicle //卡车类
{
	double payload;//载重量
public:
	truck(int wheels1, double weight1,double payload1);//构造函数
	void show();
};
//构造函数
truck::truck(int wheels1, double weight1, double payload1) :vehicle(wheels1, weight1)
{
	payload = payload1;
}
//显示车属性信息
void truck::show()
{
	cout << "卡车类：" << endl;
	vehicle::show();
	cout << "载重量：" << payload << endl;
}

int main()
{
	car a(4, 100, 5);
	truck b(6, 500, 300);
	a.GetStart();
	b.GetStart();
	a.show();
	cout << endl;
	b.show();
	a.GetStop();
	b.GetStop();
	system("PAUSE");
	return 0;
}

