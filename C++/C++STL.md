# 模板+STL

## 1 模板

模板：建立通用模具，提高复用性

- 模板不可以直接使用 只是一个框架
- 模板的通用不是万能的

### 1.1 函数模板

建立一个通用函数，其函数**返回值、形参类型**可不具体指定 用一个**虚拟类型**代表

- 编译器会对函数模板进行**两次编译**，在声明的地方对模板代码进行编译，在调用的地方对参数替换后进行编译

语法：

```c++
template<typename T>
函数声明或定义
// template - 声明创建模板
// typename - 表明其后面的符号是一种数据类型，可用class代替
// T - 通用的数据类型 名称可以替换 通常为大写字母
```

使用方法：

```c++
template<typename T>
void Swap(T &a, T &b){
    T tem = a;
    a = b;
    b = tem;
}

//利用函数模板进行两个数的交换
int a=10, b=20;
//方法1. 自动类型推导 直接传入实参，编译器自己推导类型
Swap(a,b);
//方法2. 显示指定类型 主动告诉编译器类型
Swap<int>(a,b);
```

注意：

- 自动类型推导法：必须推导出一致的数据类型T 才可以使用
- 模板必须确定T的数据类型(即使不会用到T) 才可以使用

```c++
template<class T>
void func(){
    cout<<endl;
}

void test(){
    func();//报错！！这样不可以调用func函数
    func<int>();//正确 必须确定T的数据类型(即使不会用到T) 才可以使用
}
```

**普通函数与函数模板区别：**

1. 普通函数调用时可以发生自动类型转换（隐式类型转换）
2. 函数模板调用时，自动类型推导--不会隐式类型装换；显示指定类型--可以隐式类型转换

> 隐式类型转换：即形参是int，传入char型数据 系统可自动转换成ASCII码的int

**普通函数与函数模板调用规则：**

1. 函数模板可以重载
2. 如果函数模板和普通函数都可以实现，优先调用普通函数
3. 可以通过空模板参数列表来强制调用函数模板
4. 若函数模板可以产生更好的匹配，优先调用函数模板

（有了函数模板 最好不要提供与模板同名的普通函数，容易出现二义性）

```c++
void Print(int a, int b){
    cout<<"1"<<endl;
}

template<class T>
void Print(T a, T b){
    cout<<"2"<<endl;
}

//函数模板可以重载
template<class T>
void Print(T a, T b, T c){
    cout<<"3"<<endl;
}

int a=1, b=2, c=3;

//如果函数模板和普通函数都可以实现，优先调用普通函数
Print(a,b);//1

//可以通过空模板参数列表来强制调用函数模板
Print<>(a,b);//2

//函数模板可以重载
Print(a,b,c);//3

//若函数模板可以产生更好的匹配，优先调用函数模板
char c='c', d='d';
Print(c,d);//2
//若调用1 需要隐式类型转换；调用2 只要用char代替T即可
```

#### 模板的局限性

模板不能比较两个类的大小---解决方法：提供模板的重载，为**特定类型提供具体化的模板**

- 利用具体化的模板，可以解决自定义类型的通用化
- 学习模板不是为了写模板，是为了在STL运用系统提供的模板

```c++
template<class T>
bool compare(T a,T b){
    if(a==b){
        return true;
    }
    else{
        return false;
    }
}

//重载 使之能比较类
template<>bool compare(Person &p1, Person &p2){//注意此处重载的格式
    if(p1.m_name==p2.m_name && p1.m_age==p2.m_age){
        return true;
    }
    else{
        return false;
    }
}

class Person{
public:
    Person(string name, int age){
        m_name = name;
        m_age = age;
    }
    
    string m_name;
    int m_age;
}

Person p1("Tom",10);
Person p2("Tom",10);
compare(p1,p2);
```



#### 函数模板案例：数组排序

​	利用函数模板封装一个排序函数 可以对不同数据类型数组进行排序：降序排序、选择排序

```c++
template<typename T>
void Swap(T& a, T& b) {
	T tem = a;
	a = b;
	b = tem;
}

template<typename T>
void SelectSort(T arr[], int len) {
	for (int i = 0; i < len; i++) {
		int max = i;
		for (int j = i + 1; j < len; j++) {
			if (arr[max] < arr[j]) {
				max = j;
			}
		}
		if (max != i) {
			Swap(arr[max], arr[i]);
		}
	}
}

template<typename T>
void Display(T arr[],int len) {
	for (int i = 0; i < len; i++) {
		cout << arr[i];
	}
	cout << endl;
}

int main() {
	char arr1[20] = "bcdfea";
	int len = strlen(arr1);
	SelectSort(arr1, len);
	Display(arr1, len);

	return 0;
}
```

#### 函数模板与普通函数

https://blog.csdn.net/jack123345667/article/details/107184316

1. 函数模板可以像普通函数一样被重载
2. C++优先考虑普通函数
3. 如果函数模板可以产生一个更好的匹配，那么选择模板
4. 可以通过空模板实参列表的语法限定编译器只能通过模板匹配<>

```C++
// 模板函数
template<class Type>
void SwapData(Type& a, Type& b) {
	cout << "函数模板!" << endl;
	Type temp = a;
	a = b;
	b = temp;
} 
// 重载函数模板
template<class Type>
void SwapData(Type& a, Type& b, Type& c) {
	cout << "函数模板!" << endl;
	Type temp = a;
	a = b;
	b = temp;
}
```



### 1.2 类模板

建立一个通用类，类中的**成员 数据类型**可以不具体指定，用一个虚拟的类型来代表

```c++
//多个虚拟类型
template<class NameType, class AgeType = int>//类模板在模板参数列表中可以有默认参数
class Person {
public:
	Person(NameType name, AgeType age) {
		m_name = name;
		m_age = age;
	}

	NameType m_name;
	AgeType m_age;
};

void test() {
	Person<string, int>p1("Tom", 19);
    //或 Person<string>p1("Tom", 19); 省略int 因为有默认参数
	cout << p1.m_name << " " << p1.m_age;
}
```

**类模板与函数模板区别：**

1. 类模板没有自动类型推导的使用方式
2. 类模板在模板参数列表中可以有默认参数

**类模板中成员函数和普通类中成员函数创建时机：**普通类中成员函数一开始就创建了，类模板中的成员函数在调用时才创建

```c++
class Person1 {
public:
	void show1() {
		cout << "1" << endl;
	}
};

class Person2 {
public:
	void show2() {
		cout << "2" << endl;
	}
};

template<class T>
class Cla {
public:
	T obj;

	void func1() {
		obj.show1();
	}

	void func2() {
		obj.show2();
	}
};

void test() {
	Cla<Person1>obj1;
	obj1.func1();//可以
	//obj1.func2();//会运行错误 但编译无错误；因为类模板中成员函数不是一开始就创建 只有调用后才会创建 然后这里才报错
}
```

#### 1.2.1 类模板对象做函数参数

类模板也可以实例化对象给函数传参，传入方式有：

1. 指定传入类型：直接显示对象的数据类型
2. 参数模板化：将对象中的参数变为模板进行传递
3. 整个类模板化：将这个对象类型模板化进行传递

```c++
template<class T1, class T2 = int>
class Person{
public:
    Person(T1 name, T2 age){
        m_name=name;
        m_age=age;
    }
    void showInfo(){
        cout<<m_name<<m_age<<endl;
    }
    
    T1 m_name;
    T2 m_age;
}

//1. 指定传入的类型
void show1(Person<string, int> &p){
    p.showInfo();
}

//2. 参数模板化
template<class T1, class T2>
void show2(Person<T1, T2> &p){
    p.showInfo();
    cout << typeid(T1).name() << endl;//输出T1的类型在内存中的名字
    cout << typeid(T2).name() << endl;//输出T2的类型在内存中的名字
}

//3. 整个类模板化
template<class T>
void show3(T &p){
    p.showInfo();
    cout << typeid(T).name() << endl;
}

Person<string, int>p("zhangsan",18);
show1(p);
show2(p);
show3(p);
```

