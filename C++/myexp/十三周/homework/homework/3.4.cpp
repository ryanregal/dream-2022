#include<iostream>
using namespace std;

template<class Type>
class Node {
public:
	Type Data;
	Node* next;
};

template<class Type>
class List{
private:
	Node<Type>* head;
	int Length;
public:
	void Get() {cout << head->next->Data << endl;}
	int length() { return Length; }

	List() {
		head = new Node<Type>;
		head->Data = (Type) 0;
		head->next = NULL;
		Length = 1;
	}

	int Append(const Type& t) {
		Node<Type>* p, * q;
		p = head;
		while (p->next != NULL) {
			p = p->next;
		}
		q = new Node<Type>;
		q->Data = t;
		q->next = NULL;
		p->next = q;
		Length++;
		return Length;
	}

	int Insert(const Type& t, int i) {
		Node<Type>* p, * q;
		p = head;
		for (int loc = 0; loc < i ; loc++) {
			p = p->next;
		}
		q = new Node<Type>;
		q->Data = t;
		q->next = p->next;
		p->next = q;
		Length++;
		return Length;
	}

	int Search(Type& t) {
		Node<Type>* p;
		p = head;
		int i = 0;
		while (p != NULL) {
			i++;
			if (p->Data == t) return i-1;
			p = p->next;
		}
		return 0;
	}

	void Delete(int t) {
		Node<Type>* p;
		p = head;
		if (t != 1) {
			for (int j = 0; j < t-1; j++) p = p->next;
			p->next = p->next->next;
		}
		else {
			head = p->next;
		}
	}

	Type& operator [ ] (int i) {
		Node<Type>* p;
		p = head;
		for (int j = 0; j < i; j++) p = p->next;
		return p->Data;
	}

	void Display() {
		Node<Type>* p;
		p = head->next;
		while (p != NULL) {
			cout << p->Data << " ";
			p = p->next;
		}
		cout << endl;
	}
};

int main() {
	//整数链表
	List<int> L;
	int n=70;
	L.Append(70);L.Append(3);L.Append(5);
	L.Append(1);L.Append(8);
	L.Insert(55, 3);
	L.Delete(2);
	L.Display();//链表头赋值为-1，方便排序的进行，也更方便数据的存取 
	cout<< L.Search(n) <<endl;
	cout << L[3]<<endl;
	//字符链表
	List<char> Lc;
	Lc.Append('a'); Lc.Append('b'); Lc.Append('c');
	Lc.Insert('d', 3);
	Lc.Delete(3);
	Lc.Display();
	//浮点数链表
	List<double> Ld;
	Ld.Append(1.1); Ld.Append(2.2); Ld.Append(3.3);
	Ld.Insert(4.4, 3);
	Ld.Delete(4);
	Ld.Display();
}
