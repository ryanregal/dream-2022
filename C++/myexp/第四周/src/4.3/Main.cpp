#include <iostream>
#include <string>
using namespace std;

void Encode(string& input) {
	for (auto &ch:input){
		ch += 8;
	}
}

void Decode(string& output) {
	for (auto &ch : output) {
		ch -= 8;
	}
}

int main() {
	int order;
	cout << "ÇëÊäÈë²Ù×÷£º±àÂë£¨1£© ½âÂë£¨2£© ÍË³ö£¨0£©" << endl;
	cin >> order;
	while(order!=0){
		if (order == 1) {
			string input;
			cout << "ÇëÊäÈëÒª±àÂëµÄ×Ö·û´®" << endl;
			cin>>input;
			Encode(input);//±àÂë
			cout << "±àÂëÎª" <<input<< endl<<endl;
		}
		else if (order == 2) {
			string output;
			cout << "ÇëÊäÈëÒª½âÂëµÄ×Ö·û´®" << endl;
			cin>>output;
			Decode(output);//±àÂë
			cout << "½âÂëÎª" << output << endl << endl;
		}
		cout << "ÇëÊäÈë²Ù×÷£º±àÂë£¨1£© ½âÂë£¨2£© ÍË³ö£¨0£©" << endl;
		cin >>order;
	}
}



