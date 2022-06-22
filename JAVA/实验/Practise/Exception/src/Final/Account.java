package Final;

public class Account {
	private double balance;
	private double deficit;
	
	//构造函数
	public Account(double balance) {
		this.setBalance(balance);
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double depo) {
		this.setBalance(this.getBalance() + depo);
	}
	
	public void withdraw(double draw) throws OverdraftException{
		deficit=getBalance()-draw;
		if(deficit<0) {
			throw new OverdraftException("余额不足",deficit);
		}
		else this.setBalance(this.getBalance() - draw);
	}
	
	public static void main(String args[]) {
		Account test=new Account(10);
		try {
			test.withdraw(100);
		}catch(OverdraftException e) {
			e.printStackTrace();
			System.err.println("透支金额："+e.getDeficit());
		}
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
