#pragma once
#include<iostream>
using namespace std;
class Cuboid
{
private:
	float length;
	float width;
	float height;
	float volume;
public:
	void setdata();
	void volumevalue();
	void printvalue();
};