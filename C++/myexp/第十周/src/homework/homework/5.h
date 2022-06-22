#pragma once
#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include <string.h>
#include <stdlib.h>
using namespace std;

class MyString
{
private:
	char* str;
	int len;
	void memError();
public:
	MyString() { str = NULL; len = 0; }
	MyString(const char*);
	MyString(MyString&);
	~MyString() {if (len != 0)delete[]str;}
	int length() { return len; }
	char* getValue() { return str; }
	MyString operator +=(MyString&);
	char* operator+=(const char*);
	MyString operator=(MyString&);
	char* operator=(const char*);
	bool operator ==(MyString&);
	bool operator ==(const char*);
	MyString operator +(MyString&);
	char* operator+(const char*);
	// 重载流操作符和提取符，流操作符必须重载为友元
	friend ostream& operator <<(ostream&, MyString&);
	char & operator[](int idx);
};