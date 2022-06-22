//作者：庾晓萍
//老虎类,继承抽象类Animal

public class Tiger extends Animal{
	private double weight;//体重
	private int teeth;//牙齿
	
	//Setters Getters
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getTeeth() {
		return teeth;
	}
	public void setTeeth(int teeth) {
		this.teeth = teeth;
	}
	
	//构造函数
	public Tiger(String[] inputs) throws IllegalSexException 
	{
		super(inputs);
		this.weight=Double.parseDouble(inputs[3]);
		setColor(inputs[4]);
		this.teeth=Integer.parseInt(inputs[5]);
	}
	
	//重写输出
	@Override
	public void printAnimal()
	{
		System.out.printf("%s %s %s %.0f %s %d\n", getAnimalType(), getNumber(),
				getSex(),getWeight(),getColor(), getTeeth());
	}
}
