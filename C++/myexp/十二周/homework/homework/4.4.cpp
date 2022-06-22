#include <iostream>
#include <string>
using namespace std;

class Vehicle {
protected:
	string na;//名字
	string co;//颜色
};

class Car :public Vehicle {
protected:
	string na;
	string co;
	int pa;
public:
	Car() {
		na = "";
		co = "";
		pa = 0;
	}
	Car(string name, string color, int pas) {
		na = name;
		co = color;
		pa = pas;
	}
	//只能将重载“ << ”和“ >> ”的函数作为友元函数或普通函数，而不能将它们定义为成员函数
	friend ostream& operator <<(ostream& strm, Car& car);
	friend istream& operator >>(istream& strm, Car& car);
};
ostream& operator <<(ostream& strm, Car& car) {
	strm << "Car name:" << car.na << " Car color:" << car.co << " Car passager:" <<
		car.pa << endl;
	return strm;
}
istream& operator >>(istream& strm, Car& car) {
	cout << "Car name:" << endl;
	strm >> car.na;
	cout << "Car color:" << endl;
	strm >> car.co;
	cout << "Car passager:" << endl;
	strm >> car.pa;
	return strm;
}

class Truck :public Vehicle {
protected:
	string na;
	string co;
	double ca;
public:
	Truck(string name, string color, double cap) {
		na = name;
		co = color;
		ca = cap;
	}
	Truck() {
		na = "";
		co = "";
		ca = 0;
	}
	friend ostream& operator <<(ostream&, Truck&);
	friend istream& operator >>(istream& strm, Truck& truck);
};

//只能将重载“<<”和“>>”的函数作为友元函数或普通函数，而不能将它们定义为成员函数
ostream& operator <<(ostream& strm, Truck& truck) {
	strm << "Truck name:" << truck.na << " Truck color:" << truck.co << " Truck capacity:" << truck.ca << endl;
	return strm;
}
istream& operator >>(istream& strm, Truck& truck) {
	cout << "Truck name:" << endl;
	strm >> truck.na;
	cout << "Truck color:" << endl;
	strm >> truck.co;
	cout << "Truck capacity:" << endl;
	strm >> truck.ca;
	return strm;
}

int main() {
	cout << "请输入Car的数据：" << endl;
	Car car;
	cin >> car;
	cout << car << endl;
	cout << "请输入Truck的数据：" << endl;
	Truck truck;
	cin >> truck;
	cout << truck << endl;
	return 0;
}