#include<iostream> 
using namespace std;

template <class T>
class Array
{
	T* set;
	int n;
public:
	Array(T* data, int i) { set = data; n = i; }
	void sort(); // 排序 
	int seek(T key); // 查找指定的元素 
	T sum(); // 求和 
	void disp(); // 显示所有的元素 
};

template<class T>
void Array<T>::sort()
{
	int i, j;
	T temp;
	for (i = 1; i < n; i++)
		for (j = n - 1; j >= i; j--)
			if (set[j - 1] > set[j]){
				temp = set[j - 1]; set[j - 1] = set[j]; set[j] = temp;
			}
}

template <class T>
int Array<T>::seek(T key)
{
	int i;
	for (i = 0; i < n; i++)
		if (set[i] == key)  return i;
	return -1;
}

template<class T>
T Array<T>::sum()
{
	T s = 0; int i;
	for (i = 0; i < n; i++) s += set[i];
	return s;
}

template<class T>
void Array<T>::disp()
{
	int i;
	for (i = 0; i < n; i++) cout << set[i] << " ";
	cout << endl;
}

void main()
{
	int a[] = { 6,3,8,1,9,4,7,5,2 };
	double b[] = { 2.3,6.1,1.5,8.4,6.7,3.8 };
	Array<int>arr1(a, 9);
	Array<double>arr2(b, 6);
	cout << " arr1:" << endl;
	cout << " 原序列:"; arr1.disp();
	cout << " 8在arr1中的位置:" << arr1.seek(8) << endl;
	arr1.sort();
	cout << " 排序后:"; arr1.disp();
	cout << "arr2:" << endl;
	cout << " 原序列:"; arr2.disp();
	cout << " 8.4在arr2中的位置:" << arr2.seek(8.4) << endl;
	arr2.sort();
	cout << " 排序后:"; arr2.disp();
}