#### 1.2.2 类模板与继承

- 子类继承的父类若是一个类模板，子类在声明时 要指定出父类中的T的类型；若不指定 则编译器无法给子类分配内存
- 若想灵活指定父类中T的类型，子类也需变成模板

```c++
template<class T>
class Bass{
    T m;
};

//父类若是一个类模板，子类在继承时 要指定出父类中的T的类型
class Son: public Base<int>{
    
};

//若想灵活指定父类中T的类型，子类也需变成模板
template<class T1, class T2>
class Son: public Base<T2>{
    T1 n;
};
```

#### 1.2.3 类模板成员函数类外实现

```c++
template<class T1, class T2 = int>
class Person{
public:
    //只有成员函数声明
    Person(T1 name, T2 age);
    void showInfo();
    
    T1 m_name;
    T2 m_age;
}

//构造函数类外实现
template<class T1, class T2>
Person<T1, T2>::Person(T1 name, T2 age)
{
        m_name=name;
        m_age=age;
}

//成员函数类外实现
template<class T1, class T2>
void Person<T1, T2>::showInfo()
{
        cout<<m_name<<m_age<<endl;
}
```

#### 1.2.4 类模板份文件编写

类模板中成员函数创建时机是在调用阶段，导致分文件编写时链接不到

假设 .h 中有类的声明，.cpp 中有类的成员函数定义，解决方法：

1. 直接包含 .cpp 源文件
2. 将声明与实现写到同一个头文件中，并改后缀名为 **.hpp** hpp是约定的名称 并不是强制

#### 1.2.5 类模板与友元

全局函数类内实现：直接在类内声明友元即可 (用法更简单)

全局函数类外实现：需要提前让编译器知道全局函数的存在

```c++
template<class T1, class T2>//还要让编译器知道Person是个类模板
class Person;
//类外定义：定义必须在声明之前 让编译器知道有这个函数的存在；而此时参数中有个Person 需要提前声明Person
//该声明是一个函数模板的声明
template<class T1, class T2>
void showInfo2( Person<T1, T2> &p ){
    	cout << p.m_name << p.m_age << endl;
}

template<class T1, class T2>
class Person{
    //全局函数 类内实现 - 配合友元
    friend void showInfo1( Person<T1, T2> &p ){
    	cout << p.m_name << p.m_age << endl;
    }
    
    //全局函数 类外实现
    friend void showInfo2<>( Person<T1, T2> &p );//类内进行声明
    //此处要加一个空括号 告诉编译器这是一个函数模板的声明；不加这个括号的话 会被当做普通函数的声明 两者不匹配
    
public:
    Person(T1 name, T2 age){
        m_name=name;
        m_age=age;
    }

private:
    T1 m_name;
    T2 m_age;
};

void test(){
    Person<string, int>p("zhang",18);
    
    //全局函数 类内实现 - 配合友元
    void showInfo1(p);
}
```



#### 类模板中的static

1. 从类模板实例化的每一个模板类有自己的类模板数据成员，该模板的所有对象共享一个 static 数据成员
2. 和非模板类的static数据成员一样，模板类的static数据成员也应该在文件范围定义和初始化
3. 每个模板类有自己类模板的static数据成员的副本

```C++
template<class T>
class Person{
public:
	static T a;
};

//类外初始化
template <class T> T Person<T>::a = 0;

int main(void)
{

	Person<int> p1, p2, p3;
	Person<char> pp1, pp2, pp3;

	p1.a = 10;
	pp1.a = 100;

	cout << p1.a << " " << p2.a << " " << p3.a << endl;
	cout << pp1.a << " " << pp2.a << " " << pp3.a << endl;

	return 0;
}
```



#### 类模板案例：数组类封装

需求分析：

1. 可对内置数据类型和自定义数据类型的数据进行存储
2. 数组数据存储到堆区
3. 构造函数中可以传入数组容量
4. 提供对应的拷贝构造函数以及operator=防止浅拷贝
5. 提供尾插法和尾删法对数组元素增删
6. 可通过下标方式访问数组元素
7. 可获取数组当前元素个数和数组容量

.hpp文件：

```c++
#pragma once
#include <iostream>
using namespace std;
#include <string>

//数组类
template<class T>
class MyArr {
public:
	//构造函数
	MyArr(int capacity) {
		cout << "构造函数" << endl;
		m_capacity = capacity;
		m_size = 0;
		m_arr = new T[capacity];
	}

	//拷贝构造函数
	MyArr(const MyArr& arr) {
		cout << "拷贝构造函数" << endl;
		this->m_capacity = arr.m_capacity;
		this->m_size = arr.m_size;
		this->m_arr = new T[arr.m_capacity];//深拷贝
		for (int i = 0; i < arr.m_size; i++) {
			this->m_arr[i] = arr.m_arr[i];
		}
	}//注意区分这里的拷贝构造和=重载
	//前者是创建新对象时拷贝一份已有对象
	//后者是已创建好的对象重新赋值，所以需要释放原有空间

	//operator= 重载
	MyArr& operator=(const MyArr& arr) {
		cout << "重载=函数" << endl;
		//先判断原来是否有数据，有的话先释放
		if (this->m_arr != NULL) {
			delete[]this->m_arr;
			this->m_arr = NULL;
			this->m_capacity = 0;
			this->m_size = 0;
		}
		this->m_capacity = arr.m_capacity;
		this->m_size = arr.m_size;
		this->m_arr = new T[arr.m_capacity];//深拷贝
		for (int i = 0; i < arr.m_size; i++) {
			this->m_arr[i] = arr.m_arr[i];
		}
		return *this;//别忘返回值！！！
	}

	//尾插法
	void Insert(const T& e) {
		//先判断是否满了
		if (this->m_size == this->m_capacity) {
			cout << "数组已满" << endl;
			return;
		}
		this->m_arr[this->m_size] = e;
		this->m_size++;
	}
    
	//尾删法
	void Delete() {
		//是否为空
		if (this->m_size == 0) {
			cout << "数组为空" << endl;
			return;
		}
		this->m_size--;
	}

	//通过下标访问数据
	T getValue(int index) {
		return this->m_arr[index];
	}

	//得到容量
	int getCapacity() {
		return this->m_capacity;
	}

	//得到数组当前大小
	int getSize() {
		return this->m_size;
	}

	//析构函数
	~MyArr() {
		cout << "析构函数" << endl;
		if (m_arr != NULL) {
			delete[]m_arr;
			m_arr = NULL;
		}
	}

private:
	T* m_arr;//指向数组
	int m_capacity;//数组容量
	int m_size;//数组当前大小
};
```

.cpp文件：

```c++
#include <iostream>
using namespace std;
#include <string>
#include "test.hpp"

class Person {
public:
	Person(){}//为什么必须有这一行？？？
	//因为.hpp中new开辟了Person的堆区数据 这些数据没有参数所以需要调用无参构造
	//但自己写了有参构造则系统不会默认提供无参构造 所以需要自己写

	Person(string name, int age) {
		this->m_age = age;
		this->m_name = name;
	}

	int m_age;
	string m_name;
};

//打印数组所有元素
void Display(MyArr<Person>& arr) {//注意这里的写法！！！
	for (int i = 0; i < arr.getSize(); i++) {
		cout << arr.getValue(i).m_name << endl;
	}
}

void test() {
	//自定义类型
	MyArr<Person>arr2(5);
	Person p("Tom", 100);
	arr2.Insert(p);
	Display(arr2);

	//MyArr<int>arr1(5);//构造函数

	//for (int i = 0; i < 5; i++) {
	//	arr1.Insert(i);
	//}

	//Display(arr1);

	//MyArr<int>arr2(arr1);//拷贝构造

	//MyArr<int>arr3(15);
	//arr3 = arr1;//=重载
}

int main() {
	test();
	return 0;
}
```

