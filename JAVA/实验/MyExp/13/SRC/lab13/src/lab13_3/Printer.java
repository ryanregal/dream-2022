package lab13_3;

public class Printer implements Runnable
{
	private final int startValue;
	
	public Printer(int value)
	{
		startValue = value;
	}
	
	public void run()
	{
		for(int i = startValue; i < startValue + 10; i++)
		{
			try
			{
				Thread.sleep(500);
			}
			catch(InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
			System.out.printf("%s\t\t´òÓ¡%d\n", 
					Thread.currentThread().getName(), i);	
		}
	}
}
