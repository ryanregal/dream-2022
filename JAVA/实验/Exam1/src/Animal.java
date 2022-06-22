//作者：庾晓萍
//动物类

public abstract class Animal {
	private String animalType;//种类
	private String number;//编号
	private String sex;//性别
	private String color;//毛色
	
	//Setters Getters
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) throws IllegalSexException {
		if(sex.equals("雌")||sex.equals("雄")||sex.equals("双性")){
			this.sex = sex;
		}
		else {
			System.out.println("性别应该是雄，雌，或双性");
			throw new IllegalSexException();	
		}
		
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	
	//构造函数
	public Animal(String[] inputs) throws IllegalSexException 
	{
		this.animalType=inputs[0];
		this.number=inputs[1];
		setSex(inputs[2]);
	}
	
	//打印动物
	public abstract void printAnimal();
	
}