## 2 STL

STL：standard template library 标准模板库

STL从广义上分为：容器(container)、算法(algorithm)、迭代器(iterator)，容器算法之间通过迭代器进行无缝连接

STL六大组件：容器、算法、迭代器、仿函数、适配器（配接器）、空间配置器

1. 容器：各种数据结构，如vector、list、deque、set、map等，用来存放数据
2. 算法：如sort、find、copy、for_each
3. 迭代器：算法、容器之间的胶合剂
4. 仿函数：行为类似函数，可作为算法的某种策略
5. 适配器：一种用来修饰容器或仿函数或迭代器接口的东西
6. 空间配置器：负责空间的配置与管理

STL几乎所有代码都采用模板类或模板函数，目的是提高代码复用性

### 2.1 容器

**序列式容器**：强调值的排序，其中每个元素均有固定位置

**关联式容器**：二叉树结构，各元素之间无严格物理顺序关系

### 2.2 算法

**质变算法**：运算过程中会更改区间内元素的内容，如拷贝 替换 删除

**非质变算法**：运算过程中不会更改区间内元素的内容，如查找 计数 遍历 寻找极值

### 2.3 迭代器

**迭代器**：提供一种方法，使之能依序寻访某个容器内各个元素 而又无需暴露该容器内部表示方式；每个容器都有自己专属迭代器；迭代器使用类似指针；描述为内联函数

`vector<Type>::const_iterator it` 加了const，it可以++，但不可通过it来修改元素的值——只读

`const vector<...>::iterator` 指向不可变 不可++，指向元素的值可以修改

迭代器种类：

| 种类           | 功能                                                 | 支持的运算                              |
| -------------- | ---------------------------------------------------- | --------------------------------------- |
| 输入迭代器     | 对数据只读访问                                       | 只读，支持++、==、!=                    |
| 输出迭代器     | 只写访问                                             | 只写，支持++                            |
| 前向迭代器     | 读写操作，并能向前推进迭代器                         | 读写，支持++、==、!=                    |
| 双向迭代器     | 读写操作，并能向前和向后推进迭代器                   | 读写，支持++、--                        |
| 随机访问迭代器 | 读写操作，以跳跃方式访问任意数据，是功能最强的迭代器 | 读写，支持++、--、[n]、-n、<、<=、>、>= |

常用的为：双向、随机访问迭代器

## 3 STL-常用容器

### size_type配套类型

由string类类型和vector类类型定义的类型，用以保存任意string对象或vector对象的长度，标准库类型将size_type定义为unsigned类型；string::size_type它在不同的机器上，长度是可以不同的，并非固定的长度。但只要你使用了这个类型，就使得你的程序适合这个机器。与实际机器匹配。

```C++
for(vector<int>::size_type i=0; i != v.size(); i++){
    cout << v[i] << endl;
}
```



### 3.1 Vector容器

和数组相似，称为**单端数组**，一般在尾部插入删除 前端封闭

与普通数组区别：数组是静态空间，vector可动态扩展（并非在原空间后接新空间 而是寻找更大内存空间 再将原数据拷贝过来 再释放原空间）

vector容器的迭代器是支持随机访问的迭代器

`v.rend(); //迭代器 指向第一个元素之前的那个位置`

`v.rbegin(); //迭代器 指向最后一个元素的位置`

`v.front(); v.back(); //第一和最后一个元素`

#### 3.1.1 vector构造函数

函数原型：

- `vector<T> v;`
- `vector<T> v(length);` 定义length个元素，若T为int 初值为0
- `vector<T> v(length, a);` 定义length个元素，初始化为a
- `vector<T> v(v1);` 
- `vector( v.begin(), v.end() );` 将 v[ begin(), end() ) 前闭后开区间中的元素拷贝给本身
- `vector(n, elem);` 将n个elem拷贝给本身
- `vector(const vector& v);` 拷贝构造函数

#### 3.1.2 vector赋值操作

函数原型：

- `vector& operator=(const vector& vec);` 重载=
- `assign(beg, end);` 将 [beg, end) 区间中的数据拷贝赋值给本身
- `assign(n, elem);` 将n个elem拷贝赋值给本身

#### 3.1.3 vector容量和大小

函数原型：

- `empty();`

- `capacity();`

- `size();`

- `resize(int num);`

  `resize(int num, elem);` 重新指定容器长为num，若容器变长 则以elem或默认值0填充新位置；若变短 则末尾超出部分的元素删除

#### 3.1.4 vector插入和删除

函数原型：

- `push_back(ele);`

- `pop_back();`

- `insert(const_iterator pos, ele);` 迭代器指向位置pos插入ele

  `insert(const_iterator pos, int cnt, ele);` 迭代器指向位置pos插入cnt个ele

- `erase(const_iterator pos);`

  `erase(const_iterator start, const_iterator end)` 删除start到end之间的元素

- `clear();` 删除容器中所有元素

```c++
vector<int> v;
for(int i=0; i<10; i++){
    v.push_back(i);
}
v.insert(v.begin()+1, 100);
```

向该容器中插入、遍历内置数据：

```c++
#include <vector>
#include <algorithm>

void func(int val){
    cout << val << endl;
}

void test(){
    //创建一个vector容器
    vector<int> v;//v是该容器的名字
    
    //插入数据：push_back内置函数
    for(int i=0; i<10; i++){
        v.push_back(i);
    }
    
    //通过迭代器访问数组中的数据
    vector<int>::iterator itBegin = v.begin();//起始迭代器 指向第一个元素
    vector<int>::iterator itEnd = v.end();//结束迭代器 指向最后一个元素的下一个元素
    
    //第一种遍历方式
    while( itBegin != itEnd){
        cout << *itBegin << endl;
        itBegin++;
    }
    
    //第二种遍历方法
    for(vector<int>::iterator it = v.begin(); it != v.end(); it++){
        cout << *it << endl;
    }
    
    //第三种遍历方法：利用STL提供的遍历算法
    for_each(v.begin(), v.end(), func);//func为自定义函数 对数组中的元素进行的操作
}
```

#### 3.1.5 vector数据存取

函数原型：

- `at(int idx);`
- `operator[];`
- `front();` 第一个元素
- `back();` 最后一个元素

#### 3.1.6 vector互换容器

两容器的元素互换

函数原型：

- `swap(v);`

应用：利用swap收缩空间

```c++
vector<int> v;
for(int i=0; i<10000; i++){
    v.push_back(i);
}
//此时size==10000 但capacity==13000多--由于动态扩展
v.resize(3);//此时size==3 但capacity不变 浪费内存

vector<int>(v).swap(v);//利用拷贝构造函数创建匿名对象 拷贝一个v
//匿名对象一行之后自动销毁
```

#### 3.1.7 vector预留空间

减少vector在动态扩展容量时的扩展次数

函数原型：

- `reserve(int len);` 容器预留len个元素长度 预留位置不初始化 元素不可访问

预留空间的作用：假设现在size == a，则此时capacity大于a，当仍存储数据 存到size==a时就要重新动态扩展开辟一个新空间；预留空间的作用就是预先开辟这么一块空间 而不要总是重新动态扩展

