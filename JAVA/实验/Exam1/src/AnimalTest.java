//作者：庾晓萍
import java.util.*;

//测试类
public class AnimalTest {

	//储存动物基本信息的Animal数组
	static ArrayList<Animal> animalList = new ArrayList<>();
	//储存动物疫苗信息的Animal数组
	static ArrayList<ArrayList<String> > vaccineList=new ArrayList<ArrayList<String> >();
	static Scanner scanner=new Scanner(System.in);
	
	//打印主菜单
	public static void printMenu() {
		System.out.println("\n请输入对应输入进行相应菜单操作：");
		System.out.println("1.	新增动物");
		System.out.println("2.	疫苗登记");
		System.out.println("3.	展示所有动物信息");
		System.out.println("4.	列出某动物的疫苗记录");
		System.out.println("5.	结束程序");
	}
	
	//打印添加菜单
	public static void addMenu() {
		System.out.println("\n请输入动物信息，实例：\n"
		 +"\t老虎 N001 雄 167 白色 30\n"
		 +"\t鸵鸟 M001 雌 灰色 40\n"
		 +"结束新增，请输入end");
	}
	
	//打印添加疫苗信息
	public static void vaccineMenu() {
		System.out.println("\n请按照以下方式输入疫苗信息：\n"
		 +"\tN001 2022年4月15日 犬瘟热\n"
		 +"结束新增，请输入end");
	}
	
	//打印查询菜单
	public static void searchMenu() {
	}
	
	//添加动物到数组中
	public static void addAnimal() throws Exception{
		addMenu();//打印添加菜单
		String input=scanner.nextLine();//读入输入字符串
		
		while(!input.equals("end")) {
			//将一个字符串通过空格分割
			String[]inputArray=input.split("[ ]+");
			
			if(inputArray[0].equals("老虎")) {
				//获得Class对象、获得构造、通过构造对象获得实例化对象
				Tiger addTiger=(Tiger) Class.forName("Tiger").getConstructor(String[].class).newInstance((Object)inputArray);
				animalList.add(addTiger);
				System.out.println("创建成功");
			}
			
			else if(inputArray[0].equals("鸵鸟"))
			{
				Ostrich addOstrich = (Ostrich) Class.forName("Ostrich").getConstructor(String[].class).newInstance((Object)inputArray);
				animalList.add(addOstrich);
				System.out.println("创建成功");
			}
			else{
				throw new IllegalTypeException();
			}
			addMenu();//打印添加菜单
			input=scanner.nextLine();//读入输入字符串
		}
	}
	
	//显示所有动物
	public static void printAll()
	{
		if(animalList.size() == 0)
		{
			System.out.println("目前没有动物。");
			return;
		}
		System.out.println("一共有" + animalList.size() + "只动物：");
		for(Animal animal : animalList)
		{
			animal.printAnimal();
		}
		System.out.printf("\n");
	}
	
	//输入打疫苗信息
	public static void addVaccine() throws IllegalNumberException {
		vaccineMenu();//打印添加菜单
		String input=scanner.nextLine();//读入输入字符串
		//疫苗二维arraylist中，[0]表示动物编号，[1]表示疫苗时间，[2]表示疫苗名称
		
		while(!input.equals("end")) {
			//将一个字符串通过空格分割
			String[]inputArray=input.split("[ ]+");
			int flag=0;
			for(Animal animal : animalList) {
				if((animal.getNumber()).equals(inputArray[0])) {
					ArrayList<String> s=new ArrayList<String>();
					s.add(inputArray[0]);
					s.add(inputArray[1]);
					s.add(inputArray[2]);
					vaccineList.add(s);
					flag=1;
				}
			}
			if(flag==0) throw new IllegalNumberException();
			else System.out.println("输入成功!");
			vaccineMenu();//打印添加菜单
			input=scanner.nextLine();//读入输入字符串
		}
	}
	
	//查找疫苗记录
	public static void searchVaccine() {
		System.out.println("请输入动物编号\n");
		if(vaccineList.size() == 0)
		{
			System.out.println("当前没有疫苗记录，请先添加！");
			return;
		}
		searchMenu();//打印查询菜单
		String searchString=scanner.nextLine();//读取输入字符串
		
		int flagA=0;
		for(ArrayList<String> inform : vaccineList) {
			if(inform.get(0).equals(searchString)) {
				flagA=1;
				System.out.println(inform.get(0)+' '
				+inform.get(1)+' '+inform.get(2));
			}
		}
		if(flagA==0) System.out.println("无相关记录");
	}
	
	//main方法
	public static void main(String[] args)
	{
		int choice = 0;
		boolean runflag = true;
		
		while(runflag)
		{
			printMenu();
			try{choice=Integer.parseInt(scanner.nextLine());} 
			//没有输入数字
			catch (Exception e) {
				System.out.println("请输入1-5数字");
				choice=0;
			}
			
			switch(choice)
			{
			case 0:
				break;
			case 1:
				int flagadd=1;
				while(flagadd==1) {
					flagadd=0;
					try { addAnimal();} 
					//输入动物类型错误
					catch (IllegalTypeException e) {
						System.out.println("第一个应该为老虎或者鸵鸟");
						System.out.println("创建不成功");
						flagadd=1;
					}
					//输入动物性别错误，应该是雌、雄或者双性
					catch (IllegalSexException e) {
						flagadd=1;
					}
					catch (Exception e) {
						System.out.println("输入格式错误，请重新输入");
						System.out.println("创建不成功");
						flagadd=1;
					}
				}
				break;	
			case 2:
				int flagB=1;
				while(flagB==1) {
					flagB=0;
					try{addVaccine();}catch(IllegalNumberException e) {
						System.out.println("动物编号错误!");
						flagB=1;
					}
					catch(Exception e) {
						System.out.println("输入格式错误");
						flagB=1;
					};
				}
				break;
			case 3:
				printAll();
				break;
			case 4:
				searchVaccine();
				break;
			case 5:
				runflag = false;
				break;
			default:
				System.out.println("请输入1-5数字");
				break;
			}
		}
		System.out.println("退出程序");
		return;
	}

}
