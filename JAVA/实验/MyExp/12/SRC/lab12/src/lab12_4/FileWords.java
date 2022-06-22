package lab12_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileWords 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String line;
		File file = new File("src/lab12_4/TheGreatDictator.txt");
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
		Pattern p=Pattern.compile("[;.,\"\\?!:—“”(){}']");//
		while(scan.hasNextLine())
		{
			line = scan.nextLine();
			//将一行按照空格和-分隔开,+表示1次或多次
			String[] lineWords = line.split("[ -]+");
			for(int i = 0; i < lineWords.length; i++)
			{
			    //用Pattern类的matcher()方法生成一个Matcher对象
				Matcher m= p.matcher(lineWords[i]);
				//将这些特殊字符都删掉
				lineWords[i] = m.replaceAll("");
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
		System.out.println("文章中包含的单词如下：");
		//按照出现次数排序输出
		//entrySet返回映射中包含的映射的集合视图。集合由映射支持，因此对映射的更改会反映在集合中，反之亦然。
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>()
		{
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
			{
				return o2.getValue().compareTo(o1.getValue());//重写，哈希表是比较值
			}
		});
		
		int wordsCount = 0;
		int prevalue=0;
		for(Map.Entry<String, Integer> entry : list)
		{
			//使用keySet()方法获取所有的key值
			if(wordsCount==0) {
				prevalue=entry.getValue();
				System.out.println(entry.getValue()+":\t"+entry.getKey() );
			}
			else if(entry.getValue()!=prevalue) {
				System.out.println(entry.getValue()+":\t"+entry.getKey() );
			}
			else System.out.println("\t"+entry.getKey() );
			wordsCount += entry.getValue();
			prevalue=entry.getValue();
		}
		System.out.println("\n总单词数：" + wordsCount);
	}
}
