# 目录



[TOC]



# 题型

- 10% choice // 读结果，可能编译不通过
- true or false 10%
- short answer 30% // 简答题，基本概念
- completion 50% // 填空题，读程序，填空



# CH1		Introduction to Java



## 考点

- Features of Java
- A typical Java development environment
  - edit，compile，load，verify，execute

- **Java programs normally undergo five phases: Edit, Compile, Load, Verify, Execute**
- To test-drive a Java application using the command   line



## Java的特征

- **(1) 简单  simple**
- **(2) 面向对象  object-oriented**
- **(3) 分布式的 distributed**
- **(4) 解析性的**
- **(5) 强壮的 robust**
  - 编译时错误检查/不支持内存指针/自动垃圾回收机制
  - no memory pointers
  - no preprocessor
- **(6) 安全的 secure**
- **(7) 体系结构中立 architecture neutral**
  - Java源代码的默认编译结果并不是可执行代码（本地机器指令），而是具有平台通用性的字节码；实现了“一次编译，处处运行”，并且只能在java虚拟机中运行，与物理宿主环境隔离。
- **(8) 轻便的 portable**
- **(9) 高性能 good performance **
- **(10) 多线程的 threading**
- **(11) 动态语言**
  - 程序在运行时可以改变其结构：新的函数可以被引进，已有的函数可以被删除等在结构上的变化。



## 典型的Java开发环境

- Java程序通常经历五个阶段:

### (1) 编辑edit：用编辑器程序编辑文件

 ![](\asset\图片1.png)

### (2) 编译compile：将Java程序编译为字节码

 ![](\asset\图片2.png)

### (3) 加载load：将程序加载到内存中

- 程序在启动的时候，并不会一次性加载程序所要用的所有class文件，而是根据程序的需要，通过**Java的类加载器（Class Loader）**来动态加载某个class文件（加载类的字节码）到内存当中的。
  
- class文件只有被载入到了内存之后，才能被其它class所引用。
  
  - class加载到内存指的是**加载到JVM内存中**，且class文件中的方法存储在JVM的方法区。
  
-  然后在内存上创建一个`java.lang.Class`对象用来封装类在方法区内的数据结构**作为这个类的各种数据的访问入口**。
  - java.lang包是默认在JVM内部加载的。
  
    ![](\asset\图片3.png)

### (4) 验证verify：验证字节码

- 主要是为了确保class文件中的**字节流包含的信息是否符合当前JVM的要求**，且不会危害JVM自身安全，比如校验文件格式、字节码验证等等。
- 当加载类后，JVM使用一个称为字节码验证器（**bytecode verifier**）的程序来检验字节码的合法性。

 ![](\asset\图片4.png)

### (5) 执行excute

 ![](\asset\图片5.png)



## 使用命令行测试Java应用程序

- test-drive a Java application using the command line
  - 编译Java时，用javac xxx.java命令
  - 编译目录下全部文件javac*.java命令
  - 运行Java时，用java xxx.class命令（.class扩展名可以省略）

 ![](asset\图片6.png)

![](asset\图片7.png)

## Java SDK的三种版本

- J2SE
  - ***Java SDK*** ***Standard*** **Edition（标准版)**
  - contains the basic core Java classes：包含了***基本的核心的Java类。***

- J2ME
  - for developers that code to **portable devices**, such as a **palm pilot**（掌上电脑） or a cellular phone。
  - 便携式设备编码开发