```c++
vector<int> v;
int num=0;//计算重新动态扩展个数
v.reserve(10000);//本来num为30多 加上这一行代码后num==1
int* p=NULL;
for(int i=0; i<10000; i++){
    v.push_back(i);
    
    if(p != &v[0]){
        p=&v[0];
        num++;
    }
}
```

#### 3.1.8 vector容器嵌套容器

```c++
vector<vector<int>>v;

	vector<int>v1;
	vector<int>v2;
	vector<int>v3;
	vector<int>v4;

	for (int i = 0; i < 4; i++) {
		v1.push_back(i);
		v2.push_back(i+1);
		v3.push_back(i+2);
		v4.push_back(i+3);
	}

	//把小容器放进大容器中
	v.push_back(v1);
	v.push_back(v2);
	v.push_back(v3);
	v.push_back(v4);

	//用大容器遍历数据
	for (vector<vector<int>>::iterator it = v.begin(); it != v.end(); it++) {
		for (vector<int>::iterator vit = it->begin(); vit != it->end(); vit++) {
			cout << *vit << endl;
		}
	}
```



### 3.2 string容器

**本质**：是一个类

**string和char*区别**：

1. char* 是一个指针
2. string 是一个类，类内部封装了char* ，是一个char*型的容器

**特点**：

1. string类内部封装了很多成员方法：如查找find、删除delete、替换replace、插入insert
2. string管理char*所分配的内存，不用担心复制越界和取值越界等，由类内部进行负责

**string类型的输入符操作**：

1. 读取并忽略开头所有空白字符（空格 换行 制表符）

2. 读取字符直到再次遇到空白字符就终止（所以以空格为分割！）

   > 如果要读取一行：
   >
   > **普通成员函数**`getline(cin,str);` 从输入流的下一行读取，保存到str中，但不读取换行符；若行开头就是换行符，就停止读入
   >
   > `cin.get()` 往往用来从流中抽取换行符，防止下一次读入时因读入换行符而提前结束
   >
   > **输入流成员函数** `cin.getline(name,256,'a');` 读取255个字符放入name 最后一个放\0，有第三个参数 - 结束字符，输出时不输出该字符，省略时默认为\0

#### int 转 string类型

```c++
	// 方法1
    int nNum1 = 123;
    stringstream ss;
 
    ss << nNum1;// 将int类型的值放入输入流中
    string strTest1 = ss.str();
    cout << "strTest1 is: " << strTest1 << endl;
 
    string strTest2;
    ss >> strTest2;// 从sstream中抽取前面插入的int类型的值，赋给string类型
    cout << "strTest2 is: " << strTest2 << endl;
 
    // 方法2
    int nNum2 = 456;
    string strTest4;
    strTest4 = to_string(nNum2);    // C++11 标准
    cout << "strTest4 is: " << strTest4 << endl;
```



#### 3.2.1 string构造函数

函数原型：

- `string();`

  `string(const char* s);`

- `string(const string& str);` 使用一个string对象初始化另一个string对象

- `string(int n, char c);` 使用n个字符c初始化

```c++
string s1;

string s2("hello");

string s3(s2);//拷贝构造

string s4(10,'a');
```

#### 3.2.2 string赋值操作

函数原型：

- `string& operator=(const char* s);`

  `string& operator=(const string& s);` 自带=重载

- `string& operator=(char c);0`

- `string& assign(const char* s);`

  `string& assign(const string& s);`

- `string& assign(const char* s, int n);` 把s的前n个字符赋值给当前字符串

  `string& assign(int n, char c);` 用n个字符c赋给当前字符串

```c++
string s;
s.assign("hello", 3);
```

#### 3.2.3 string字符串拼接

函数原型：

- `string& operator+=(const char* str);`

  `string& operator+=(const string& str);`

  `string& operator+=(const char c);`

- `string& append(const char* str);`

  `string& append(const string& str);`

- `string& append(const char* str, int n);` 把s的前n个字符连接到当前字符串尾

- `string& append(const string& s, int pos, int n);` 把s中从pos开始的n个字符连接到当前字符串尾

字符串常量不可以拼接：`string s = "hh" + "oo"; //错误！！`

#### 3.2.4 string查找替换

函数原型：

- `int find(const string& str, int pos = 0);` 从pos开始查找str第一次出现的位置
- `int find(const string& str, int pos, int n);` 从pos开始查找str的前n个字符第一次出现的位置
- `int find(const char c, int pos = 0);` 从pos开始查找字符c第一次出现的位置
- `int rfind(const string& str, int pos = npos);` 从pos开始查找str最后一次出现的位置
- `int rfind(const string& str, int pos, int n);` 从pos开始查找str的前n个字符最后一次出现的位置
- `int rfind(const char c, int pos = npos);` 从pos开始查找字符c最后一次出现的位置
- `string& replace(int pos, int n, const string& str);` 替换从pos开始的n个字符为str

#### 3.2.5 string字符串比较

函数原型：

- `int compare(const string& s);` ==返回0；<返回-1；>返回1

#### 3.2.6 string字符存取

函数原型：

- `char& operator[](int n);`
- `char& at(int n);`

```c++
string s = "hello";
s.at[1]='a';
cout << s[1];
```

#### 3.2.7 string插入和删除

函数原型：

- `string& insert(int pos, const char* s);`
- `string& insert(int pos, int n, char c);`
- `string& erase(int pos, int n = npos);` 删除从pos开始的n个字符

#### 3.2.8 string子串

函数原型：

- `string substr(int pos = 0; int n = npos);` 返回从pos开始的n个字符串

#### 应用

find与substr配合

```c++
string email = "zhangsan@qq.com";
int pos = email.find("@", 0);
string userName = email.substr(0,pos);
```

数字字符串string转化成int

```c++
string str="123";
atoi( str.c_str() );//转化成了int型的123
//string类中有内置函数c_str() - 把string转化成char*
//atoi - 把char*转化成int
```

### 3.3 deque容器

双端数组，对头端数组进行插入删除操作

**deque与vector区别**：

1. vector对于头部的插入删除效率低，数据量越大效力越低；deque对头部插入删除操作相对较快
2. vector访问元素时速度比deque快，这与两者的内部实现有关

**deque内部工作原理**：内部有一个中控器，维护每段缓冲区中的内容，缓冲区中存放真实数据；中控器维护的是每个缓冲区的地址，使得使用deque时像一片连续的内存空间

deque的迭代器支持随机访问

#### 3.3.1 deque构造函数

函数原型：

- `deque<T>;`
- `deque(beg, end);` 将 [beg, end] 区间中的元素拷贝给本身
- `deque(n, elem);` 将n个elem拷贝给本身
- `deque(const deque& deq);` 拷贝构造

```c++
void printDeque(const deque<int>& d){//为了防止不小心修改d中数据 加一个const
    for(deque<int>::const_iterator it = it.begin(); it != d.end(); it++){
        //for循环条件中的iterator也必须加上const 否则会报错
        cout << *it << endl;
    }
}
```

#### 3.3.2 deque赋值操作

函数原型：和vector一样

- `deque& operator=(const deque& d);` 重载=
- `assign(beg, end);` 将 [beg, end) 区间中的数据拷贝赋值给本身
- `assign(n, elem);` 将n个elem拷贝赋值给本身

#### 3.3.3 deque大小操作

函数原型：

- `empty();`
- `size();` (deque没有容量的概念)
- `resize(int num);`
- `resize(num, elem);`

#### 3.3.4 deque插入和删除

两端插入操作：

- `push_back(ele);`
- `push_front(ele);`
- `pop_back();`
- `pop_front();`

