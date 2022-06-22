package Final;

public class OverdraftException extends Exception{
	private double deficit;
	public double getDeficit() {
		return deficit;
	}
	public OverdraftException(String msg, double deficit) {
        super(msg);
        this.deficit = deficit;
    }
}
