package lab10_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class words 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String line;
		//打开文件report
		File file = new File("src/lab10_1/report.txt");
		if(!file.exists()) 
		{
			System.err.println("No such file!");
			return;
		}
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(file);
		//建立一个String和Integer键值的哈希表
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		//使用Pattern.compile函数来实现对指定字符串的截取
		//生成Pattern对象p并且编译正则表达式
		Pattern p=Pattern.compile("[.,\"\\?!:—“”(){}]");
		while(scan.hasNextLine())
		{
			line = scan.nextLine();
			//将一行按照空格分隔开,+表示1次或多次
			String[] lineWords = line.split("[ ]+");
			for(int i = 0; i < lineWords.length; i++)
			{
				////用Pattern类的matcher()方法生成一个Matcher对象
				Matcher m= p.matcher(lineWords[i]);
				//将这些特殊字符都删掉
				lineWords[i] = m.replaceAll("");
				//System.out.println(lineWords[i]);
				if(!lineWords[i].isEmpty())
				{
					//如果不为空，则将该单词加入到哈希表的key中，对应为1
					if(!hashMap.containsKey(lineWords[i]))
					{
						hashMap.put(lineWords[i], 1);
					}
					else
					{
						int times = hashMap.get(lineWords[i]);
						times++;//记录单词出现了几次
						hashMap.put(lineWords[i], times);
					}
				}
				
			}
		}
		System.out.println("The article contains the following words：\n");
		//使用keySet()方法获取所有的key值
		Iterator<String> it = hashMap.keySet().iterator();
		while(it.hasNext())
		{
			String word = it.next();
			//如果该单词出现次数大于1则打印
			if(hashMap.get(word) >= 1)
				System.out.println(word);
		}
	}
}