指定位置操作：

- `insert(pos, ele);` 返回新数据的位置
- `insert(pos, n, ele);` 无返回值
- `insert(pos, beg, end);` 在pos处插入[beg,end)区间的数据 无返回值
- `clear();` 清空容器内所有数据
- `erase(beg, end);` 返回下一个数据的位置
- `erase(pos);` 删除pos位置数据，返回下一个数据位置

#### 3.3.5 deque数据存取

函数原型：

- `at(int idx);`
- `operator[];`
- `front(); back();`

#### 3.3.6 deque排序操作

函数原型：

- `sort(iterator beg, iterator end);` 对beg到end区间内元素排序

对于支持随机访问的迭代器的容器 都可以用sort 包含头文件<algorithm>

### 案例：评委打分

案例描述：5选手 10评委；去除最高、最低分 取平均分

步骤：

1. 创建5选手 放进vector
2. 遍历vector 取出每一个选手再for循环 把10个分数放进deque
3. sort对分数排序 去除极值分数（因为用deque方便前后删除最高最低分）
4. 对deque数据遍历累加求平均

```c++
#include <iostream>
using namespace std;
#include <string>
#include <vector>
#include <deque>
#include <algorithm>
#include <time.h>

#define NUM 5 //选手人数
#define STRNUM "12345" //选手序号
#define JUDGENUM 10 //评委人数

class Person {
public:
	Person(string name, int score) {
		this->m_name = name;
		this->m_score = score;
	}
    
	string m_name;
	int m_score;
};

void createPerson(vector<Person>& v) {
	string nameSeed = STRNUM;//选手序号
	for (int i = 0; i < NUM; i++) {
		string name = "NO.";
		name += nameSeed[i];//巧用字符串拼接
		Person p(name, 0);
		v.push_back(p);
	}
}

void setScore(vector<Person>& v) {
	srand(time(0));

	for (vector<Person>::iterator it = v.begin(); it != v.end(); it++) {
		deque<int>d;
		//打分 10个随机数
		for (int i = 0; i < JUDGENUM; i++) {
			int tem = rand() % 41 + 60;//60~100之间的数
			d.push_back(tem);
		}
		//排序
		sort(d.begin(), d.end());
		cout << "选手" << it->m_name << ":" << endl;
		for (int i = 0; i < d.size(); i++) {//注意这里用size最好！
			cout << d[i] << " ";
		}//输出排序后的分数
		cout << endl;
		//去除最高分最低分 求平均
		d.pop_back();
		d.pop_front();
		int sum = 0;
		for (int i = 0; i < d.size(); i++) {//注意这里用size最好！
			cout << d[i] << " ";
			sum += d[i];
		}//输出去除最高分最低分后的分数
		cout << endl;

		it->m_score = sum / d.size();//注意这里用size最好！
		cout << "average: " << it->m_score << endl;
	}
}

void test() {
	vector<Person>v;

	//1. 创建选手
	createPerson(v);

	//test：
	for (int i = 0; i < NUM; i++) {
		cout << v[i].m_name << " " << v[i].m_score << endl;
	}
	//遍历的另一种方法
	for (vector<Person>::iterator it = v.begin(); it != v.end(); it++) {
		cout << (*it).m_name << " " << (*it).m_score << endl;
	}

	//2. 给选手打分 并输出平均分
	setScore(v);
}
```

### 3.4 stack容器

栈：`top(); push(); pop();`

栈不允许有遍历行为

#### 3.4.1 stack常用接口

构造函数：

- `stack<T> stk;`
- `stack(const stack& stk);` 拷贝构造

赋值操作：

- `stack& operator=(const stack& stk);`

数据存取：

- `push(ele); pop();`
- `top();` 返回栈顶元素

大小操作：

- `empty();`
- `size();`

### 3.5 queue容器

队列：`push(); pop(); back(); front();`

队列不允许有遍历行为

#### 3.5.1 queue常用接口

和stack基本一样 没有top

- `front(); back();` 返回第一个 最后一个元素

### 3.6 list容器

链表：数据逻辑顺序是通过指针链接实现；由一系列**结点**组成

STL中的链表是一个双向循环链表

重要性质：插入删除操作都不会造成原有迭代器失效，这与vector不同（vector每次插入删除数据都要动态扩展 找一块新空间 原有迭代器仍指向原有空间--失效了）

#### 3.6.1 list构造函数

函数原型：与vector相同

- `list<T> l;`
- `list( begin(), end() );` 将 [ begin(), end() ) 前闭后开区间中的元素拷贝给本身
- `list(n, elem);` 将n个elem拷贝给本身
- `list(const list& v);` 拷贝构造函数

#### 3.6.2 list赋值和交换

函数原型：与vector相同

- `list oprator=(const vector& vec);` 重载=
- `assign(beg, end);` 将 [beg, end) 区间中的数据拷贝赋值给本身
- `assign(n, elem);` 将n个elem拷贝赋值给本身
- `swap(lst);`

#### 3.6.3 list大小操作

函数原型：没有capacity

- `empty();`

- `size();`

- `resize(int num);`

  `resize(int num, elem);` 重新指定容器长为num，若容器变长 则以elem或默认值0填充新位置；若变短 则末尾超出部分的元素删除

#### 3.6.4 list插入和删除

两端插入操作：

- `push_back(ele);`
- `push_front(ele);`
- `pop_back();`
- `pop_front();`

指定位置操作：

- `insert(pos, ele);` 返回新数据的位置
- `insert(pos, n, ele);` 无返回值
- `insert(pos, beg, end);` 在pos处插入[beg,end)区间的数据 无返回值
- `clear();` 清空容器内所有数据
- `erase(beg, end);` 返回下一个数据的位置
- `erase(pos);` 删除pos位置数据，返回下一个数据位置
- `remove(elem);` 删除容器中所有与elem值匹配的元素

**双向迭代器只能前移和后移 不支持随时访问**，所以不能+n 也不能+1 只能++和--

#### 3.6.5 list数据存取

`front(); back();` 返回第一个 最后一个元素

#### 3.6.6 list反转和排序

`reverse(); sort();`

默认升序sort，自己写程序来实现降序

```c++
bool myCompare(int v1, int v2){
    return v1 > v2;
    //要想降序 就要v1>v2
}
l.sort(myCompare);
```

```c++
class Person{
public:
    string m_name;
    int m_age;
    int m_height;
}

//年龄升序；年龄相同则按身高降序
void m_compare(Person& p1, Person& p2){
    if(p1.m_age == p2.m_age){
        return p1.m_height > p2.m_height;
    }
    else{
        return p1.m_age < p2.m_age;
    }
}

l.sort(m_compare);
```



### 3.7 set 和 multiset 容器

集合容器：所有元素在插入时自动排序

本质：属于**关联式容器**，底层结构用**二叉树**实现

**set和multiset区别**：

1. set不允许容器中有重复元素，multiset允许有重复元素
2. set若输入了重复数据 只会有一份该数据
3. set插入数据的同时会返回插入结果 表示插入是否成功；multiset不会检测数据 因此可以插入重复数据

```c++
set<int>s;
pair< set<int>::iterator, bool > res = s.insert(10);//接受insert的返回值 是一个对组类型pair
if(res.second){//second为res中的第二个数据
    cout<<"第一次插入成功";
}
s.insert(10);//第二次插入返回值是FALSE 插入失败
```

#### 3.7.1 构造和赋值

构造：`set<T> st;` `set(const set& st);`

赋值：`set& operator=(const set& st);`

插入数据只有insert 没有push_back一类

#### 3.7.2 大小和交换

`size(); empty();` `swap(st);`

