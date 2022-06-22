#include<iostream>
#include<string>
#include<algorithm>
using namespace std;

int main() {
	string a[5];
	cout << "ÇëÊäÈëÎå¸ö´ýÅÅÐò×Ö·û´®£º" << endl;
	for (int i = 0; i < 5; i++) {
		cin >> a[i];
	}
	sort(a, a + 5);
	cout << "ÅÅÐòÍê³É£º" << endl;
	for (int i = 0; i < 5; i++) {
		cout << a[i]<<endl;
	}
	return 0;
}