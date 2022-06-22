// 创建一个Employee变量数组，
// 以存储对新Employee层次结构中每个具体类的对象的引用。
// 对于每个Employee，显示其String表示和earnings。 


public class PayrollSystemTest 
{
	public static void main(String[] args)
	{
		Employee[] employees = new Employee[5];
		
		employees[0] = new SalariedEmployee("John","Smith", "111-11-1111", 800.00);
		employees[1] = new HourlyEmployee("Karen","Price","222-22-2222",16.75,40);
		employees[2] = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
		employees[3] = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);
		employees[4] = new PieceWorker("Higashikata","Josuke","000005",20,14);
		
		System.out.println("Employees processed individually:\n");
		
		for(Employee eachEmployee : employees)
		{
			System.out.printf("%s%nearned: $%,.2f%n%n", eachEmployee,  eachEmployee.earnings());
		}
	}
}