#### 3.7.3 插入和删除

- `insert(ele);`
- `clear();`
- `erase(pos);` 删除pos迭代器指向的元素 返回下一个元素的迭代器
- `erase(beg, end);` 返回下一个元素的迭代器
- `erase(ele);` 删除值为ele的元素

#### 3.7.4 查找和统计

- `find(key);` key若存在 返回该元素的迭代器；不存在 返回set.end()
- `count(key);` 统计key元素个数；对于set而言 结果为0或1 因为无重复元素

```c++
//判断是否找到
set<int>::iterator pos = s.find(30);
if(pos != s.end()){
    cout<<"找到了！" << *pos;
}
```

#### 3.7.5 pair对组创建

对组是指成对出现的数据，利用对组可以返回两个数据

创建方式：

- `pair<type,type> p (value1,value2)`
- `pair<type,type> p = make_pair(value1,value2)`

```c++
pair<string,int>p1("Tom",19);
pair<string,int>p2 = make_pair("Tom",19);
cout << p.first;//第一个数据-即string
```

#### 3.7.6 set容器排序

默认升序，利用仿函数可以改变排序规则

```c++
//关键是创建这个类 重载() 返回值是bool
//仿函数即仿照的函数，表示它功能与函数类似但并不是真正的函数，仿函数又叫函数对象
//在仿函数的类中，通常不需要定义构造函数和析构函数，这将由系统帮我们自动完成
class m_compare {
public:
	bool operator()(int v1, int v2)const {//这里必须加const！！！
		return v1 > v2;
	}
};

void test() {
	set<int, m_compare>s;
	s.insert(1);
	s.insert(4);
	s.insert(5);
	s.insert(7);
	s.insert(0);
	s.insert(8);
	for (set<int, m_compare>::iterator it = s.begin(); it != s.end(); it++) {
		cout << *it << endl;
	}
}
```

set容器存放自定义数据类型时制定排序规则

```c++
class m_compare {
public:
	bool operator()(const Person& v1, const Person& v2)const {//这里必须加const！！！
		return v1.m_age > v2.m_age;
	}
};

set<Person, m_compare>s;
```

### 3.8 map 和 multimap 容器

1. map中所有元素都是**pair**
2. pair中第一个元素为key（键值），起索引作用；第二个元素为value（实值）
3. 所有元素都会根据元素的**键值自动排序**，可根据key快速找到value

**本质**：关联式容器 底层结构通过二叉树实现

**map 和 multimap 区别**：map不允许有重复key的元素，multimap允许

#### 3.8.1 map构造和赋值

构造：

- `map<T1, T2> mp;`
- `map(const map& mp);`

赋值：

- `map& operator=(const map &mp);`

```c++
map<int,int> m;
m.insert(pair<int,int>(1,10));//1是key 10是value 根据key自动排序
```

#### 3.8.2 map大小和交换

`size(); empty(); swap(map);`

#### 3.8.3 map插入和删除

- `insert(ele);`
- `clear();`
- `erase(pos);` 删除pos迭代器所指向的元素 返回下一个元素的迭代器
- `erase(beg,end);`
- `erase(key);` 删除键值为key的元素

```c++
map<int,int>m;
//插入第一种
m.insert(pair<int,int>(1,10));
//插入第二种
m.insert(make_pair(2,20));
//插入第三种
m.insert(map<int,int>::value_type(3,30));
//插入第四种
m[4]=40;//key==4 value==40
//不建议用第四种进行插入，可用这一方法来进行元素访问
cout<<m[5];//因为如果没有key==5的pair 这里系统会自己创建一个m[5] 其值自动赋值为0
```

#### 3.8.4 map查找和统计

- `find(key);` key若存在 返回该元素的迭代器；不存在 返回set.end()
- `count(key);` 统计key元素个数 在map中结果为0或1

#### 3.8.5 map排序

利用仿函数改变默认升序规则

```c++
class MyCompare{
public:
    bool opretor()(int v1,int v2){
        return v1>v2;
    }
}

map<int,int,MyCompare>m;
```

#### 案例：员工分组

案例描述：

1. 10员工 全部进入公司后需要指派员工在哪个部门工作
2. 员工信息：姓名、工资组成；部门有：策划、美术、研发
3. 随机给10员工分配部门和工资
4. 通过multimap进行信息的插入：key（部门编号）、value（员工）
5. 分部门显示员工信息

实现步骤：

1. 创建10员工 放进vector
2. 遍历vector，取出每个员工进行随机分组
3. 分组后，将员工部门编号作为key 具体员工作为value，放进multimap（因为可能在同一个部门 key会重复）
4. 分部门显示员工信息

```c++
#include <iostream>
using namespace std;
#include <string>
#include <vector>
#include <map>
#include <time.h>

#define WORKER_NUM 10 //员工人数
#define CEHUA 0 //策划
#define MEISHU 1 //美术
#define YANFA 2 //研发
#define NAMESEED "ABCDEFGHIJ"//员工编号

class Worker {
public:
	Worker(string name, int salary) {
		this->m_name = name;
		this->m_salary = salary;
	}

	int m_salary;
	string m_name;
};

void createWorker(vector<Worker>& v, multimap<int, Worker>& mp) {
	srand(time(0));
	string nameSeed = NAMESEED;
	for (int i = 0; i < WORKER_NUM; i++) {
		//创建员工 存入vector
		string name = "员工";
		name += nameSeed[i];
		int salary = rand() % 10001 + 10000;//10000~20000
		Worker p(name, salary);
		v.push_back(p);

		//生成随机部门 存入multimap
		int depID = rand() % 3;//0 1 2
		mp.insert(pair<int, Worker>(depID, p));
	}
}

void showWorkerByGroup(multimap<int, Worker>& mp) {
	cout << "---------ce hua department----------" << endl;
	multimap<int, Worker>::iterator pos = mp.find(CEHUA);
	int num = mp.count(CEHUA);//巧用find和count
	for (int i = 0; i < num; i++) {
		cout << "name: " << pos->second.m_name << "\tsalary:" << pos->second.m_salary << endl;
		pos++;//注意这里pos迭代器也要变！
	}

	cout << "---------mei shu department----------" << endl;
	pos = mp.find(MEISHU);
	num = mp.count(MEISHU);
	for (int i = 0; i < num; i++) {
		cout << "name: " << pos->second.m_name << "\tsalary:" << pos->second.m_salary << endl;
		pos++;
	}

	cout << "---------yan fa department----------" << endl;
	pos = mp.find(YANFA);
	num = mp.count(YANFA);
	for (int i = 0; i < num; i++) {
		cout << "name: " << pos->second.m_name << "\tsalary:" << pos->second.m_salary << endl;
		pos++;
	}
}

void test() {

	//1. 创建员工 存入vector；同时生成随机部门和工资 存入multimap
	vector<Worker>v;
	multimap<int, Worker>mp;
	
	createWorker(v, mp);

	//2. 按部门显示员工 即按key排序输出

	showWorkerByGroup(mp);
}
```



## 4 STL-函数对象

### 4.1 函数对象

**概念**：重载**函数调用符**的类，其对象常称为**函数对象**；函数对象使用重载的()时，行为类似函数，也称为**仿函数**

**本质**：仿函数是一个类，不是一个函数

**函数对象使用特点**：

1. 在使用时，可以像普通函数那样点用 可以有参数、返回值
2. 函数对象超出普通函数概念 函数对象可以有自己的状态
3. 函数对象可以作为参数传递

