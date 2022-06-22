#include "5.h"

//内存分配失败，调用exit()终止
void MyString::memError() {
	cout << "内存分配出错" << endl;
	exit(0);
}
//构造函数
MyString::MyString(const char* sptr) {
	len = strlen(sptr);
	str = new char[len + 1];
	if (str==NULL){
		memError();
	}
	strcpy(str, sptr);
}
//拷贝构造函数
MyString::MyString(MyString& right) {
	str = new char[right.length() + 1];
	if (str == NULL) memError();
	strcpy(str, right.getValue());
	len = right.length();
}

//能使用 += 运算符进行两个字符串的连接运算
//返回值是自身调用的对象
MyString MyString::operator+=(MyString& right) {
	char* temp = str;
	str = new char[strlen(str) + right.length() + 1];
	if (str == NULL)memError();
	strcpy(str, temp);
	strcat(str, right.getValue());
	if (len != 0)delete[]temp;
	len = strlen(str);
	return *this;
}
char* MyString::operator +=(const char* right)
{
	char* temp = str;
	str = new char[strlen(str) + strlen(right) + 1];
	if (str == NULL)  memError();
	strcpy(str, temp);
	strcat(str, right);
	if (len != 0) delete[] temp;
	return str;
}

//能使用 + 运算符进行两个字符串的复制
//返回值是调用对象
MyString MyString::operator +(MyString& right) {
	MyString temp(*this);
	temp += right;
	return temp;
};
char* MyString::operator+(const char* a){
	MyString temp(* this);
	temp += (const char*)a;
	char* ans = temp.str;
	return ans;
};

//能使用 == 运算符对两个字符串判断是否相等
// 如果调用对象和参数对象的 str 内容相同，返回true，否则返回 false
bool MyString::operator ==(MyString& right)
{
	return strcmp(str, right.getValue()) == 0 ? true : false;
}
bool MyString::operator ==(const char* right)
{
	return strcmp(str, right) == 0 ? true : false;
}

//能使用 = 运算符进行两个字符串的赋值运算
//返回值是调用对象自身
char* MyString::operator=(const char* right) {
	if (len != 0)  delete[] str;
	len = strlen(right);
	str = new char[len + 1];
	if (str == NULL) memError();
	strcpy(str, right);
	return str;
} 
MyString MyString::operator =(MyString& right)
{
	if (len != 0)  delete[] str;
	str = new char[right.length() + 1];
	if (str == NULL)  memError();
	strcpy(str, right.getValue());
	len = right.length();
	return *this; // 返回调用对象本身
}

//能使用[]运算符返回字符信息，并能做越界判断
char& MyString::operator[](int idx) {
	if (idx < len) return str[idx];
	else {
		cout << "数组越界" << endl;
		char zero[] = "\0";
		*this += (const char*)zero;
		return str[len];
	}
};
// 重载流插入符<<，返回一个引用
ostream& operator <<(ostream& strm, MyString& obj)
{
	strm << obj.str;
	return strm; // 将当前流对象返回
}

int main()
{
	MyString obj1("I "), obj2("love "),obj3("China");
	MyString obj4 = obj1; // 调用拷贝构造函数
	cout << obj1[3] << endl;
	char str[] = "!";
	cout << "对象 1: " << obj1 << endl;
	cout << "对象 2: " << obj2 << endl;
	cout << "对象 3: " << obj3 << endl;
	cout << "对象 4: " << obj4 << endl;
	cout << "字符数组: " << str << endl;
	// 演示对象 += 操作
	obj1 += obj2;
	obj1 += obj3;
	obj1 += str;
	cout << "对象 1: " << obj1 << "\n\n";
	// 演示关系运算
	if (obj1 == str)  cout << obj1 << " 等于字符数组 " << str << endl;
	else  cout << obj1 << " 不等于字符数组 " << str << endl;
	if (obj3 == "China") cout << obj3 << " 等于 China\n";
	else cout << obj3 << " 不等于 China\n";
	return 0;
}