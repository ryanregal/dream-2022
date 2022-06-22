//采用字符数组存储字符串，从字符串中去除标点符号
#include <iostream>
#include <string>
using namespace std;

void remove(char(& s)[100]) {
	for (int i = 0, len = strlen(s); i < len; i++){
		if (ispunct(s[i])) {
			for (int j = i; j < len;j++) {
				s[j] = s[j + 1];
			}
			len = strlen(s);
		}
	}
}

int main() {
	char text[100];
	cin.getline(text, 100);//"Lorem ipsum, dolor sit! amet, consectetur? adipiscing elit. Ut porttitor."
	remove(text);
	cout << text << endl;
	return 0;
}

