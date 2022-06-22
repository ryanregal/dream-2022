import java.util.*;

//图书馆类
public class Library {
	
	//储存书籍的Item类数组
	static ArrayList<Item> itemList = new ArrayList<>();
	static Scanner scanner=new Scanner(System.in);

	//打印主菜单
	public static void printMenu() {
		System.out.println("\n请输入命令编号：");
		System.out.println("a.	录入");
		System.out.println("b.	查询");
		System.out.println("c.	查看书列表");
		System.out.println("d.	退出");
	}
	
	//打印添加菜单
	public static void addMenu() {
		System.out.println("\n请按照以下格式输入：\n"
		 +"\t书 ISBN 书名 作者 出版社\n"
		 +"\t杂志 ISBN 杂志名 出版年 期号 出版社\n"
		 +"如：\n"
		 +"\t书 9787121340932 Java程序设计 约翰·刘易斯 电子工业出版社\n"
		 +"\t杂志 9771009633001 中国国家地理 2021 1 中国国家地理出版社\n"
		 +"其中出版年和期号必须为数字");
	}
	
	//打印查询菜单
	public static void searchMenu() {
		System.out.println("\n请按照以下格式输入查询信息:");
		System.out.println("\t书 ISBN 书名 作者 出版社");
		System.out.println("\t杂志 ISBN 杂志名 出版年 期号 出版社\n如:");
		System.out.println("\t书 9787121340932 Java程序设计 约翰·刘易斯 电子工业出版社");
		System.out.println("\t杂志 9771009633001 中国国家地理 2021 1 中国国家地理出版社");
		System.out.println("其中，空的字段均可用 null 代替");
	}
	
	//添加书籍到图书馆
	public static void addItem() throws Exception{
		addMenu();//打印添加菜单
		String input=scanner.nextLine();//读入输入字符串
		int flag=0;//判断是否添加完毕
		
		
		while(flag!=1) {
			//将一个字符串通过空格分割
			String[]inputArray=input.split("[ ]+");
			
			if(inputArray[0].equals("书")) {
				//获得Class对象、获得构造、通过构造对象获得实例化对象
				Book addBook=(Book) Class.forName("Book").getConstructor(String[].class).newInstance((Object)inputArray);
				itemList.add(addBook);
				String booknumber=String.format("%05d", itemList.size());
				addBook.setNumber(booknumber);
				System.out.println("创建成功,是否继续录入？(是/否)");
				input=scanner.nextLine();//读入输入字符串
				if(!input.equals("是")) flag=1;
			}
			
			else if(inputArray[0].equals("杂志"))
			{
				Magazine addMagazine = (Magazine) Class.forName("Magazine").getConstructor(String[].class).newInstance((Object)inputArray);
				itemList.add(addMagazine);
				String booknumber=String.format("%05d", itemList.size());
				addMagazine.setNumber(booknumber);
				System.out.println("创建成功，是否继续录入？(是/否)");
				input=scanner.nextLine();//读入输入字符串
				if(!input.equals("是")) flag=1;
			}
			else{
				throw new IllegalTypeException();
			}
			if(flag==1) break;
			else{
				addMenu();//打印添加菜单
				input=scanner.nextLine();//读入输入字符串
			}
		}
	}
	
	//查询图书馆中的书籍
	public static void searchItem() {
		
		if(itemList.size() == 0)
		{
			System.out.println("当前书架为空，无法进行查询，请先添加书本！");
			return;
		}
		
		ArrayList<Item> output=new ArrayList<>();//存储打印列表
		searchMenu();//打印查询菜单
		String searchString=scanner.nextLine();//读取输入字符串
		String[] searchArray = searchString.split("[ ]+");
		int hasFlag=0;
		
		if( searchArray[0].equals("书") || ( searchArray[0].equals("null") && searchArray.length==5))
		{
			for(Item item : itemList){
				if (	(searchArray[0].equals("null") || item.getType().equals(searchArray[0]))
						&& (searchArray[1].equals("null") || item.getIsbn().equals(searchArray[1]))
						&& (searchArray[2].equals("null") || item.getName().equals(searchArray[2]))
						&& (searchArray[3].equals("null") || ((Book)item).getAuthor().equals(searchArray[3]))
						&& (searchArray[4].equals("null") || (item).getPublisher().equals(searchArray[4]))
					) {
					output.add(item);
					hasFlag=1;
				}
			}
		}
		
		else if(searchArray[0].equals("杂志")  || ( searchArray[0].equals("null") && searchArray.length==6))
		{
			for(Item item : itemList){
				if (	(searchArray[0].equals("null") || item.getType().equals(searchArray[0]))
						&& (searchArray[1].equals("null") || item.getIsbn().equals(searchArray[1]))
						&& (searchArray[2].equals("null") || item.getName().equals(searchArray[2]))
						&& (searchArray[3].equals("null") || ((Magazine)item).getYear() == Integer.parseInt(searchArray[3]))
						&& (searchArray[4].equals("null") || ((Magazine)item).getIssueNumber().equals(searchArray[4]))
						&& (searchArray[5].equals("null") || (item).getPublisher().equals(searchArray[5]))
					) {
					System.out.println("找到一本杂志！");
					output.add(item);
					hasFlag=1;
				}
			}
		}
		
		if(hasFlag==1) {//打印结果
			System.out.printf("搜索到%d本书,信息如下：\n",output.size());
			for(Item item : output){
				item.printItem();
			}
			System.out.print("\n");
		}
		else System.out.println("没有搜索到相应书籍");
		output.clear();//清空输出列表
	}
	
	//显示所有书籍
	public static void printAll()
	{
		if(itemList.size() == 0)
		{
			System.out.println("目前没有书本。");
			return;
		}
		
		System.out.println("目前有" + itemList.size() + "本书，信息如下：");
		for(Item item : itemList)
		{
			item.printItem();
		}
		System.out.printf("\n");
	}
	
	//main函数
	public static void main(String[] args) 
	{
		String choice;
		boolean runflag = true;
		
		while(runflag)
		{
			printMenu();
			choice=scanner.nextLine();
			
			switch(choice)
			{
			case "a":
				try { addItem();}
				catch(IllegalTypeException e) 
				{
					System.out.println("书籍类型只能存储书刊或杂志\n");
					System.out.println("创建不成功");
				} 
				catch (Exception e) {
					System.out.println("请检查输入\n");
					System.out.println("创建不成功");
				}
				break;	
			case "b":
				searchItem();
				break;
			case "c":
				printAll();
				break;
			case "d":
				runflag = false;
				break;
			default:
				System.out.println("输入错误，请输入a-d的字母");
				break;
			}
		}
		System.out.println("退出程序");
		return;
	}	

}
