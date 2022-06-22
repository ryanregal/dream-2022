package RationalNumbers;

//用于使用分数执行算术的有理数类
public class Rational {
	private int numerator;//有理数的分子
	private int denominator;//有理数的分母

	// 构造函数,简化形式保存数字
	public Rational(int numerator, int denominator) {
		int commonDivisor = greatestDivisor(numerator, denominator);
		this.numerator = numerator / commonDivisor;
		this.denominator = denominator / commonDivisor;
	}

	// 无参构造函数，创建一个分子分母都为1的有理数
	public Rational() {
		this(1, 1);
	}

	//GETTERS
	public int getNumerator() {
		return numerator;
	}
	public int getDenominator() {
		return denominator;
	}
	
	//SETTERS
	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	// 返回两个有理数的和
	public static Rational add(Rational number1, Rational number2) {
		int lcd = (Math.abs(number1.getDenominator() * number2.getDenominator()))
				/ greatestDivisor(number1.getDenominator(), number2.getDenominator());

		int numerator1 = (lcd / number1.getDenominator()) * number1.getNumerator();
		int numerator2 = (lcd / number2.getDenominator()) * number2.getNumerator();
		return new Rational(numerator1 + numerator2, lcd);
	}

	// 返回有理数与另一个有理数的差
	public static Rational subtract(Rational number1, Rational number2) {
		int lcd = (Math.abs(number1.getDenominator() * number2.getDenominator()))
				/ greatestDivisor(number1.getDenominator(), number2.getDenominator());

		int numerator1 = (lcd / number1.getDenominator()) * number1.getNumerator();
		int numerator2 = (lcd / number2.getDenominator()) * number2.getNumerator();
		return new Rational(numerator1 - numerator2, lcd);
	}
	
	// 返回两个有理数的积
	public static Rational multiply(Rational number1, Rational number2){
		return new Rational(number1.getNumerator() * number2.getNumerator(),
				number1.getDenominator() * number2.getDenominator());
	}
	
	// 返回有理数和另一个有理数的商
	public static Rational divide(Rational number1, Rational number2){
		   return new Rational(number1.getNumerator() * number2.getDenominator(),
					number1.getDenominator() * number2.getNumerator());
	}
		
	//以a/b的形式打印有理数，其中a为分子，b为分母
	public String toString() {
		if(denominator==1) return String.format("%d", numerator);
		return String.format("%d/%d", numerator, denominator);
	}
	
	//返回浮点格式打印有理数。
	//类的用户能够指定小数点右侧的精度位数
	public String toStringFloat(int precision){
		double number = (double)numerator / denominator;
		return String.format("%." + precision + "f", number);
	}
	
	//返回最大公约数
	public static int greatestDivisor(int number1, int number2){
		boolean divisorFound = false;
		while (!divisorFound){
			if(number1 == number2)	{
				divisorFound = true;
				break;
			}
			int tempMax = Math.max(number1, number2);
			int tempMin = Math.min(number1, number2);
			number1 = Math.abs(tempMax);
			number2 = Math.abs(tempMin);
			number1 = number1 - number2;
			if(number1 == number2)	divisorFound = true;
		}
		return number1;
	}
}