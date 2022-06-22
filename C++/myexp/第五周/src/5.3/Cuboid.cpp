#include "Cuboid.h"

inline void Cuboid::setdata(){
	cout << "长方体的长、宽、高分别为：";
	cin >> length >> width >> height;
	if (length<=0|| width <= 0|| height <= 0){
		throw "无效输入";
	}
}

inline void Cuboid::volumevalue(){
	volume = length * width * height;
}

inline void Cuboid::printvalue(){
	cout << endl<<"长方体的长、宽、高为：";
	cout << length << ' ' << width << ' ' << height << endl ;
	cout << "长方体的体积为：";
	cout << volume << endl << endl;
}

int main()
{
	Cuboid cub;
	while (true){
		try{
			cub.setdata();
			break;
		}
		catch (const char*msg){
			cerr << msg << endl;
		}
	}
	cub.volumevalue();
	cub.printvalue();
	return 0;
}