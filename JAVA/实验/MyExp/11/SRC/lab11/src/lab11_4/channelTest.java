package lab11_4;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class channelTest 
{
	public static void main(String[] args) throws IOException
	{
		RandomAccessFile File1 = new RandomAccessFile("src/lab11_4/file1.txt", "rw");
		RandomAccessFile File2 = new RandomAccessFile("src/lab11_4/file2.txt", "rw");
		FileChannel channelFirst = File1.getChannel();
		FileChannel channelSecond = File2.getChannel();
		
		String newData = "Magic always comes with a price.";
		String newData2 = "Love is the most powerful magic.";
		
		//覆盖写,文件1
//		ByteBuffer buf = ByteBuffer.allocate(50);
//		buf.clear();
//		buf.put(newData.getBytes());
//		//翻转此缓冲区。 将限制设置为当前位置，然后将位置设置为零。 如果标记已定义，则将其丢弃
//		//在一系列通道读取或放置操作之后，调用此方法以准备一系列通道写入或相关获取操作
//		buf.flip();
//		while(buf.hasRemaining()) channelFirst.write(buf);
		
		//非覆盖写（直接续写）
		//分配一个新的字节缓冲区
		//新缓冲区的位置将为零，其限制将是其容量，其标记将未定义，并且其每个元素将初始化为零。 它将有一个后备数组，其数组偏移量为零。
		ByteBuffer buf = ByteBuffer.allocate(50);
		//对第一个文件进行写
		if(channelFirst.size() == 0)
		{
			channelFirst.position(channelFirst.size());
			//将字节数组包装到缓冲区中。
			//新缓冲区将由给定的字节数组支持；也就是说，对缓冲区的修改将导致数组被修改，反之亦然。
			//新缓冲区的容量和限制将为array.length，其位置为零，其标记为未定义。 它的后备数组将是给定的数组，并且它的数组偏移量为零。
			channelFirst.write(ByteBuffer.wrap(newData.getBytes()));
		}
		else
		{
			//添加回车
			channelFirst.position(channelFirst.size());
			channelFirst.write(ByteBuffer.wrap("\n".getBytes()));
			//在回车后面加上需要输入的句子
			channelFirst.position(channelFirst.size());
			channelFirst.write(ByteBuffer.wrap(newData.getBytes()));
		}
		//对第二个文件进行写
		if(channelSecond.size() == 0)
		{
			channelSecond.position(channelSecond.size());
			channelSecond.write(ByteBuffer.wrap(newData2.getBytes()));
		}
		else
		{
			channelSecond.position(channelSecond.size());
			channelSecond.write(ByteBuffer.wrap("\n".getBytes()));
			channelSecond.position(channelSecond.size());
			channelSecond.write(ByteBuffer.wrap(newData2.getBytes()));
		}
		
		//关闭文件
		File1.close();
		File2.close();
		
		//读取
		File1 = new RandomAccessFile("src/lab11_4/file1.txt", "rw");
		File2 = new RandomAccessFile("src/lab11_4/file2.txt", "rw");
		channelFirst = File1.getChannel();
		channelSecond = File2.getChannel();
		buf = ByteBuffer.allocate(50);
		//输出文件1的内容
		System.out.println("File1内容");
		while(channelFirst.read(buf) != -1) //当没有结束时，打印每个buf
		{
			buf.flip();
			while(buf.hasRemaining()) System.out.print((char)buf.get());
			buf.clear();
		}
		System.out.println();
		//输出文件2的内容
		System.out.println("\nFile2内容");
		while(channelSecond.read(buf) != -1)
		{
			buf.flip();
			while(buf.hasRemaining())  System.out.print((char)buf.get());
			buf.clear();
		}
		
		File1.close();
		File2.close();
	}
}
