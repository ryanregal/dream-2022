package exception;
/*1. throws 出现在方法声明上，而throw通常都出现在方法体内。
2. throws 表示出现异常的一种可能性，并不一定会发生这些异常；
throw则是抛出了异常，执行throw则一定抛出了某个异常对象。*/

/*可查异常： CheckedException
可查异常即必须进行处理的异常，要么try catch住,要么往外抛，谁调用，谁处理，比如 FileNotFoundException
如果不处理，编译器，就不让你通过*/

/*运行时异常RuntimeException指： 不是必须进行try catch的异常
常见运行时异常: 除数不能为0异常:ArithmeticException
下标越界异常:ArrayIndexOutOfBoundsException
空指针异常:NullPointerException
在编写代码的时候，依然可以使用try catch throws进行处理，与可查异常不同之处在于，即便不进行try catch，也不会有编译错误
Java之所以会设计运行时异常的原因之一，这些运行时异常太过于普遍，如果都需要进行捕捉，代码的可读性就会变得很糟糕。*/

/*错误Error，指的是系统级别的异常，通常是内存用光了
在默认设置下，一般java程序启动的时候，最大可以使用16m的内存
如例不停的给StringBuffer追加字符，很快就把内存使用光了。抛出OutOfMemoryError
与运行时异常一样，错误也是不要求强制捕捉的*/

/*运行时异常是运行的时候抛出的异常，非运行时异常，不运行也能抛出*/

/*Throwable是类，Exception和Error都继承了该类*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
 
public class TestException {
 
    public static void main(String[] args) {
    	method1();
    	System.out.println(method());
    }
    
    //method1就会接到该异常。 处理办法选择了try catch处理掉
    //也可以抛出去
    private static void method1() {
    	try {
    		method2();
    	}catch(Throwable e) {
    		e.printStackTrace();
    	}
    }
    
    //method2不处理，而是把这个异常通过throws抛出去
    private static void method2() throws FileNotFoundException{
        File f = new File("d:/LOL.exe");
        System.out.println("试图打开 d:/LOL.exe");
        //new FileInputStream(f);
        System.out.println("成功打开");
    }
    
    public static int method() {
    	try {return 1;}
    	catch(Exception e) {return 2;}
    	//若finally有返回值，那么将会覆盖try或是catch中返回的代码
    	finally {return 3;}
    }
    
}