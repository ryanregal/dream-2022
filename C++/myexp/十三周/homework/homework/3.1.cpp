#include <iostream>
using namespace std;
template<typename T1>   //模板声明，T1为类型参数
T1 max(T1* p1, T1 n)    //定义模板函数max，求最大值
{
	int j = 0;
	for (int i = 1; i < n; i++)
		if (p1[i] > p1[j])  j = i;
	return p1[j];
}
template<typename T2>
T2 min(T2* p2, T2 m)   //定义模板函数min,求最小值
{
	int j = 1;
	for (int i = 0; i < m; i++)
		if (p2[i] < p2[j]) j = i;
	return p2[j];
}
int main()
{
	int a[] = { 5,6,4,58,2 };
	double b[] = { 2.3,1.1,2.2,3.3,88.01 };
	cout << "数组a为：" << endl;
	for (int i = 0; i < 5; i++)  cout << a[i] << "  ";  cout << endl;
	cout << "数组a中最大值为：" << max(a, 5) << endl;
	cout << "数组a中最小值为:" << min(a, 5) << endl;
	cout << "数组b为:" << endl;
	for (int j = 0; j < 5; j++)  cout << b[j] << "  ";  cout << endl;
	cout << "数组b中最大值为：" << max(b, 5.0) << endl;
	cout << "数组b中最小值为:" << min(b, 5.0) << endl;
	return 0;
}