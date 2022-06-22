//作者：庾晓萍
//鸵鸟类 继承抽象类Animal

public class Ostrich extends Animal{
	private int eggsNumber;//产蛋数

	//Setters Getters
	public int getEggsNumber() {
		return eggsNumber;
	}
	public void setEggsNumber(int eggsNumber) {
		this.eggsNumber = eggsNumber;
	}
	
	//构造函数
	public Ostrich(String[] inputs) throws IllegalSexException 
	{
		super(inputs);
		setColor(inputs[3]);
		this.eggsNumber=Integer.parseInt(inputs[4]);
	}
	
	//重写输出
	@Override
	public void printAnimal()
	{
		System.out.printf("%s %s %s %s %d\n", getAnimalType(), getNumber(),
				getSex(),getColor(), this.eggsNumber);
	}
}
