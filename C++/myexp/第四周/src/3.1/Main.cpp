#include <iostream>
#include <string.h>
using namespace std;
int main() {
	string s = "He123llo"; int count = 0;
	for (string::size_type index = 0; s[index] != s.size(); ++index) {   //£¨*£©
		char ch = s[index];
		if (ch >= '0' && ch <= '9') count++;
	}
	cout << count << endl;

	string s1 = "He123l4l5o";
	string new1;
	for (string::size_type index = 0; index != s1.size(); ++index) {   //£¨*£©
		char ch = s1[index];
		if (ch >= '0' && ch <= '9')
			new1 += ch;
	}
	cout << new1 << endl;
}

