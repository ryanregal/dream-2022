#define _CRT_SECURE_NO_WARNINGS 
#include <stdio.h>
#define N 20 //假设N最大不超过20

int main() {
	int col, row,k;
	int n;
	printf("请输入二维数组的行数/列数：\n");
	scanf("%d", &n);
	int a[N][N];

	//输入二维数组
	printf("\n请输入二维数组：\n");
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf_s("%d", &k);
			a[i][j] = k;
		}
	}

	//输出整个二维数组
	printf("\n输出整个二维数组：\n");
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			printf("%4d", a[i][j]);
		}
		printf("\n");
	}

	//每一行最大值
	for (int i = 0; i < n; i++) {
		int rowmax = a[i][0];
		for (int j = 0; j < n; j++) {
			if (a[i][j] > rowmax)
				rowmax = a[i][j];
		}
		printf("\n第%d行最大值为:%d\n", i, rowmax);
	}


	//每一列最大值
	for (int i = 0; i < n; i++) {
		int colmax = a[0][i];
		for (int j = 0; j < n; j++) {
			if (a[j][i] > colmax)
				colmax = a[j][i];
		}
		printf("\n第%d列最大值为:%d\n", i, colmax);
	}

	//实现下三角输出
	printf("\n实现下三角输出：\n");
	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= i; j++) {
			printf("%4d", a[i][j]);
		}
		printf("\n");
	}

	//实现上三角输出
	printf("\n实现上三角输出：\n");
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) {
			printf("    ");
		}
		for (int j = i; j < n; j++) {
			printf("%4d", a[i][j]);
		}
		printf("\n");
	}
	return 0;
}