```c++
class MyAdd{
public:
    MyAdd(){
        this->cnt = 0;//记录调用次数；
        //若是普通函数 则需要静态变量来记录 而仿函数可以有类内自己的状态
    }
    
    int operator()(int v1, int v2){
        return v1+v2;
        cnt++;
    }
    
    int cnt;
}

void doAdd(MyAdd& m,int a,int b){
    m(a,b);
}

MyAdd m;
res = m(10,10);//仿函数调用类似普通函数调用
doAdd(m,10,10);//函数对象可以作为参数传递
```

### 4.2 谓词

**谓词**：返回bool类型的仿函数

**一元谓词**：operator() 只接受一个参数

**二元谓词**：operator() 接受两个参数

查找vector中是否有大于5的数据：

```c++
#include <vector>
#include <algorithm>

class GreaterThan5 {
public:
	bool operator()(int v) {
		return v > 5;
	}
};

void test() {
	vector<int>v;
	for (int i = 0; i < 10; i++) {
		v.push_back(i);
	}
	vector<int>::iterator pos = find_if(v.begin(), v.end(), GreaterThan5());
	cout << *pos;
	//应该要用GreatThan5类创建一个类对象再传参 这里使用了匿名对象
	//find_if返回值是迭代器：若找到 返回找到的第一个元素的迭代器；找不到 返回end()
}
```

### 4.3 内建函数对象

内建函数对象：STL内已经建立好的函数对象

头文件 `#include <functional>` 这些仿函数产生的对象，用法和普通函数完全相同

#### 4.3.1 算术仿函数

实现四则运算，其中negate是一元运算 其他都是二元运算

仿函数原型：

- `templlate<class T> T plus<T>` 加法仿函数
- `templlate<class T> T minus<T>` 减法
- `templlate<class T> T multiplies<T>` 乘法
- `templlate<class T> T divides<T>` 除法
- `templlate<class T> T modulus<T>` 取模
- `templlate<class T> T negate<T>` 取反

```c++
#include <functional>

negate<int>n;
n(50);//输出-50
```

#### 4.3.2 关系仿函数

仿函数原型：

- `template<class T> bool equal_to<T>` ==
- `template<class T> bool not_equal_to<T>` !=
- `template<class T> bool greater<T>` >
- `template<class T> bool greater_equal<T>` >=
- `template<class T> bool less<T>` <
- `template<class T> bool less_equal<T>` <=

```c++
sort(v.begin(), v.end(), greater<int>());//降序排序 匿名函数对象
```

#### 4.3.3 逻辑仿函数

函数原型：

- `template<class T> bool logical_and<T>` 逻辑与
- `template<class T> bool logical_or<T>` 逻辑或
- `template<class T> bool logical_not<T>` 逻辑非

把v中数据搬到v2中 并执行取反操作：

```c++
vector<bool>v;
v.push_back(true);
v.push_back(false);
v.push_back(false);
vector<bool>v2;
v2.resize(v.size());//没有这一行就无法transform
transform(v.begin(), v.end(), v2.begin(), logical_not<bool>());
```



- 

## 5 STL-常用算法

算法主要头文件：<algorithm> <functional> <numeric>

<algorithm>：所有STL头文件中最大的一个 范围涉及比较 交换 查找 遍历 复制 修改等

<numeric>：体积很小 只包括几个在序列上进行简单数学运算的模板函数

<functional>：内建仿函数 定义了一些模板类 用以声明函数对象

### 5.1 遍历

#### for_each 遍历

`for_each( iterator beg, iterator end, _func );` func可以是函数或反函数；函数的话只要写个函数名（函数名就是函数地址），仿函数要创建一个对象 一般写匿名对象

```c++
#include <algorithm>
#include <vector>
vector<int>v;
for(int i=0; i<10; i++){
    v.push_back(i);
}

//函数
void Print1(int val){
    cout << val << " ";
}

//仿函数
class Print2{
public:
    void operator()(int val){
        cout << val << " ";
    }
}

for_each(v.begin*(), v.end(), Print1);
for_each(v.begin*(), v.end(), Print2());//匿名对象
```

#### transform 搬运

`transform(iterator beg_source, iterator end_source, iterator beg_dest, _func);` 搬运到另一个容器

```c++
vector<int>v;
for(int i=0; i<10; i++){
    v.push_back(i);
}

void Trans(int v){
    return v;//不做任何处理 直接搬运过去
}

vector<int>v2;
v2.resize(v.size());//搬运前记得开辟目标容器大小！！
transform(v.begin(),v.end(),Trans());
```

### 5.2 查找

#### find 查找

找到返回该元素迭代器，找不到返回end()

`find(iterator bag, iterator end, value);`

查找自定义数据类型：

```c++
#include <vector>
#include <algorithm>

class Person {
public:
	Person(string name, int age) {
		this->m_age = age;
		this->m_name = name;
	}

	//为了使系统知道如何寻找自定义类型 需要重载== ！！！
	bool operator==(const Person& p) {//要加const！！！
		if (p.m_age == this->m_age && p.m_name == this->m_name) {
			return true;
		}
		else {
			return false;
		}
	}

	string m_name;
	int m_age;
};

void test() {
	vector<Person>v;
	Person p1("1", 1);
	Person p2("2", 2);
	Person p3("3", 3);
	Person p4("4", 4);
	v.push_back(p1);
	v.push_back(p2);
	v.push_back(p3);
	v.push_back(p4);

	Person p("2", 2);
	vector<Person>::iterator pos = find(v.begin(), v.end(), p);
	if (pos == v.end()) {
		cout << "not exist" << endl;
	}
	else {
		cout << pos->m_name << " " << pos->m_age << endl;
	}

}
```

#### find_if 按条件查找

`find_if(iterator beg, iterator end, _Pred);` 这里pred必须是函数或者谓词（bool返回类型的仿函数）

```c++
//找>2的Person
class Greater {
public:
	bool operator()(Person& p){
		return p.m_age > 2;
	}
};//仿函数

void test() {
	vector<Person>v;
	Person p1("1", 1);
	Person p2("2", 2);
	Person p3("3", 3);
	Person p4("4", 4);
	v.push_back(p1);
	v.push_back(p2);
	v.push_back(p3);
	v.push_back(p4);

	
	vector<Person>::iterator pos = find_if(v.begin(), v.end(), Greater());//匿名对象
	if (pos == v.end()) {
		cout << "not exist" << endl;
        
	}
	else {
		cout << pos->m_name << " " << pos->m_age << endl;
	}

}
```

#### adjacent_find 查找相邻重复元素

`adjacent_find(iterator beg, iterator end);` 查找相邻重复元素 找到-返回相邻元素的第一个位置的迭代器 没找到-返回end()

#### binary_search 二分查找

`bool binary_search(interator beg, iterator end, val);` 查找指定元素 查到返回TRUE 否则FALSE

- 在**无序序列中不可用** 结果未知

#### count 统计元素个数

`int count(interator beg, iterator end, val);` 统计val出现次数

#### count_if 按条件统计个数

`count_if(iterator beg, iterator end, _Pred);` _Pred谓词

### 5.3 排序

#### sort

`sort(v.begin(),end(),_Pred);` 

```c++
void Print(int val){
    cout << val << " ";
}

//不传入仿函数 默认升序
sort(v.begin(),v.end());
for_each(v.begin(),v.end(),Print);//可以传函数或谓词

#include <functional>
sort(v.begin(),v.end(),greater<int>());
```

#### random_shuffle洗牌

`random_shuffle(iterator beg, iterator end)` 将指定范围内元素进行随机调整次序

```c++
#include <ctime>

srand((unsigned int)time(NULL));//不接这一行 则每次洗牌出来结果一样
random_shuffle(v.begin(), v.end());
```

#### merge合并

