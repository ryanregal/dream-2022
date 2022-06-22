#define _CRT_SECURE_NO_WARNINGS
#include <string>
#include <iostream>
using namespace std;

int main() {
	string movieTitle;
	char movieone[30];
	strcpy( movieone,"Gone with Wind");  //(*)字符数组
	cin.getline(movieone, 14, '\n');
	cout << "Movie one is " << movieone << endl;
	//const char* movietwo;
	//movietwo= "Gone with Wind";  //(*)字符型指针变量
	//cout << "Movie two is " << movietwo << endl;
	//movieTitle = "Gone with Wind";  //(*)string对象
	//cin >> movieTitle;
	//getline(cin, movieTitle);
	//cout << "Movie is "<< movieTitle << endl;
	return 0;
}

