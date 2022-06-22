package lab14_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CircularBufferTest 
{
	public static void main(String[] args)
	{
		//一个 Executor，提供管理终止的方法和可以跟踪一个或多个异步任务的进度的方法。
		//创建一个线程池，根据需要创建新线程，但在可用时将重用以前构造的线程。
		ExecutorService application = Executors.newCachedThreadPool();
		
		CircularBuffer sharedLocation = new CircularBuffer();
		
		sharedLocation.displayState("Initial State");
		
		//三个线程随机产生1-10的随机数放入一个包含10个元素的循环缓冲区
		//一个线程从里面取出元素并输出。
		application.execute(new Producer(sharedLocation));
		application.execute(new Producer(sharedLocation));
		application.execute(new Producer(sharedLocation));
		application.execute(new Consumer(sharedLocation));
		
		application.shutdown();
	}
}
