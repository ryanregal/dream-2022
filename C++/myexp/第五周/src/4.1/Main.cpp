#include <iostream>

class Player {
private:
	int x, y;
	int speed;
public:
	int money;
};

int main() {
	Player player;
	//player.x = 5;//不可以修改私有变量
	player.money = 10;
}