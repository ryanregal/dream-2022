import java.util.*;

//车队类
public class Fleet {
	
	//储存车队的Vehicle类数组
	static ArrayList<Vehicle> carList = new ArrayList<>();
	static Scanner scanner=new Scanner(System.in);
	
	//打印主菜单
	public static void printMenu() {
		System.out.println("\n请输入对应输入进行相应菜单操作：");
		System.out.println("1.	新增车辆");
		System.out.println("2.	查询车辆");
		System.out.println("3.	列出所有车辆");
		System.out.println("4.	结束程序");
	}
	
	//打印添加菜单
	public static void addMenu() {
		System.out.println("\n请输入车辆信息，实例：\n"
		 +"\t小汽车 丰田 红色 4 2007 2厢\n"
		 +"\t卡车 雷诺 红色 3.5 2008\n"
		 +"结束新增，请输入end");
	}
	
	//打印查询菜单
	public static void searchMenu() {
		System.out.println("\n请按照“类型 商标 颜色 出厂年”的顺序输入条件，若条件为空用“null”代替,两个示例:");
		System.out.println("\t小汽车 丰田 红色 2007");
		System.out.println("\tnull 丰田 null null");
		System.out.println("输入end返回上级菜单");
	}
	
	//添加车辆到车队中
	public static void addVehicle() throws Exception{
		addMenu();//打印添加菜单
		String input=scanner.nextLine();//读入输入字符串
		
		while(!input.equals("end")) {
			//将一个字符串通过空格分割
			String[]inputArray=input.split("[ ]+");
			
			if(inputArray[0].equals("小汽车")) {
				//获得Class对象、获得构造、通过构造对象获得实例化对象
				Car addCar=(Car) Class.forName("Car").getConstructor(String[].class).newInstance((Object)inputArray);
				carList.add(addCar);
				System.out.println("创建成功");
			}
			
			else if(inputArray[0].equals("卡车"))
			{
				Truck addTruck = (Truck) Class.forName("Truck").getConstructor(String[].class).newInstance((Object)inputArray);
				carList.add(addTruck);
				System.out.println("创建成功");
			}
			else{
				throw new IllegalTypeException();
			}
			addMenu();//打印添加菜单
			input=scanner.nextLine();//读入输入字符串
		}
	}
	
	//查询车队中的车辆
	public static void searchVehicle() {
		
		if(carList.size() == 0)
		{
			System.out.println("当前车队为空，无法进行查询，请先添加车辆！");
			return;
		}
		
		ArrayList<Vehicle> output=new ArrayList<>();//存储打印列表
		searchMenu();//打印查询菜单
		String searchString=scanner.nextLine();//读取输入字符串
		
		while(!searchString.equals("end")) {
			String[] searchArray = searchString.split("[ ]+");
			
			if((searchArray[0].equals("小汽车") || searchArray[0].equals("卡车") || searchArray[0].equals("null")))
			{
				for(Vehicle vehicles : carList){
					if (	(searchArray[0].equals("null") || vehicles.getCarType().equals(searchArray[0]))
							&& (searchArray[1].equals("null") || vehicles.getTradeMark().equals(searchArray[1]))
							&& (searchArray[2].equals("null") || vehicles.getColor().equals(searchArray[2]))
							&& (searchArray[3].equals("null") || vehicles.getFactoryYear() == Integer.parseInt(searchArray[3]))
						)
						output.add(vehicles);
				}
				//打印结果
				System.out.printf("搜索到%d辆车,信息如下：\n",output.size());
				for(Vehicle vehicles : output)
				{
					vehicles.printVehicle();
				}
				System.out.print("\n");
			}
			else System.out.println("没有搜索到相应的交通工具");
			output.clear();//清空输出列表
			searchMenu();//再次打印查单菜单
			searchString = scanner.nextLine();//读取新的字符串	
		}
	}
	
	//显示所有车辆
	public static void printAll()
	{
		if(carList.size() == 0)
		{
			System.out.println("目前没有车辆。");
			return;
		}
		
		System.out.println("目前有" + carList.size() + "辆车，信息如下：");
		for(Vehicle vehicles : carList)
		{
			vehicles.printVehicle();
		}
		System.out.printf("\n");
	}
	
	public static void main(String[] args) 
	{
		int choice = 0;
		boolean runflag = true;
		
		while(runflag)
		{
			printMenu();
			choice=Integer.parseInt(scanner.nextLine());
			
			switch(choice)
			{
			case 1:
				try { addVehicle();}
				catch(IllegalYearException e) 
				{	
					System.out.println("创建不成功");
				} 
				catch(IllegalArgumentException e) 
				{
					System.out.println("创建不成功");
				} 
				catch(IllegalTypeException e) 
				{
					System.out.println("第一个应为小汽车或者卡车");
					System.out.println("创建不成功");
				} 
				catch (Exception e) {
					System.out.println("创建不成功");
				}
				break;	
			case 2:
				searchVehicle();
				break;
			case 3:
				printAll();
				break;
			case 4:
				runflag = false;
				break;
			default:
				System.out.println("请输入1-4的整数");
				break;
			}
		}
		System.out.println("退出程序");
		return;
	}	
	
}