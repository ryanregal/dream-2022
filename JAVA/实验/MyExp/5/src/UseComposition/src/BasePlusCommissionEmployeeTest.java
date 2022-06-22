// BasePlusCommissionEmployee test program.

public class BasePlusCommissionEmployeeTest {
	public static void main(String[] args) {
		// instantiate BasePlusCommissionEmployee object
		BasePlusCommissionEmployee employee = new BasePlusCommissionEmployee("Bob", "Lewis", "333-33-3333", 5000, .04,
				300);

		// get base-salaried commission employee data
		System.out.println("Employee information obtained by get methods:%n");
		System.out.printf("%s %s%n", "First name is", employee.getCommissionEmployee().getFirstName());
		System.out.printf("%s %s%n", "Last name is", employee.getCommissionEmployee().getLastName());
		//社保号
		System.out.printf("%s %s%n", "Social security number is",
				employee.getCommissionEmployee().getSocialSecurityNumber());
		//销售额
		System.out.printf("%s %.2f%n", "Gross sales is", employee.getCommissionEmployee().getGrossSales());
		//佣金率
		System.out.printf("%s %.2f%n", "Commission rate is", employee.getCommissionEmployee().getCommissionRate());
		//基本工资
		System.out.printf("%s %.2f%n", "Base salary is", employee.getBaseSalary());

		employee.setBaseSalary(1000);

		System.out.printf("%n%s:%n%n%s%n", "Updated employee information obtained by toString", employee.toString());
	}
}
