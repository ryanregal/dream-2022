#include<iostream>
using namespace std;
int* a = new int[10];

//选择排序
void SelectionSort(int n) {
	for (int i = 0; i <= n; i++) {
		for (int j = i + 1; j <= n - 1; j++) {
			if (a[i] > a[j]) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
	}
}

//快速排序
void QuickSort(int left, int right) {
	int i, j, t, temp;
	if (left > right) return;
	temp = a[left]; //temp中存的就是基准数
	i = left;
	j = right;
	while (i != j) {//顺序很重要，要先从右边开始找
		while (a[j] >= temp && j > i)  j--;
		while (a[i] <= temp && j > i)  i++;//再找左边的
		if (i < j) {//交换两个数在数组中的位置
			t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
	a[left] = a[i];//最终将基准数归位
	a[i] = temp;
	QuickSort(left, i - 1);//继续处理左边的，这里是一个递归的过程
	QuickSort(i + 1, right);//继续处理右边的 ，这里是一个递归的过程
}

//冒泡排序
void BubbleSort(int n) {
	for (int i = 1; i <= n - 1; i++) {
		for (int j = 1; j <= n - i; j++) {
			if (a[j - 1] > a[j]) {
				int temp = a[j - 1];
				a[j - 1] = a[j];
				a[j] = temp;
			}
		}
	}
}

int main() {
	int n;
	cout << "要排序的数的数量：";
	cin >> n;
	//输入数组
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	SelectionSort(n);//选择排序
	QuickSort(0, n - 1);//快速排序
	BubbleSort(n);//冒泡排序
	//输出数组
	cout << endl;
	for (int i = 0; i < n; i++) {
		cout << a[i] << " ";
	}
}