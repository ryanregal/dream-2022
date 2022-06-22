#include <string>
#include <iostream>
using namespace std;
int main() {
	string set1 = "XYY";    string set2 = "XYZ";
	char set3[10] = "XYX";  char set4[10] = "XZZ";
	//cout << (set1 == set2) << endl;
	//cout << (set1.size() == set2.size()) << endl;
	cout << (set3 == set1) << endl;
	//cout << strcmp(set3, set4)<<endl;
	//cout << (set1 > set2) << endl;  //*
	//cout << (set1 == set3) << endl;  //**
	return 0;
}

