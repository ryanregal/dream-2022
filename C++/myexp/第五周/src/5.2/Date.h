#pragma once
#include <string>
#include <iostream>
using namespace std;

class Date {
private:
	int month;
	int day;
	int year;
public:
	void cindata();
	void cout1();
	void cout2();
	void cout3();
	std::string getmonth(int month);

	void setdata() {
		year = 2010;
		month = 12;
		day = 25;
	}
};