`merge(iterator beg1, iterator end1, iterator beg2, iterator end2, iterator dst);` 两个容器合并 存储到第三个容器；dst是目标容器的开始迭代器

- 两个容器必须有序，合并后仍有序

- 注意给目标容器开辟空间！！！

- ```c++
  vector<int>vTarget;
  vTarget.resize(v1.size() + v2.size());
  ```

#### reverse反转

`reverse(iterator beg, iterator end);`

### 5.4 拷贝和替换

#### copy

`copy(iterator beg, iterator end, iterator dest);` 按值查找元素 找到返回指定位置迭代器 找不到返回end()

#### replace

`replace(iterator beg, iterator end, oldVal, newVal);` 将区间内旧元素替换成新元素

#### replace_if

`replace(iterator beg, iterator end, _Pred, newVal);` 将区间内满足条件的替换成新元素

```c++
#include <algorithm>

class ReplaceGreater30{
public:
    void operator()(int val){
        return val > 30;
    }
}

replace_if(v.begin(), v.end(), ReplaceGreater30(), 0);
```

#### swap

`swap(container c1, container c2);` 互换c1 c2容器中的元素

### 5.5 算术生成算法

头文件 `#include <numeric>`

#### accumulate总和

`int res = accumulate(iterator beg, iterator end, startVal);` startVal起始值

#### fill添加元素

` fill(iterator beg, iterator end, val);` 向容器中填充元素 val为填充的值

### 5.6 集合算法

#### set_intersection求交集

` set_intersection(iterator beg1, iterator end1, iterator beg2, iterator end2, iterator dst);`

- 两容器必须是有序的

```c++
#include <vector>
#include <algorithm>

void Print(int val) {
    cout << val << " ";
}

void test() {
    vector<int>v1;
    vector<int>v2;

    for (int i = 0; i < 10; i++) {
        v1.push_back(i);
        v2.push_back(i + 5);
    }

    vector<int>vTar;
    vTar.resize(min(v1.size(), v2.size()));//开辟v1 v2中较小的那个的大小
    vector<int>::iterator itEnd = set_intersection(v1.begin(), v1.end(), v2.begin(), v2.end(), vTar.begin());
    //返回值迭代器指向所求交集的最后一个元素的下一个位置

    for_each(vTar.begin(), vTar.end(), Print);//输出所有元素 后面有0 0 0...
    cout << endl;
    for_each(vTar.begin(), itEnd, Print);//输出交集元素
    //区分上述两种迭代器的结果！！！

}
```

#### set_union求并集

` set_union(iterator beg1, iterator end1, iterator beg2, iterator end2, iterator dst);`

- 两容器必须是有序的

```c++
	vector<int>vTar;
    vTar.resize(v1.size(),+v2.size());//开辟v1 v2的和
	vector<int>::iterator itEnd = set_union(v1.begin(), v1.end(), v2.begin(), v2.end(), vTar.begin());
    //返回值迭代器指向所求交集的最后一个元素的下一个位置
```

#### set_difference求差集

` set_difference(iterator beg1, iterator end1, iterator beg2, iterator end2, iterator dst);`

- 两容器必须是有序的

```c++
	vector<int>vTar;
    vTar.resize(max(v1.size(), v2.size()));//A-B!=B-A 开辟v1 v2中较大的那个的大小
    vector<int>::iterator itEnd = set_intersection(v1.begin(), v1.end(), v2.begin(), v2.end(), vTar.begin());
    //返回值迭代器指向所求交集的最后一个元素的下一个位置
```

# 文件操作

头文件：`<fstream>`

操作文件的三大类：

1. ofstream：写操作
2. ifstream：读操作
3. fstream：读写操作

### 5.1 文本文件

#### 5.1.1 写文件

写文件步骤：

1. 包含头文件：`#include <fstream>`
2. 创建流对象：`ofstream ofs; %前面的类名取决于是要读还是写还是读写文件`
3. 打开文件：`ofs.open("文件路径", 打开方式)`
4. 写数据：`ofs << "写入的数据"`
5. 关闭文件：`ofs.close()`

文件打开方式：

| 打开方式    | 说明                        |
| ----------- | --------------------------- |
| ios::in     | 为读文件而打开文件          |
| ios::out    | 为写文件而打开文件          |
| ios::ate    | 初始位置：文件末尾          |
| ios::app    | 追加的方式写文件            |
| ios::trunc  | 若文件存在，则先删除 再创建 |
| ios::binary | 二进制方式                  |

- 文件打开方式可以配合使用，用 `|` 操作符；如：用二进制方式写文件 `ios::binary | ios::out`

```c++
#incude <iostream>
using namespace std;
#include <fstream>

void test(){
    ofstream ofs;
    ofs.open("text.txt", ios::out);
    ofs<<"hello"<<endl;
    ofs.close();
}
```



#### 5.1.2 读文件

步骤：

1. 包含头文件：`#include <fstream>`
2. 创建流对象：`ifstream ifs;`
3. 打开文件并判断文件是否打开成功：`ifs.open("文件路径", 打开方式);`
4. 读数据：四种方式
5. 关闭文件：`ifs.close();`

```c++
#include <iostream>
using namespace std;
#include <fstream>

void test(){
    ifstream ifs;
    
    ifs.open("test.txt", ios::in);
    
    //判断是否打开成功
    if( !ifs.is_open() ){
        cout << "fail to open!" << endl;
        return;
    }
    
    //read the file -- NO.1
    char buf[1024] = {0};
    while( ifs >> buf ){
        cout << buf << endl;
    }
    
    //read the file -- NO.2
    char buf[1024] = {0};
    while( ifs.getline(buf, sizeof(buf)) ){
        cout << buf << endl;
    }
    //ifstream类中有一个函数getline 得到一行 参数是文件读入目的地buf 读入字节数
    
    //read the file -- NO.3
    string buf;
    while(getline(ifs,buf)){//getline函数 参数：文件流 目的地
        cout << buf << endl;
    }
    
    //read the file -- NO.4
    char c;
    while((c=ifs.get()) != EOF){
        cout << c;
    }
    
    ifs.close();
}
```

### 5.2 二进制文件

打开方式要指定为 `ios::binary`

#### 5.2.1 写文件

二进制写文件 主要利用流对象成员函数 write `ostream& write(const char * buf, int len);` buf 指向内存中一段存储空间 len 是读写的字节数

```c++
#include <iostream>
using namespace std;
#include <fstream>//对文件操作 就必须有这个头文件
#include <string>

class Person{
public:
    char m_name[64];
    int m_age;
};

void test(){
    //1. 包含头文件
    
    //2. 创建输出流对象并打开文件
    ofstream ofs("Person.txt", ios::out | ios:binary);
    //或：
    //ofstream ofs; ofs.open(...)
    
    Person p = {"zhangsan", 18};
    
    //3. 写文件
    ofs.write((const char *)&p , sizeof(p));
    //取p的地址 强制转换成const char * 类型
    
    //4. 关闭文件
    ofs.close();
    
}
```

#### 5.2.2 读文件

`istream& read(char * buf, int len);`

```c++
#include <iostream>
using namespace std;
#include <fstream>

class Person{
public:
    char m_name[64];
    int m_age;
};

void test(){
    //1. 包含头文件

    //2. 创建流对象
    ifstream ifs;

    //3. 打开并判断是否打开成功
    ifs.open("Person.txt", ios::in | ios::binary);

    if(!ifs.is_open()){
        cout << "fail to open !" << endl;
    }

    //4. 读文件
    Person p;
    ifs.read((char*)&p, sizeof(Person));

    //5. 关闭文件
    ifs.close();

}

int main(){
    test();
    system("pause");
    return 0;
}
```

