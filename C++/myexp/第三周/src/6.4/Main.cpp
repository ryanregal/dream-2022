#include <iostream>
#include <string>
#include<vector>
using namespace std;

int main() {

	//使用标准库类型string
	string str1("Hello!"), str2("My friend!");
	str1 += str2;
	cout <<str1 << endl;

	//使用标准库类型vector
	vector <char> string1{'H','e','l','l','o','!',0};//动态数组
	vector <char> string2{ 'M','y',' ','f','r','i','e','n','d','!',0 };
	string1.pop_back();
	for (char i:string2) {
		string1.push_back(i);//字符串连接
	}
	for (char j:string1){
		cout << j ;//输出最终字符
	}	

	//自己编写
	char* mystr1 = new char[15]{ 'H','e','l','l','o','!',0 };//在堆上分配15个连续内存
	char* mystr2 = new char[15]{ 'M','y',' ','f','r','i','e','n','d','!',0 };//在堆上分配15个连续内存
	//动态分配内存不够
	char* newstr1 = new char[30];//在堆上分配30个连续内存
	for (int i = 0; mystr1[i - 1] != 0; i++) {
		newstr1[i] = mystr1[i];
	}
	delete[]mystr1;
	for (int i = 0; mystr2[i - 1] != 0; i++) {
		newstr1[i+6] = mystr2[i];
	}
	delete[]mystr2;
	cout << endl<<newstr1;
}