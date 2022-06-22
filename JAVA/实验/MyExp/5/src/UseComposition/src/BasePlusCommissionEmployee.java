
public class BasePlusCommissionEmployee {
	//BasePlusCommissionEmployee类使用CommissionEmployee类作为它的成员
	private CommissionEmployee commissionEmployee;
	private double baseSalary; // base salary per week

	// 带参数构造函数
	public BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales,
			double commissionRate, double baseSalary) {
		commissionEmployee = new CommissionEmployee(firstName, lastName, socialSecurityNumber, grossSales,commissionRate);
		// 如果 baseSalary 无效则抛出异常
		if (baseSalary < 0.0) {
			throw new IllegalArgumentException("Base salary must be >= 0.0");
		}
		this.baseSalary = baseSalary;
	}

	public CommissionEmployee getCommissionEmployee() {
		return commissionEmployee;
	}

	//Setters
	public void setBaseSalary(double baseSalary) {
		if (baseSalary < 0.0) {
			throw new IllegalArgumentException("Base salary must be >= 0.0");
		}
		this.baseSalary = baseSalary;
	}

	// Getters
	public double getBaseSalary() {
		return baseSalary;
	}
	public double earnings() {
		return getBaseSalary() + commissionEmployee.earnings();
	}

	// return String representation of BasePlusCommissionEmployee
	@Override
	public String toString() {
		return String.format("%s %s%n%s: %.2f", "base-salaried", commissionEmployee, "base salary", getBaseSalary());
	}
}
