#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string.h>
using namespace std;

int main() {
	//string str1, str2, str3;
	//str1 = "ABC"; str2 = "DEF";
	//str3 = str1 + str2;
	//cout << str3 << endl;
	//str3 += "GHI";
	//cout << str3 << endl;
	char strone[10]{ "ABC" };
	char strtwo[10]{ "DEF" };
	strcat(strone, strtwo);
	cout << strone << endl;
	strcat(strone, "GHI");
	cout << strone << endl;
}