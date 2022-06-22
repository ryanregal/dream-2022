#include <iostream>
#include <string>
using namespace std;

class A {
public:
	A(string s) { cout << s << endl; }
};
class B :virtual public A {
public:
	B(string s1, string s2) :A(s1) { cout << s2 << endl; }
};
class C :virtual public A {
public:
	C(string s1, string s2) :A(s1) { cout << s2 << endl; }
};
class D :public B, public C {
public:
	D(string s1, string s2, string s3, string s4) :B(s1, s2), C(s3, s4), A(s1) { cout << s4 << endl; }
};
void main() {
	string strA = "class A"; string strB = "class B"; string strC = "class C"; string strD = "class D";
	D d(strA, strB, strC, strD);
}