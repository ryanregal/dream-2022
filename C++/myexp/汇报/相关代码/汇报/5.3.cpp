#include <iostream>
using namespace std;

//在for循环中反复执行内联函数，使嵌入的效率相对较高。
inline int is_digit(char a) {
	if (a >= '0' && a <= '9') return 1;
	else return 0;
}

int main()
{
	int num = 0;
	cout << "请输入字符串的字符个数:" << endl;
	scanf_s("%d", &num);
	cout << "请输入字符串(字符个数为" << num << ")" << endl;
	char clear = getchar();
	for (int i = 0; i < num; i++)
	{
		char a = getchar();
		if (is_digit(a))
		{//调用内联函数
			cout << a << "是数字" << endl;
		}
		else cout << a << "不是数字" << endl;
	}
	return 0;
}