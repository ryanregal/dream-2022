#include <iostream>
using namespace std;

int a[7][2] = { {0,0},{1,2},{2,4},{4,3},{6,4},{7,2},{8,4} };
struct node {
	int x;
	int y;
	struct node* next;
};

int main() {
	node* head = nullptr;
	node* p = nullptr;
	node* q = nullptr;
	head = new node;
	p = q = head;
	head->x = a[0][0], head->y = a[0][1];

	for (int i=1;i<7;i++){
		q = new node;
		q->x = a[i][0], q->y = a[i][1];
		p->next = q;
		q->next = nullptr;
		p = q;
	}
	q = head;
	while (q!=nullptr){
		cout <<  q->x << "," << q->y  << endl;
		q = q->next;
	}
}