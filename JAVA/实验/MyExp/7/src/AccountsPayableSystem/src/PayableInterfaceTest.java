// Fig. 10.14: PayableInterfaceTest.java 
// Payable interface test program processing Invoices and Employees polymorphically.

/*修改PayableInterfaceTest，以多态处理两个Invoices
 * 一名SalariedEmployee、一名HourlyEmployee、一名CommissionEmployee和一名BasePlusCommissionEmployee。
 * 首先输出每个Payable对象的字符串表示形式。如果对象是 BasePlusCommissionEmployee，则将其基础金额增加10%。
 * 最后输出每个payment对象的支付金额*/

public class PayableInterfaceTest 
{
	public static void main(String[] args) {
		// 创建 Payable数组
		Payable[] payableObjects = new Payable[7];

		// 用实现 Payable 的对象填充数组
		payableObjects[0] = new Invoice("01234", "seat", 2, 375.00);
		payableObjects[1] = new Invoice("56789", "tire", 4, 79.95);
		payableObjects[2] = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00);
		payableObjects[3] = new SalariedEmployee("Lisa", "Barnes", "888-88-8888", 1200.00);
		payableObjects[4] = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40);
		payableObjects[5] = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06);
		payableObjects[6] = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);

		System.out.println("Invoices and Employees processed polymorphically:");

		// 一般处理数组payableObjects中的每个元素
		for (Payable currentPayable : payableObjects) {
			if (currentPayable instanceof BasePlusCommissionEmployee) {
				//向下转换 Employee 引用到 BasePlusCommissionEmployee 引用
				BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) currentPayable;
				employee.setBaseSalary(1.10 * employee.getBaseSalary());
				System.out.printf("%nnew base salary with 10%% increase is: $%,.2f", employee.getBaseSalary());
			}
			//输出 currentPayable 及其相应的支付金额
			System.out.printf("%n%s %n%s: $%,.2f%n", currentPayable.toString(), // could invoke implicitly
					"payment due", currentPayable.getPaymentAmount());
		}
	} 
} 