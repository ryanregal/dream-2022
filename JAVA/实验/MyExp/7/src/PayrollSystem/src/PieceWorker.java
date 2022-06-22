
public class PieceWorker extends Employee
{
	private double wage;
	private int pieces;
	
	//constructor
	public PieceWorker(String firstName, String lastName, String socialSecurityNumber,
			double wage, int pieces) 
	{
		super(firstName, lastName, socialSecurityNumber);
		this.pieces = pieces;
		this.wage = wage;
	}
	
	//Getters Setters
	public double getWage() 
	{
		return wage;
	}
	public void setWage(double wage) 
	{
		this.wage = wage;
	}
	public int getPieces() 
	{
		return pieces;
	}
	public void setPieces(int pieces) 
	{
		this.pieces = pieces;
	}
	
	//º∆À„ ’“Ê
	@Override
	public double earnings()
	{
		return pieces * wage;
	}
	
	
	@Override
	public String toString()
	{
		return String.format("pieces employee: %s%nwage: $%,.2f; pieces: %d", super.toString(),
			 getWage(), getPieces());
	}
	
}