- J2EE
  - Java SDK **Enterprise** Edition **(企业版）**
  - contains classes that go above and beyond J2SE：包含超越了J2SE的类




## 补充：JVM

### 概念

- Java Virtual Machine（Java 虚拟机）是整个 java实现跨平台的最核心的部分。
- 虚拟机是一种抽象化的计算机，**通过在实际的计算机上仿真模拟各种计算机功能来实现的**。Java虚拟机有自己完善的硬体架构，如处理器、堆栈、寄存器等，还具有相应的指令系统。Java虚拟机**屏蔽了与具体操作系统平台相关的信息**，使得Java程序只需生成在Java虚拟机上运行的目标代码（字节码），就可以**在多种平台上不加修改地运行**。

### 细节

- java.lang包默认是由JVM在内部加载的



# CH2		Introduction to Java Applications



## 考点

- To Know some **convention（规则）** and **syntax（语法）**.
- To write simple Java applications.
  - main method(main函数头)
    - **public static void main(String[] args)**
- To use input and output statements.
  - **Class Scanner ( nextInt … )**
    - 导入：import java.util.Scanner (Import class Scanner from **java.util**)
    - 声明
    - 创建并初始化
    - 调用方法
  - **OutputStream (System.out.println(…))**
    - java IO中所有输出流的父类
    - system.out.print() 输出一个字符
    - system.out.println() 输出一行字符并自动换行
    - system.out.printf() 格式化输出
- Java’s **primitive types.**（八大基本数据类型，选择题）
  - boolean, byte, char, short, int, long, float, double
  - boolean(2B) 只能是 True 或False。
    - **A boolean can only be assigned the literal true or false**

- Identifier(字母数字下划线，开头不能是数字)
  - a series of characters consisting of letters, digits, underscores (_) and dollar signs ($) that does not begin with a digit and does not contain spaces. 
- Basic memory concepts.
- Garbage collection（垃圾回收）



## main method

public static void main( String[] args )



## 输入和输出语句

- Class Scanner ( nextInt … )

  - ① 数据可以来自许多来源，例如键盘上的用户或磁盘上的文件。
  - ② 标准输入对象，System.in，使应用程序能够读取用户键入的信息字节。

- OutputStream (System.out.println(…))

  - ##### ① 指示用户采取特定行动的输出语句。
  - ② 类System：java.lang包的一部分，**不在程序开始时随导入声明一起导入**。




## 输入流

- 三种Standard Streams输入输出流 + System.console()

- Standard Streams

  - Standard Input标准输入——accessed through **System.in**; 

    - System.in is a byte stream 字节流

      - To use Standard Input as a **character stream**, wrap System.in in **InputStreamReader.** 

        ```java
        InputStreamReader cin = new InputStreamReader(System.in);
        ```

  - Standard Output标准输出——accessed through **System.out**; 

  - Standard Error标准—— accessed through **System.err**.

- The Console控制台

  - The Console is particularly useful for secure password entry. 
  - The Console object also provides input and output streams that are true **character streams**（字符串,） through its **reader and writer methods**（读和写方法）. 
  - Before a program can use the Console, it must attempt to **retrieve**（检索）the Console object by **invoking**（调用） System.console().

```java
Console console = System.console();   
    if (console != null){   
        String user = new String(console.readLine("Enter User:",new Object[0]));   
        String pwd = new String(console.readPassword("Enter Password:",new Object[0])); 
        console.printf("User name is:%s", new Object[] { user });   
        console.printf("Password is:%s", new Object[] { pwd });   
     } 
    else{   
        System.out.println("No Console!");   
    }   
```



## Java的基本类型

- boolean, **byte**, char, short, int, long, float, double



## 标识符

- 由字母，数字，下划线(_)和**美元符号($)**组成的一系列字符，不以数字开头，不包含空格。

  

## 运算符

- **arithmetic operators** 算术运算符：  + - * / %
- **decision-making statements** 判断语句：   If
- **relational and equality operators** 比较运算符：==  !=  >  >=  <  <=



## 关键词

<img src="C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220616204538586.png" alt="image-20220616204538586" style="zoom:80%;" />



## 基本内存概念

### 垃圾收集

-  程序员无权回收内存。当一个对象**没有被任何引用类型的变量引用**时，该对象的内存可以被垃圾回收器自动回收； 
-  垃圾回收器**以独立的线程异步执行**，回收的时间是程序无法预计的； 
-  程序员可以调用**System.gc()**或者**Runtime.gc()**，提示垃圾回收器进行垃圾回收； 
-  垃圾回收器进行垃圾回收时，**调用对象的方法finalize 析构方法**。

- 考点：

  - 1 注意条件，**没有被任何引用类型的变量引用时**

  - 2 程序员**无权强制回收**，**只能建议**。

  - 3 对象**没有被回收的话就不会调用finalize方法**

    <img src="C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220615155208165.png" alt="image-20220615155208165" style="zoom:80%;" />



### 变量：名称、类型、大小（字节）和值



# CH3	 Introduction to Classes, Objects Methods and Strings



## 考点

- **class and object**
- **instance variables and local variables**
- **primitive and reference types**
- Member access modifiers



## 类和对象



## 实例变量和局部变量

- 实例变量：声明在类声明中，但定义在类方法的主体之外

  - ①  初始化不一样
    - 创建类的对象时，其实例变量默认初始化。
  - ②  位置不一样
    - 我们更喜欢在类的主体中首先列出类的实例变量，以便在将变量用于类的方法之前看到它们的名称和类型。可以在类的方法声明之外的任何地方列出该类的实例变量，但是分散实例变量可能会导致难读的代码。

- 局部变量：参数

  
  
- 类的实例变量和方法的局部变量 比较

  - Instance variable 如果不初始话将会有默认值0或null，
  - local variable 使用前必须初始化，否则报错
  
  

## 基本类型和引用类型

-  基本类型：boolean, **byte,** char, short, int, long, float, double
-  引用类型：所有非基本类型，默认值为**null**
  - ① 程序使用引用类型的变量来**存储对象在计算机内存中的位置**，称为**引用一个对象**。
  - ② 当使用另一个类的对象时，需要**对该对象的引用**来调用其方法，也称为**向对象发送消息**。
-  区别
   -  原始类型是数据的基本类型： byte、short、int、long、float、double、boolean、char。 原始变量存储原始值。
   -   引用类型是**任何可实例化的类以及数组**：String、Scanner、Random、 Die、int[]、String[] 等
   -  **primitive variable是在栈中**创建，一旦超出作用域将被销毁
   -  **reference variable是在堆中**创建 ，由garbage collection 管理	




## 成员访问修饰符

- public：类与子类的客户可以访问变量、方法
- private：
  - ① 类的**客户**无法访问变量、方法
  - ② 只有在超类的方法中才能访问
  - ③ 声明**实例变量**为**私有**的称为**数据隐藏**
- protected：超类的方法、子类的方法和同一包中其他类的方法可以访问
- default：
  - ① 控制对类变量和方法的访问
  - **② 未声明为公共、受保护或私有的成员有默认访问权限，可以从声明的包中的任何地方访问并且只能从那里访问。**



# CH4		Control Statements: Part I



## 考点

- if and if else selection statements 
- ? :
- While repetition statement 
- = += -= …
- ++   --
- Graphics, JPanel, paintComponet



## if 和 if else 选择语句

-  if：单选
-  if…else：双选
- switch：多选语句



## ? :	三元运算符

- 缩短if…else语句



## while 循环语句

- while、for语句：0+次
- do…while语句：1+次



## = 、+= 、-= …

 ![](\asset\图片8.png)



## ++ 、--

 ![](\asset\图片9.png)





# CH5		Control Statements: Part 2



## 考点

- for, switch, do…while 
- break and continue 
- logical operators 
  - &  &&  |  || !  ^




## for, switch, do…while 

### **for**

- 需要for标头中的两个;，但三个表达式都是可选的。

- for ( initialization; loopContinuationCondition; increment ) statement

- 例：返回数组中偶数出现的次数

  - ```java
    public class test {
    	public static void main(String args[]) {
    		int[] A= {6,3,4,5,2,7,3};
    		System.out.println(numInArray(A));
    	}	
    	public static int numInArray(int[] A) {
    		int count=0;
    		for(int n:A) {
    			if(n%2==0) count++;
    		}
    		return count;
    	}
    }
    ```

### while

- initialization; while ( loopContinuationCondition ) { statement increment; }

- do…while

  - initialization; do{ statement increment; }while ( loopContinuationCondition ) ;


### switch

​	① 表达式可为：**byte**, short, int,char, String,enum

​	② 如果和表达式大小写不匹配：执行default

​	③ 如果没有匹配发生并且没有默认情况，则程序控制只会继续执行切换后的第一条语句。

```java
switch(day){
        case MONDAY,FRIDAY,SUNDAY -> System.out.println(6);
        case TUESDAY -> System.out.println(7);
        case THURSDAY,SATURDAY -> System.out.println(8);
        case WEDNESDAY -> System.out.println(6);
}
```

```java
static void howMany(int k){
	System.out.println(
		switch(k){
			case 1 -> "one"
			case 2 -> "two"
			default -> "many"
		}
	);
}
```



- break语句：在while，for，do...while或switch中会导致立即退出该语句。

- continue语句：当在while，for或do...while中时，跳过循环体中的其余语句，并继续循环的下一次迭代。



## logical operators ：&  &&  |  || !  ^

-  && (conditional AND)

- **|| (conditional OR)**

  & (boolean logical AND)

- **| (boolean logical inclusive OR)**

- ^ (boolean logical exclusive OR) 

-  ! (logical NOT)



# CH6		Methods: a deeper look



## 考点

- **static** methods and fields 
- Argument Promotion and Casting
- **final**
- Class **Math.**
- passing information between methods.（都是值传递）
- packages import
- jdk packages
- **random-number** generation
- SecureRandom 
- method **overloading**



## 静态方法和字段

- 静态变量：
  - Java中被 static 修饰的成员称为静态成员或类成员。
    - ① 它属于整个类所有，而不是某个对象所有，**即被类的所有对象所共享**。
    - ② 静态成员**可以使用类名直接访问**，也可以使用对象名进行访问。
    - ③ 静态成员属于整个类，当系统第一次使用该类时，**就会为其分配内存空间直到该类被卸载才会进行资源回收**。
-  静态方法：
  - ① 可以直接调用同类中的静态成员，**但不能直接调用非静态成员**。
  - ② 如果希望在静态方法中调用非静态变量，可以通过创建类的对象，然后通过对象来访问非静态变量。
  - ③ **在普通成员方法中，则可以直接访问同类的非静态变量和静态变量。**
  - 继承的时候，static method不能重写，因为默认是final



## 数据类型转换

 ![](\asset\图片10.png)



## final

- **常量：**必须在使用之前初始化，以后不能修改。
  - (1) 初始化后修改最终变量的尝试会导致编译错误
  - (2) 尝试在最终变量初始化之前访问其值会导致编译错误
- **方法：**子类中不能重写
- **类：**不能被继承



## final、finalize、finally的区别

- **final：**表示常量
- **finally：**用于异常处理里面最后必要执行到的程序段
- **finalize：**析构函数，垃圾回收时也会调用。



## Math类

- (1) 由于Math类在java.lang包下，所以不需要导包。
- (2) **因为它的成员全部是static,所以私有了构造方法。**

 <img src="\asset\图片11.png" style="zoom:33%;" /><img src="\asset\图片12.png" style="zoom:33%;" />



## 在方法之间传递信息：值传递

- 对于原始数据类型，也就是int、 long、char之类的类型，是传值的

  - 如果你在方法中修改了值，方法调用结束后，那个变量的值没用改变。 

- 对于对象类型，也就是Object的子类，如果你在方法中修改了它的成员的值，那个修改是生效的。

  - 方法调用结束后，它的成员是新的值，但是如果你把它指向一个其它的对象，方法调用结束后，原来对它的引用并没用指向新的对象。

  

## 包导入

- 相关类通常被分组到包中，以便可以将它们导入程序并重用。

- java中有两种包的导入机制：

  - **单类型导入**(single-type-import)例如 import java.io.File; 
    - 仅仅导入一个public类或者接口。

  - **按需类型导入**(type-import-on-demand)，例如 import java.io.*;
    - 有人误解为导入一个包下的所有类，其实不然，他只会按需导入，也就是说它并非导入整个包，而仅仅导入当前类需要使用的类。

- java.lang；是自动import的

  

###  jdk包

 <img src="\asset\图片13.png" style="zoom:33%;" /><img src="\asset\图片14.png" style="zoom: 33%;" />

## 随机数生成

- Random产生可由恶意程序员预测的确定性值。

  - 都是**左闭右开**

  - ```java
    import java.util.Random;
    
    public class test {
    	public static void main(String args[]) {
    		Random r = new Random(); //创建Random对象
            //更改nextInt产生的值范围[1,6）
    		System.out.print(r.nextInt(6) + 1);
    	}
    }
    ```

  - Math.random()

  - ```java
    //Math.random()产生一个[0.0,1.0)的随机数
    //即[1.0,7.0)
    Math.random()*6 + 1;
    ```

-  SecureRandom产生无法预测的非确定性随机数。

  - ```java
    //创建SecureRandom对象
    SecureRandom randomNumbers = new SecureRandom();
    //获取随机int值
    int randomValue = randomNumbers.nextInt();
    //更改nextInt产生的值范围（0,1）
    int randomValue = randomNumbers.nextInt(2);
    
    ```

  

## 方法重载⭐

-  在同一类中声明的同名方法。必须有不同的参数集。
- 编译时静态执行。编译器通过检查调用中**参数的数量、类型和顺序**来选择要调用的适当方法。
- 用于创建具有相同名称的多个方法，这些方法执行相同或相似的任务，但对**不同类型或不同数量的参数**执行。



## 方法重载/重写

- 方法重载：相同方法名，但是参数列表不一样。
  - 可以有不同的返回类型，但如果两个方法具有相同的方法名，不同返回类型，会导致语法错误。
- 方法覆写：在继承关系中体现。
  - 子类覆写父类中的方法 方法名及参数名都一样，只是方法体不一样。而且子类的方法访问权限只能比父类的小



# Ch7		Arrays and ArrayLists



## 考点

- To **declare** an array, initialize an array and refer to individual elements of an array.
- **enhanced for** statement (增强的for循环)
- To pass arrays to methods.
- multidimensional arrays.
- **variable-length** argument lists.（可变长度的参数列表，参数列表最后，只出现一次）



## 声明数组

- 若要声明数组，请初始化数组并引用数组的各个元素

- ```java
  int[] c = new int[ 12 ];                    
  int[] c; // declare the array variable 
  int[] n = { 10, 20, 30, 40, 50 };   // creates the array
  ```



## 增强 for 语句 

- for ( parameter : arrayName )  statement



## 将数组传递给方法

- (1) 指定没有任何括号的数组名称。
- (2) 由于每个数组对象“知道”自己的长度，我们不需要将数组长度作为额外的参数传递新界。
- (3) 要接收数组，方法的参数列表必须指定数组参数。
  - ① 当参数=整个数组 || 引用类型的单个数组元素，接收引用副本。
  - ② 当参数=基本类型的单个数组元素，接收值副本。



## 多维数组

- 有两个以上的维度，Java不直接支持

- ```java
  int[][] b = { { 1, 2 }, { 3, 4 } };
  int[][] b = { { 1, 2 }, { 3, 4, 5 } }; 
  int[][] b = new int[ 3 ][ 4 ];                               
  int[][] b = new int[ 2 ][ ];  // create 2 rows 创建
  b[ 0 ] = new int[ 5 ]; // create 5 columns for row 0 初始化
  b[ 1 ] = new int[ 3 ]; // create 3 columns for row 1 
  ```

  

## 可变长度参数列表⭐

- 创建接收未指定数量的参数的方法

  - (1) 参数类型后面跟着省略号（...）表示该方法接收到该类型的可变数量的参数

  - (2) 只能在参数列表的末尾发生一次。

  - ```java
    //calculate average
    public static double average(double… numbers){
        double total=0.0;//initialize total
        //calculate total using the enhanced for statement
        for(double d:numbers){
            total+=d;
        }
        return total / numbers.length;
    }//end method average
    ```




## **Using Command-Line Arguments**命令行参数

- args[0]代表参数的长度

  

## 例：x[i]\[j]→x[j]\[i]

```java
static void transpose(double[][] x){
    //transpose the array x swapping each x[i][j] and x[j][i]
    double temp;
    for(int i=0;i<x.length;i++){//i行
        for(int j=i;j<x[i].length;j++){//j列
            temp=x[i][j];
            x[i][j]=x[j][i];
            x[j][i]=temp;
        }
    }
}
```



## Array和Arrays

### 1．数组类Array

     Java中最基本的一个存储结构。
    
     提供了动态创建和访问 Java 数组的方法。其中的元素的类型必须相同。
    
     效率高，但容量固定且无法动态改变。
    
     它无法判断其中实际存有多少元素，length只是告诉我们array的容量。

### 2、静态类Arrays


    此静态类专门用来操作array ，提供搜索、排序、复制等静态方法。
    
    不能动态调整大小
    
    equals()：比较两个array是否相等。array拥有相同元素个数，且所有对应元素两两相等。
    
    sort()：用来对array进行排序。
    
    binarySearch()：在排好序的array中寻找元素。
    
    Arrays.asList(array):将数组array转化为List


# Ch8		Classes and Objects: A Deeper Look



## 考点

- Encapsulation and data hiding.
- **this**
- **Composition**
- String.format
- Garbage Collection
- Static import
- **enum** 



## 封装与数据隐藏

- (1) 封装：将抽象性函数式接口的实现细节部份包装、隐藏起来。要访问该类的代码和数据，必须通过严格的接口控制。
- (2) 封装最主要的功能在于我们能修改自己的实现代码，而不用修改那些调用我们代码的程序片段。
- (3) 适当的封装可以让程式码更容易理解与维护，也加强了程式码的安全性。



## this：对象访问对自身的引用

- (1) 非静态方法：隐式地使用关键字this
  - ① 使类的代码知道应该操作哪个对象。
  - ② 也可以在非静态方法的主体中显式使用关键字this。
- (2) 外部类名.this表示内部类
- (3) 不能和static放在一块



## 构造函数调用this

- 同一个类中调用构造函数 [必须在构造函数中第一行] 
  - this(参数)
- 子类构造函数调用基类构造函数 [必须在构造函数中第一行]  
  - super(参数)
-  //不能**在非构造函数使用this调用构造函数**
- Constructor call must **be the first statement** **in a constructor**

```java
class Person {
 	private String name;
 	private int age;
 	private boolean sex;
 	public Person() {
        // 必须在第一行 不能同时调两个不同的构造函数
 		this("jadeshu", 22, true); 
 	}
 	public Person(String name,int age, boolean sex) {
 		this.name = name;
 		this.age = age;
 		this.sex = sex;
 	}
}
```





## 组合：将对其他类的对象的引用作为成员。⭐

### has a和is a

- "Is a”代表**类之间 或 类与接口 或类与对象的关系**
  - 比如猫是动物，狗也是动物，都继承了动物的共同特性，再用OO语言实现时，应将猫和狗定义成两种类，均继承动物类。
  - This *operator has a form* of **object *instanceof Type*** and returns true *if* the **object satisfies *IS-A relationship* with the *Type***
- "has a"代表的是**对象和他成员的从属关系**。
  - 同一种类的对象，通过它们的属性的不同值来区别。
  - 比如张三和李四都是人，但他们的名字不一样，可以以此区分这两个具体的人。名字应该作为人的成员变量。如果将名字叫“张三”的人和名字叫“李四”的人分别定义成两个类，均继承“人”这个类，显然是不合理的。



## String.format⭐

```java
return String.format("%02d:%02d:%02d",this.hour,this.minute,this.second);
```



## String: compareTo()

- 如果第一个字符和参数的第一个字符不等，结束比较，返回第一个字符的ASCII码差值。

```java
"a".compareTo("c");//返回-2
```

- 如果第一个字符和参数的第一个字符相等，则以第二个字符和参数的第二个字符做比较，以此类推,直至不等为止，返回该字符的ASCII码差值。
-  如果两个字符串不一样长，可对应字符又完全一样，则返回两个字符串的长度差值



## JAVA的装箱机制

```java
a="abc";
System.out.println(a.length()+a);//3abc,这里数字被当成了字符串
```



## 垃圾回收：回收那些不再使用的内存

- (1) Object(包java.lang)的finalize方法
  - 很少使用，因为会导致性能问题，且在是否会被调用方面存在一些不确定性。
- (2) **防止内存泄露**，但可能还是会发生其他类型的资源泄漏。
  - 应用程序可以打开磁盘上的文件来修改其内容。如果它没有关闭文件，则应用程序必须在任何其他应用程序可以使用它之前终止。
- (3) **JRE**在后台自动进行的，通常会提供一个**后台线程**来进行检测和控制。
- (4) 一般都是在**CPU空闲或内存不足**时进行垃圾回收，而程序员无法精确控制垃圾回收的时间和顺序等。



## 静态导入：导入类或接口的静态成员，不准使用类名.静态成员调用

```java
import static packageName.ClassName.staticMemberName;//导入特定静态成员（单个静态导入）
import static packageName.ClassName.*;//导入类的所有静态成员（按需静态导入）
```



 



## Enum：定义一组表示为唯一标识符的常量。

- **(1) 引用类型**
- **(2) 隐式final，因为它们声明不应修改**
- **(3) 隐式static**
- (4) 枚举声明包含枚举常量和枚举类型的其他成员两部分，是一个分隔的枚举常量列表
- (5) 枚举构造函数可以指定任意数量的参数，并且可以重载。
- (6) 对于每个枚举，编译器生成返回枚举常量数组的静态方法值。
- **(7) 当枚举常量转换为字符串时，该常量的标识符将用作字符串表示形式。**



### 书上的例子

- 枚举跟普通类一样可以用自己的变量、方法和构造函数，构造函数只能使用 private 访问修饰符，所以外部无法调用。枚举既可以包含具体方法，也可以包含抽象方法。 如果枚举类具有抽象方法，则枚举类的每个实例都必须实现它。
- 枚举常量表——由枚举常量构成。"枚举常量"或称"枚举成员"，是以标识符形式表示的整型量，表示枚举类型的取值。枚举常量表列出枚举类型的所有取值，各枚举常量之间以"，"间隔，且必须各不相同。取值类型与条件表达式相同。

<img src="C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220617131044956.png" alt="image-20220617131044956" style="zoom: 67%;" />



# Ch9		Object-Oriented Programming: Inheritance



## 考点

- The notions of superclasses and subclasses.
  - 构造函数不能被继承，显示调用或者隐式调用

- **super**
- How **constructors** are used in inheritance hierarchies.
- Class **Object**



## 超类、子类的概念

- (1) 超类 ：被继承的类

- (2) 子类：继承的类

- (3) 由于每个子类对象都是其超类的一个对象，并且一个超类可以有许多子类，因此由一个超类表示的对象集通常大于由其任何子类表示的对象集。

  

##  super

- 能够用来访问超类的构造方法和**被子类所隐藏的方法**。
- 假设子类中有方法与超类中的方法名称和参数同样，则超类中的方法就被隐藏起来，也就是说在子类中重载。



## 在继承层次结构中使用构造函数

- (1) 每个类在构造的时候，都会有一个默认的无参构造方法；就算我们不写，编译器也会帮助我们生成一个默认的构造方法；但是如果我们写了，那么会使用我们自己写的构造方法。
- (2) **子类的构造过程中，必定会调用其父类的构造方法**，就算我们没有写，编译器也会隐式的帮助我们使用super()来调用父类的无参构造方法。
- (3) 所以在这里进行子类的new时，会先打印父类的无参构造的方法，再调用子类本类的无参构造。



## Object类

- (1) 理论上Object类是所有类的父类，即直接或间接的继承java.lang.Object类。由于所有的类都继承在Object类，因此省略了extends Object关键字。
- (2) Object类中的**getClass(),notify(),notifyAll(),wait()**等方法被定义为**final**类型，因此**不能重写**。
  - getClass()：一般和**getName()**联合使用，如**getClass().getName()**;
  - 重写equal比较内容
  - **notify():** 随机唤醒对象的等待池中的一个线程，进入锁池
  - **notifyAll():** 唤醒对象的等待池中的所有线程，进入锁池
  - **wait():** 等待，需要唤醒再继续执行
- (3) toString()方法：可重写
  - 如果在实际使用中为特定对象提供一个特定的输出模式，当这个类型转换为字符串或字符串连接时，建自动调用重写的toString()方法。

<img src="C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220616220413412.png" alt="image-20220616220413412" style="zoom:67%;" />



## wait和sleep方法的区别

- 使用方面
  - 从使用的角度来看sleep方法是Thread线程类的方法，而wait是Object顶级类的方法。
  - sleep可以在任何地方使用，而**wait只能在同步方法和同步块中使用**。
- CPU及锁资源释放:
  - sleep、wait调用后都会暂停当前线程并让出CPU的执行时间。
  - 但不同的是sleep不会释放当前持有对象的锁资源，到时间后会继续执行。
  - 而wait会释放所有的锁并需要notify/notifyAll后重新获取到对象资源后才能继续执行。
- 异常捕获方面：
  - sleep需要捕获或者抛出异常，而wait/notify/notifyAll则不需要。



## Constructor和method的区别

- **构造函数**用于创建对象，与类同名，没有返回值，由new触发。
- **方法**是类中的普通成员，有自己的名字，有返回值（可能是void），由.触发。



# Ch10	Object-Oriented Programming: Polymorphism



## 考点

- The **concept** of polymorphism and how to use it .
- **Overriding** 
- **abstract class, interface and concrete classes.**
- To determine an object's type at execution time.
  - **instanceof**




##  多态性的概念及如何使用

- (1) 概念
  - 为普适编程而非为个类编程。允许不同类的对象对同一消息做出响应。即同一消息可以根据发送对象的不同而采用多种不同的行为方式**(发送消息就是函数调用)**
- (2) 能够编写处理共享相同超类的对象的程序，就像它们是超类的所有对象一样；这可以简化编程。
- (3) 关键：依靠每个对象来知道如何响应相同的方法调用“做正确的事情”。
- (4) 作用：消除类型之间的耦合关系
- (5) 好处：
  - **① 可替换性**
    - 多态对已存在的代码具有可替换性。
  - **② 可扩充性**
    - 增加新的子类不影响已存在类的多态性、继承性，以及其他特性的运行和操作。实际上新加子类更容易获得多态功能。
  - **③ 接口性**
    - 超类通过方法签名，向子类提供了一个共同接口，由子类来完善或者覆盖它而实现的。
  - **④ 灵活性**
    - 在应用中体现了灵活多样的操作，提高了使用效率。
  - **⑤ 简化性**
    - 简化对应用软件的代码编写和修改过程，尤其在处理大量对象的运算和操作时，这个特点尤为突出和重要。值得注意的是，**多态并不能够解决提高执行速度的问题**，因为它基于**动态装载和地址引用**，或称**动态绑定**(变量引用的对象的类型决定了要使用的实际方法)。

## 动态绑定

- 变量引用的对象的类型决定了要使用的实际方法

- 在程序运行过程中，根据具体的实例对象才能具体确定是哪个方法。动态绑定是多态性得以实现的重要因素。

  

## Polymorphism如何促进可拓展性

- 程序中用父类的变量指向子类的对象。
- 在子类中重写父类的方法。
- 实际运行的时候运行子类方法。



## Overriding重写

- 父类与子类之间的多态性，对父类的函数进行重新定义。
  - (1) 如果在子类中定义某方法与其父类有相同的名称和参数，我们说该方法被重写，又称**方法覆盖**。
  - (2) 若
  - 子类中的方法与父类中的某一方法具有相同的方法名、返回类型和参数表，则新方法将覆盖原有的方法。如需父类中原有的方法，可使用super引用父类。
  - (3) **子类函数的访问修饰权限不能少于父类的**。（子类应该可以替换任何基类能够出现的地方，并且经过替换以后，代码还能正常工作。）



## 抽象类、接口、具体类



### **(1) 抽象类：必须用abstract修饰**

- ① 如果一个类有**abstract**方法，这个类一定是抽象类
- ② **不一定有抽象方法，可以有自己的变量**
- ③ 如果一个类，继承一个抽象类但没有实现所有的抽象方法，那它也是抽象类
- ④ 用来捕捉子类的通用特性，**不能被实例化，只能被用作子类的超类。**抽象类是被用来创建继承层级里子类的模板。

### (2) 接口：interface

- ① 对于将公共功能分配给**可能不相关的**类特别有用。允许对无关类的对象进行多态处理——实现相同接口的类的对象可以响应所有接口方法调用。
- ② 描述方法**但不提供具体方法**。（9之后有实现的方法）
- ③ 只能有方法和变量，变量为**public static final**类型，方法只能被可以为**public(默认)和protected修饰，方法不能有具体的实现。**

<img src="C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220616220508232.png" alt="image-20220616220508232" style="zoom:50%;" /><img src="C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220617113147171.png" alt="image-20220617113147171" style="zoom: 67%;" />

- ④ 一个类可以实现多个接口，一旦实现接口，必须要实现接口的所有的方法
- ⑤ 特性
  - 接口是**抽象方法的集合**。如果一个类实现了某个接口，那么它就继承了这个接口的抽象方法。这就像契约模式，如果实现了这个接口，那么就必须确保使用这些方法。接口只是一种形式，接口自身不能做任何事情。
  - 接口可以进行实例化，只需要在new后将接口中未实现的方法进行实现就好，这种做法其实是匿名内部类的做法

### (3) 具体类

- 可以用来实例化对象的类称为具体类，提供了它们声明的每个方法的实现。 

### (4) interface和abstract class的区别

- 接口中只能有抽象方法，只能有静态常量。
- 抽象类中可以包含若干非抽象方法，且有静态和非静态的变量和常量。



## instanceof：在执行时确定对象的类型

```java
//determine whether element is a BasePlusCommissionEmployee
if (currentEmployee instanceof BasePlusCommissionEmployee)
```

- 当一个对象的类同时是另一个类的子类、以及另一个接口的实现类
- 也是它们的类型

```java
interface A { void print(); }
class C {} 
class B extends C implements A { 
	public void print() { }
}

public class Test{ 
    public static void main(String[] args) { 
        B b = new B(); 
        if (b instanceof A) System.out.println("b is an instance of A"); 
        if (b instanceof C) System.out.println("b is an instance of C"); } 
}
//输出⭐
//b is an instance of A
//b is an instance of C
```





# Ch11	Exception Handling a deeper look



## 考点

- **try, throw , catch and finally** 

- **Checked exceptions  and unchecked exceptions.** 



## try, throw , catch和 finally

- try：

  - 包含可能引发异常的代码，如果发生异常，则不应执行该异常的代码。
    - ① 至少一个catch块或finally块必须紧跟try块。
    - **② 在相应的catch块中，try块的局部变量不可访问。**

- catch

  - 捕获并处理异常。

    - ① 参数名允许catch块与捕获的异常对象交互。

    - ② 使用System.err（标准错误流）对象输出错误消息。

    - **③ Uncaught exception：没有匹配的catch块**

      - **（1）如果程序只有一个线程，未捕获的异常将导致程序终止。**
      - **（2）如果一个程序有多个线程，未捕获的异常将仅终止发生异常的线程。**

    - **④ 当catch块终止时，在catch块中声明的局部变量（包括异常参数）也会超出范围。**

    - **⑤ 在与特定try块关联的两个不同catch块中捕获完全相同的类型是一个编译错误。**

    - ```java
      catch(Type1|Type2|Type3 e)
      ```

- throw：

  - 表示发生了异常，操作数可以是从类Throwable派生的任何类。

- finally

  - 可有可无
  - 用于资源分配。
    - ① 如果try块通过使用return、break或continue语句退出，或者仅通过到达其右大括号来退出，则将执行finally块。
    - ② 如果应用程序通过调用方法System.exit(1)立即终止，则不会执行finally块。
  



## Checked exceptions和unchecked exceptions

- (1) unchecked exceptions：
  - 类**RuntimeException**(java.lang包)的直接或间接子类
    - ① 通常是由**程序代码中的缺陷**引起的
    - ② 编译器**不检查代码**以确定unchecked exceptions是否被捕获或声明。这些通常可以通过适当的编码来防止。不需要在方法的抛出子句中列出unchecked exceptions。
- (2) checked exceptions
  - **Exception的子类**但非RuntimeException
    - ① 由**不在程序控制范围内**的条件引起
    - ② 编译器**检查**每个方法调用和方法声明，以确定该方法是否抛出checked exceptions。如果是，编译器将验证选中的异常是否被catch或在throws子句中声明。如果未满足catch或declare要求，编译器将发出一条错误消息，指示必须捕获或声明异常。

## 常用的exception



## throw和throws

- throw语句用在**方法体内，表示抛出异常，由方法体内的语句处理**。 
- **throws语句用在方法声明后面，**表示再抛出异常，由该方法的调用者来处理。
- throws主要是声明这个方法会抛出这种类型的异常，使它的调用者知道要捕获这个异常。 throw是具体向外抛异常的动作，所以它是抛出一个异常实例。



# Ch12	JavaFX GUI part 1（基本不考）



## 考点

- Vbox and GridPaneLayout container
- How to write basic JavaFX program
  - .fxml controller main




##  Vbox、GridPane、Layout container

- Vbox
  - 在一列中垂直排列节点。
- GridPane
  - (包javafx.scene.layout)将JavaFX组件排列成矩形网格中的列和行。
    - **① 每个单元格可以是空的，也可以一个至多个组件**
    - **② 每个组件可以跨越多个列或行**
    - ③ 默认创建**两列三行**的网格窗格。
- Layout container
  - 子节点通常是将它们的子节点安排在场景中的Layout container



## 怎么写基本的JavaFX程序

- fxml controller：
  - ① 当**fxmloader**创建控制器类对象时，**fxmloader**确定类是否包含没有参数的initialize方法，如果包含，则调用该方法来初始化控制器。
  - ② FXML GUI的事件处理程序是在所谓的控制器类中定义的
-  main：Application的子类中的方法
  - ① 调用类Application中静态的launch方法来开始执行应用程序。
  - ② 启动方法，JavaFX运行时创建Application子类的对象并调用其start方法。
  - ③ Application**子类的start方法创建GUI**，将其附加到**Scene**中，并将其放置在**Stage**开始接收参数。



# Ch13	JavaFX GUI part 2（基本不考）



## 考点

- additional layouts (**TitledPane, BorderPane and Pane)** 
- Set up event handlers that respond to **property changes** on controls(such as the value of a Slider).



## 额外的布局

- TitledPane：
  - 标题可以打开和关闭的面板。
- BorderPane：
  - 包括五个区域：顶部、底部、左侧、中心和右侧，在那里放置节点。
    - ① 顶部和底部区域填充边界窗格的宽度，垂直大小为子结点的高度。
    - ② 左和右区域填充边界窗格的高度，水平大小为子结点的宽度。
    - ③ 中心区域占据了边界窗格的所有剩余空间。
- Pane
  - ① 布局窗格是容器节点，根据其大小和位置，将其子节点相对排列在场景图中。
  - ② **使用相对定位。**如果调整了布局窗格节点的大小，它会根据其**首选、最小和最大大小**相应地调整其子节点的大小和位置。



## 建立event handlers 来对控制的property变化做出响应

```java
//listener for changes to tipPercentageSlider's value
tipPercentageSlider.valueProperty().addListener(
	new ChangeListener<Number>(){
        @Override
        public void changed(ObservableValue<? extends Number>ov,Number oldValue,Number newValue){
            tipPercentage=BigDecimal.valueOf(newValue.intValue()/100.0);
            tipPercentageLabel.setText(percent.formate(tipPercentage));
        }
    })
```



# Ch14	Strings, Characters and Regular Expressions



## 考点

- String
- StringBuilder
- Character
- Regular Expression



## String

- 不可变，字符内容在创建后不能更改，因为类字符串不提供允许修改字符串对象内容的方法

- new String（不等）/常量串（双等）

  

## StringBuilder

- 用于创建和操作动态字符串信息。

  - **不是线程安全的。**如果多个线程需要访问相同的动态字符串信息，请在代码中使用类StringBuffer。
  - 可变的



## String和StringBuffer

- String类是immutable的，而StringBuffer不受限制。
  -  **String 在java中是不可变类型**
- 例一

```java
String name = "Jobs";
name = "Cook";
//代码中 name 实际上是个 String对象的引用，而 "Jobs"和"Cook"都是String对象，所以 代码第二行只是改变了 name 这个引用的指向。String本身是不可变的
```

- 例二

```java
//实际上 String的所有这类处理值的方法都不是更改对象自己的值，而是创建一个新的String对象作为返回值。

String name = "Jobs";
name.substring(1); //从第二个字符开始截取 —— 预期结果是 obs
name.replace("o","x");//把o替换成x —— 预期结果是 xbs
name.toUpperCase();//所有字符都大写 —— 预期结果是 OBS
System.out.println(name); // 依然是 Jobs,String类是immutable的
```



## Character

char的包装类



## 正则表达式（不要求写）

- 描述了其他字符串中匹配字符的搜索模式。用于验证输入和确保数据处于特定格式。由文字字符和特殊符号组成。
  - 正则表达式的一个应用是**方便编译器的构造**。
  -  String.matches
  -  []：匹配一组没有预定义字符类的字符。
  -  -：字符范围。
  -  ^：如果括号中的第一个字符是“^”，则表达式接受除所指示的字符以外的任何字符。
  -  ![](\asset\图片15.png)
  -  ![](\asset\图片16.png)
  - Pattern表示正则表达式；Matcher包含正则表达式Pattern和用于搜索模式的CharSequence。
  - **尝试将正则表达式创建为StringBuilder是一个错误。**



# Ch15	files, Input output Stream NIO and xml serialization



## 考点

- **To create, read, write and update files.**
- The differences between text files and binary files.（两者区别）
- **Path**
- Scanner , Formatter 
- FileInputStream , FileOutputStream （知道这些流的特点）
- ObjectInputStream,ObjectOutputStream
- ObjectInputStream  ObjectOutputStream



## 创建，读取，写入和更新文件

```java
try {
   file.createNewFile();//create
  } catch (IOException e) {e.printStackTrace();}
  try {
   FileWriter fw = new FileWriter(file, true);//wirte
   BufferedWriter bw = new BufferedWriter(fw);//通过FileWriter和BufferedWriter
   bw.write("kingid");
   bw.flush();//update
   bw.close();
   fw.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
  try {
   FileReader fr = new FileReader(file);//read
   BufferedReader bReader = new BufferedReader(fr);
   String string = bReader.readLine();
   System.out.println(string);
  } catch (FileNotFoundException e) {e.printStackTrace();
  } catch (IOException e) {e.printStackTrace();}
 }
}
```





### 字符流

 <img src="\asset\图片17.png" style="zoom: 50%;" />

 <img src="\asset\图片18.png" style="zoom: 50%;" />

### 字节流

```java
//将字节数组写入data.bin文件
byte[] b = {1,2};
try {  
    DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("D:\\Users\\data.bin")));  
    log.info(":{}",b);  
    os.write(b); //写文件
    os.flush();  //刷新
    os.close();  //关闭
} catch (IOException e) {e.printStackTrace();}

//读取data.bin文件中的数据
try {  
    DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream("D:\\Users\\data.bin")));  
    byte[] c =new byte[2];  
    is.read(c);  //读文件
    is.close();  //关闭
    log.info(":{}",c);
} catch (IOException e) {e.printStackTrace();} |
```



## 拷贝文件

```java
private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
    Files.copy(source.toPath(), dest.toPath());
}
```



## 文本文件和二进制文件的区别

- 文本文件
  - 顺序文件，人们可读的文件
  - 字符流
- **二进制文件**
  - **字节流文件**



## 字节流和字符流的区别⭐

- 定义
  - 字节流的单位是字节，字符流的基本单元是Unicode码元
- 结尾
  - 字节流以stream结尾，字符类以reader和writer结尾。
- 处理方式
  - 字节流以Unicode编码，字符流以ASCII编码
- 缓冲区
  - 字节流默认不使用缓冲区，字符流使用缓冲区。

 ![](\asset\图片19.png)

![image-20220615150311980](C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220615150311980.png)

- Scanner , Formatter是用来读写text files,
- FileInputStream, FileOutputStream是用来读写binary files，
- ObjectInputStream，ObjectOutputStream是用来读写对象



## Path接口

- 实现路径的类的对象表示文件或目录的位置；

- **Paths类**

  - Paths类提供静态方法，用于**获取表示文件或目录位置的Path对象**

  - ```java
    Path path = Paths.get(input.nextLine());
    ```

    

## Scanner , Formatter 

- Scanner
  - ① hasNext()确定是否输入了文件的末尾键组合。
  - ② 不允许重新定位到文件的开头。只能关了重开。
- Formatter
  - 输出格式化字符串到指定的流。
    - ① 构造函数具有一个String参数接收文件的名称，包括其路径。如果没有指定路径，则JVM假定文件在执行程序的目录中。如果文件不存在，它将被创建；如果打开现有文件，其内容将被截断。
    - ② format()像printf()一样工作
    - ③ close()关闭文件，如果没有显式调用方法close，则当程序执行终止时，操作系统通常会关闭该文件。



## 访问文件：FileInputStream , FileOutputStream

-  FileInputStream

- ```java
  read(): int
  read(b: byte[]): int
  read(b: byte[], off: int, len: int): int
  close(): void
  skip(n: long): long
  ```

- FileOutputStream

- ```java
  write(int b): void
  write(b: byte[]): void
  write(b: byte[], off: int, len: int): void
  close(): voidflush(): void
  ```

  

## 对象流，序列化：Object(In/Out)putStream

![](\asset\图片20.png)



# Ch16	Generic Collections



## 考点

- What collections are.
- Arrays, Collections .
- collections framework.
- How to use them.



##  集合是什么

- 一种数据结构，包含对所有相同类型的对象的引用。



## Arrays, Collections

- Arrays类
  - 提供static方法asList，以将数组视为List集合。
- Collections类
  - 提供对集合进行搜索、排序和执行其他操作的静态方法。
  - **框架方法是多态的，每个方法都可以对实现特定接口的对象进行操作，而不管底层实现是什么。**
    - sort：对列表元素排序。
    - binarySearch：在列表中定位对象。
    - reverse：反转列表的元素。
    - shuffle：随机排列列表的元素。
    - fill：将每个列表元素设置为引用指定的对象。
    - copy：将引用从一个列表复制到另一个列表。
    - min：返回集合中最小的元素。
    - max：返回集合中最大的元素。
    - addAll：将数组中的所有元素追加到集合。
    - frequency：计算与指定元素相等的集合元素的数量。
    - disjoint：确定两个集合是否没有共同的元素。
- Collection接口
  - 通常用作方法中的参数类型，以允许对实现Collection接口的所有对象进行多态处理。
    - ① 包含用于添加、清除和比较集合中对象的大量操作。
    - ② 提供返回迭代器对象的方法，该方法允许程序遍历集合并在迭代期间从集合中移除元素。



## 集合架构

- (1) Collection

  - 从中派生出**Set，Queue，List**

- (2) Set

  - 一个集合类，不包含重复项，派生HashSet、TreeSet
    - ① HashSet：将其元素存储在哈希表中
    - ② TreeSet：将其元素存储在树中。

- (3) List

  - 有序的集合类，可以包含重复的元素，派生出ArrayList，LinkedList，Vector
    - **① Vector默认是同步的，线程安全**
    - **② ArrayList不是同步的，不安全**
    - **③ LinkList可以高效地插入（或删除）集合中间的元素。**

- (4) Map

  - 一个集合类，将key和value关联且不能包含重复的key，派生Hashtable、HashMap、TreeMap
    - ① **Hashtable：**在哈希表中存储元素，**线程安全**
    - ② **HashMap：**在哈希表中存储元素，**不安全**
    - ③ TreeMap：在树中存储元素，实现**SortedMap**

- (5) Queue

  - 典型的一个先进先出的集合，用于模拟等待队列
  - ① PriorityQueue：按元素的自然顺序排列元素。

    - 元素按优先级顺序插入，这样最高优先级元素（即最大值）将是从PriorityQueue中删除的第一个元素。

- (6) 迭代器遍历

  ```java
  List<String> list = new ArrayList<String>(); 
  list.add("["); list.add("A"); list.add("]"); 
  System.out.println(list); // 输出[[,A,]]
  //List的迭代器ListIterator
  ListIterator it = list.listIterator(); 
  while(it.hasNext()) { 
      if ("[".equals(it.next()) || "]".equals(it.next()))  it.remove(); 
      else it.add("*"); 
  }
  System.out.println(list);
  ```
  
  
  
- (7)比较器接口

  - java.lang.**Comparable**
    - 唯一定义的方法是**compareTo()**
  - java.util.**Comparator**
    - 继承该抽象类，需要overide它的**compare()**抽象方法
  
  
  
  
  ⭐⭐⭐⭐⭐

<img src="\asset\图片21.png" style="zoom:80%;" /> 

<img src="C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220617124025878.png" alt="image-20220617124025878" style="zoom:80%;" />



## Map没有实现Collection接口

- Map提供的是键值对映射（即Key和value的映射），而collection提供的是一组数据（并不是键值对映射）。如果map继承了collection接口，那么所有实现了map接口的类到底是用map的键值对映射数据还是用collection的一组数据呢（就我们平常所用的hashMap、hashTable、treeMap等都是键值对，所以它继承collection完全没意义）
-  Map和List、set不同，Map放的是键值对，list、set放的是一个个的对象。说到底是因为数据结构不同，数据结构不同，操作就不一样，所以接口是分开的。还是接口分离原则
- ![image-20220617111928119](C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220617111928119.png)



## List转换为Set

```java
static <E> Set<E> duplicate(List<? extends E> L){ 
    Set<T> set = new HashSet<T>();
    set=L.stream.collect(Collectors.toSet());
	return set;
}
```



## List、Set、Map区别

- List、Set都是继承自Collection接口，Map则不是。
- List元素有放入顺序，元素可重复，Set元素无放入顺序，元素不可重复，重复元素会覆盖掉。Map不可重复，适合储存键值对的数据。



# Ch17	Java SE 8 Lambdas and Streams



## 考点

- what functional programming is
- lambda expressions**(看书上lambda表达式的例子！)**
  - function interface（只有一个方法的内部匿名类）
  - lamda表达式用在哪里：**用在一切需要函数式接口的地方**
  - @interface annotation（标注是函数式接口，没有可以，有会报错）

- Stream’s basic methods （要求能读）
  - filter、map……
  - 读程序，选择执行结果




## 函数式编程是什么

- **(1) 把函数的一些特性应用于编程语言之中，是面向行为的编程。**
- **(2) 可以用它来编写程序，比用以前的技术编写的程序更快、更简单、更简洁（bug更少）、更容易并行化。**
- (3) 关键技术：lambdas表达式和streams功能



## 函数式接口

- （1）只包含一个抽象方法的接口，称为函数式接口。

- （2）可以通过Lambda表达式来创建该接口的对象。（若Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上进行声明）。

- （3）可以在任意函数式接口上使用@FunctionalInterface注解，这样做可以检查它是否是一个函数式接口，同时javadoc也会包含一条声明，说明这个接口是一个函数式接口。




## lambda表达式

- **表示匿名方法，用于实现函数接口的简写表示法，类似于匿名内部类**。由后跟箭头标记（->）的参数列表和正文组成，如下所示：

  (参数列表)->{语句}

  - 表达式的类型是lambda表达式实现的功能接口的类型。
  - 可以在任何需要的功能接口中使用。
  - 参数类型通常可以省略
    - 如：(x,y)->{return x+y;}
  - 正文只包含一个表达式时，可以省略return关键字和大括号(引用透明性)
    - 如：(x，y)->x+y
  - 参数列表只包含一个参数时，可以省略括号
    - 如x->x*2
  - 空参数列表用()
    - () -> System.out.println(“欢迎来到lambdas!”)
  - **方法引用**
    - 可以将lambda替换为该方法的名称。



## lambda例子

```java
public class QuestionA {
	
	/*编写一个可以传递给带有 IntConsumer(接口) 参数的方法的 lambda。 lambda 应显示其参数，后跟一个空格。*/	
	public void haveFun(IntConsumer fun,int value) {
    	fun.accept(value);
    }
	
	public static void main(String[] args) {
		QuestionA one=new QuestionA();
		//匿名方法
		IntConsumer con= new IntConsumer() {
			public void accept(int value) {
				System.out.printf("%d ",value);
			}
		};
		//参数和方法体之间加上->	
		IntConsumer con1= value->System.out.printf("%d ",value);;
		one.haveFun(con,2);
		one.haveFun(con1,3);
		one.haveFun(value->{System.out.printf("%d ",value);},3);
	}
}
```



## Stream的基本方法

- (1) 中间操作：形成新的stream对象
  - ① lazy计算
  - ② map, filter, distinct, limit, sorted,of
- (2) 终端操作：
  - ① 贪婪计算
  - ② count, min, max, average, summaryStatistics,reduce 



# Ch20	Generic Classes and Methods



## 考点

- **Generic class, generic methods** 
- To understand **raw types（原始类型**） and how they help achieve **backwards compatibility（向后兼容性）**.
- To use **wildcards**(通配符) when **precise type information（精确类型信息）** about a parameter is not required in the method body.



## 泛型类、泛型方法

- **泛型方法 and 泛型类** enable you to specify, with a single method declaration, a set of related methods, or with a single class declaration, a set of related types, respectively.
  - 单个方法指明一组相关方法，单个类指明一组相关类。

<img src="https://upload-images.jianshu.io/upload_images/1335607-39085e68d80f2e3e?imageMogr2/auto-orient/strip|imageView2/2/w/970/format/webp" alt="img" style="zoom: 80%;" />



### 泛型类

- 定义泛型类：class后面加尖括号，定义泛型
- 常见的ArrayList类就是一个泛型类
  - 它在实现List接口的时候没有给泛型赋值，因此也就变成了一个泛型类。

```java
//Generic Class

//ArrayList就是一个泛型类
public class ArrayList<E> extends AbstractList<E>  implements List<E>,RandomAccess,Cloneable,java.io.Serializable{
    
    private static final long serialVersionUID=8683452581122892189L;
    //在创建对象的时候确定泛型的类型
    ArrayList <String> list=new ArrayList<>();
    
    //add方法参数就是一个泛型。
    //也就是说创建对象时确定的是哪个类型，使用add方法就只能添加这个类型
    public boolean add(E e){
    	ensureCapacityInternal(minCapacity:size+1);
        elementData[size++]=e;
        return true;
    }
}
```



### 泛型方法

-  修饰符 <代表泛型的变量> 返回值类型 方法名（参数）{}
-  **调用方法时，确定泛型的类型。**
-  返回值类型有尖括号，写上类型占位符。（参数、返回值、局部变量）

```java
//泛型方法
public static <T> void print(T[] list) {
    for (T element ：list ) {
        System.out.print(element);        
	}                          
}
```

 

### equal泛型方法

```java
public class IsEqual {

       public static void main(String[] args) {
              Object one = new Object();
              Object two = one;
           	  //调用方法时，确定泛型的类型
              if(isEqualTo(one,two)) {
                     System.out.println("Objects one and two are equal");
              }

              int four = 5;
              int five = 5;
              if(isEqualTo(four,five)) {
                     System.out.println("5 is equal to 5");
              }
       }
	  //修饰符 <代表泛型的变量> 返回值类型 方法名（参数）{}
       public static <T> boolean isEqualTo(T arg1, T arg2) {
              return arg1.equals(arg2);
       }
}
```



## 原始类型以及它们如何帮助实现向后兼容性

- (1) 实例化泛型类而不指定类型参数

- ```java
  Stack objectStack = new Stack( 5 );
  ```

- (2) 编译器**在每个类型参数的泛型类中隐式地使用类型Object**。

- **(3) 操作不安全，可能导致异常。**

<img src="C:\Users\icebear\AppData\Roaming\Typora\typora-user-images\image-20220616150422392.png" alt="image-20220616150422392" style="zoom:80%;" />

```java

AnimalHouse<Cat> house = new AnimalHouse<Cat>(); 

AnimalHouse<Cat> house = new AnimalHouse<Animal>(); 
// 2: fails to compile
//AnimalHouse<Cat> and AnimalHouse<Animal> are not compatible types, even though Cat is a subtype of Animal.

AnimalHouse house = new AnimalHouse<Animal>(); 
//4: compiles with a warning
//AnimalHouse is a raw type. References to generic type AnimalHouse<E> should be parameterized

AnimalHouse house = new AnimalHouse(); 
house.setAnimal(new Dog());
//4: compiles with a warning
/*The compiler doesn't know what type house contains. It will accept the code, but warn that there might be a problem when setting the animal to an instance of Dog.*/
/*Using a generic type as a raw type might be a way to work around a particular compiler error(实例化泛型类而不指定类型参数), but you lose the type checking that generics provides, so it is not recommended.*/
```



## ？代表一种“未知类型（通配符wildcards）

- 当方法主体中不需要有关参数的精确类型信息时使用通配符：？代表一种“未知类型
  - (1) 能指定为**参数类型的超类或子类的方法参数、返回值、变量或字段等**
  - (2) **因为在方法的头中没有指定类型参数名，不能在方法的整个主体中将其用作类型名。**
  - (3) 在方法的**类型参数部分**使用通配符，或在方法体中将通配符**用作变量的显式类型**是**语法错误**。
- 原来的这里是ArrayList<Number>会报错
  - List<Number>和List<Integers>需要的类型不匹配，（Number和Integer是is a，但是List<Number>和List<Integers>不是is a）都是List，只是参数不同。
  - 要引入通配符
  - 使得方法接收所有泛型类型为`Number`或`Number`子类的`Pair`类型：


![](\asset\图片22.png)



# Ch23	Concurrency⭐



## 考点

- How to **create** and **execute** a new thread.
  - 继承Thread类
  - 实现Runnable接口（**runnable填空**）
  - 匿名类的方式

- The **life cycle** of a thread.
- Thread **synchronization.**



##  如何创建、运行一个线程⭐

- 每个线程都必须实现runnable接口 并有run方法

```java
//Lock && Condition
Lock lock=new ReentrantLock();
Condition canRead=lock.newCondition();
Condition canWrite=lock.new Condition();
```



### 例一

```java
public class BlockingBufferTest { 
    public static void main( String[] args ) { 
        // create new thread pool with two threads 
        ExecutorService application = Executors.newCachedThreadPool(); //⭐
        // create BlockingBuffer to store ints 
        Buffer sharedLocation = new BlockingBuffer();
        // try to start producer and consumer 
    	try{ 
            application. execute( new Producer( sharedLocation ) ); //⭐
            application. execute( new Consumer( sharedLocation ) ); //⭐
        } // end try 
        catch ( Exception exception ) { exception.printStackTrace(); } // end catch 
        application.shutdown() ; //⭐
    } // end main 
} // end class BlockingBufferTest
```



### 例二

 <img src="\asset\图片23.png" style="zoom: 33%;" />			<img src="\asset\图片24.png" style="zoom:33%;" />



### 例三: Illustrate(举例说明) how to make an object as a separate thread running

- 比如说我们想在游戏里面让不同角色并行攻击。
- 首先创建类Battle，实现Runnable接口。
- 启动的时候，创建一个Battle对象battle1，然后再根据该battle对象创建一个线程对象，借助线程对象的start()方法，启动一个新的线程。
- 在创建Thread对象的时候，把battle1作为构造方法的参数传递进去，这个线程启动的时候，就会去执行battle1.run()方法了。

```java
Battle battle1 = new Battle(gareen,teemo);
new Thread(battle1).start();
```



## 一个线程的生命周期（Life Cycle）

- 实现了runnable接口的类的对象，可以作为线程跑。（进入runnable状态）（runnable←→ready）

### new

- ① 一个新的线程在这种状态下开始其生命周期。
- ② 一直保留到启动，直到处于在执行任务的可运行状态

### waiting

- ① 可运行线程在**等待另一个线程执行任务**时可以过渡到这个状态
- ② 只有当另一个线程通知它继续执行时（notify唤醒），才能转换回可运行状态。

### timed waiting

- ① 可运行线程可以在指定的时间间隔内进入此状态。
- ② **当时间间隔过期或等待的事件发生时**，转换回可运行状态。
- **③ 不能使用处理器，即使一个是可用的。**

### blocked（阻塞）

- 当一个可运行的线程试图执行一个不能立即完成的任务时（IO、网络等），它将过渡到这个状态，并且**它必须暂时等待该任务完成**。

### terminated（结束）

- 可运行线程在成功完成任务或以其他方式终止（可能是由于错误）时进入此状态。

<img src="\asset\图片25.png" style="zoom: 50%;" />



## 线程同步

- **线程同步**即当有一个线程在对内存进行操作时，其他线程都不可以对这个内存地址进行操作，直到该线程完成操作， 其他线程才能对该内存地址进行操作，而其他线程又处于等待状态。
- **同步方法**是用于控制对对象的访问的方法。
- **同步语句**只能在线程获得同步语句引用的对象或类的锁后才能执行。

```java
synchronized ( object ) { statements } // end synchronized statement
```

- 类ArrayBlockingQueue

  - 实现接口阻塞队列的完全实现的线程安全缓冲区类。

- 实现方法获取并设置为同步方法，这要求线程在尝试访问缓冲区数据之前获得Buffer对象上的监视器锁

- Object方法wait、notify和notifyAll可以在线程无法执行任务而等待时使用。

  - **① wait**
    - **释放其监视器锁，并将调用线程置于等待状态**
  - **② notify**
    - **允许等待线程再次变到可运行状态**
  - **③ notifyAll**
    - **等待监视器锁的所有线程都有资格重新获取锁**



# Ch24	JDBC



## 考点

- How to connect to a database



## 如何连接一个数据库

```java
import java.sql.SQLException; 
public class DisplayAuthors {  
    public static void main(String args[]) {   
        
        final String DATABASE_URL = "jdbc:derby:books";       
        //通过数据库进行查找
        final String SELECT_QUERY = "SELECT authorID, firstName, lastName FROM authors";      
        // use try-with-resources to connect to and query the database
        try {                                 
            //此处可以设置超时连接
            Connection connection = DriverManager.getConnection(DATABASE_URL, "deitel", "deitel");              		 Statement statement = connection.createStatement();  
            //存储结果
            ResultSet resultSet = statement.executeQuery(SELECT_QUERY)) {     
                // get ResultSet's meta data     
                //多少行，多少列，每列名字
                ResultSetMetaData metaData = resultSet.getMetaData();     
                int numberOfColumns = metaData.getColumnCount();            
                System.out.printf("Authors Table of Books Database:%n%n");     
                // display the names of the columns in the ResultSet     
                for (int i = 1; i <= numberOfColumns; i++) {      
                    System.out.printf("%-8s\t", metaData.getColumnName(i));     
                }     
                System.out.println();     
                // display query results     
                while (resultSet.next()) {      
                    for (int i = 1; i <= numberOfColumns; i++) {  
                        //获取相关存储的值
                        System.out.printf("%-8s\t", resultSet.getObject(i));      
                    }      System.out.println();     
                }    
        } catch (SQLException sqlException) { sqlException.printStackTrace(); }                           		} 
} 
```



 



# Ch26	Network

-  使用套接字和数据报实现Java网络应用程序。



## 考点

- To implement Java networking application by using **sockets** and **datagrams**.



## 套接字⭐

- 使应用程序能够查看网络。进程建立与另一进程的连接。提供面向连接的服务，协议是TCP。
- 服务器：

| 1、ServerSocket server = new ServerSocket( portNumber, queueLength );  // 创建ServerSocket对象（端口号和长度） |
| ------------------------------------------------------------ |
| 2、**Socket** connection = server**.accept()**;  **// 服务端等待客户端连接，**没连接则处于阻塞状态 |
| 3、Socket.getOutputStream(); Socket.getInputStream();  **// 进行通信**，输入输出流，OutputStream类、InputStream类、读写操 |
| // to get the **OutputStream and InputStream** objects that enable that server to communicate with the client by sending and receiving bytes. |
| 4、//the processing phase. **// 处理阶段**                   |
| 5、Socket.close();  **//关闭流和连接**                       |

- 客户端

| Socket connection = new Socket(serverAddress, port);  //创建Socket对象 |
| ------------------------------------------------------------ |
| Socket.getOutputStream();  Socket.getInputStream()； //OutputStream类、InputStream类，**输入输出流通信** |
| Socket.close();  **//关闭流**                                |



## 数据报

- 传输单个信息包。无连接，协议是UDP，不能保证传输顺序，可能丢包、重复
  - **① DatagramPacket用于发送和接收信息，DatagramSocket发送和接收数据包。**
  - ② Socket-Exception
    - DatagramSocket构造函数未能将DatagramSocket绑定到指定的端口

 ![](\asset\图片26.png)

 ![](\asset\图片27.png)

![](\asset\图片28.png)