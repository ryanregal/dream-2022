#include "Date.h"

void Date::cindata() {
	cout << "请输入日期（如2022 3 27）:";
	cin >> year >> month >> day;
	if (month > 12 || month < 1) 	throw "无效月份";
	if (day > 31 || day < 1) 	throw "无效日期";
}

void Date::cout1() {
	cout << endl << month << "-" << day << "-" << year % 100 << endl;
}

void Date::cout2() {
	cout << endl << getmonth(month) << " " << day << "," << year << endl;
}

void Date::cout3() {
	cout << endl << day << " " << getmonth(month) << " " << year << endl;
}

string Date::getmonth(int month) {
	switch (month) {
	case 1: return "January";
	case 2: return "February";
	case 3:  return "March";
	case 4:  return "April";
	case 5: return "May";
	case 6: return "June";
	case 7: return "July";
	case 8: return "August";
	case 9: return "September";
	case 10: return "October";
	case 11: return "November";
	case 12: return "December";
	}
}

int main(){
	Date date;
	int choice;
	while (true) {
		while (true) {
			try {
				date.cindata();
				break;
			}
			catch (const char* msg) {
				cerr << msg << endl;//输出throw的错误信息
			}
		}
		while (true) {
			cout << "\n1.输出12-25-11格式" << endl;
			cout << "2.输出December 25,2011格式" << endl;
			cout << "3.输出25 December 2011格式" << endl;
			cout << "4.重新输入" << endl;
			cout << "选择：";
			cin >> choice;
			if (choice == 4) {
				cout << endl;
				break;
			}
			switch (choice) {
			case 1:date.cout1(); break;
			case 2:date.cout2(); break;
			case 3:date.cout3(); break;
			default:break;
			}
		}
	}
}