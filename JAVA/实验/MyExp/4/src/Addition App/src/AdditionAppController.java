import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdditionAppController {
	//用@FXML注解来表示属性可以被FXML格式文件访问
	@FXML
	private Label resultLabel;
	@FXML
	private TextField number1TextField;
	@FXML
	private TextField number2TextField;

	@FXML
	//计算输入的两个数的相加结果
	void calculateSumButtonPressed(ActionEvent event) {
		int number1 = getNumber(number1TextField);
		int number2 = getNumber(number2TextField);
		int sum = number1 + number2;
		resultLabel.setText(String.valueOf(sum));//将内容输出
	}
	//获取用户输入
	private int getNumber(TextField numberTextField) {
		int number = 0;
		try {
			number = Integer.parseInt(numberTextField.getText().trim());
			//numberTextField是对象的引用，它有一个getText方法来获取文本内容
			//用String类中的trim（）方法来减少用户输入的多余空格
			//Integer.parseInt会把一个String型对象转换为一个整型对象。
		} catch (NumberFormatException e) {
			numberTextField.setText("Enter an integer");//提示要输入一个整数
			numberTextField.selectAll();//选择输入文本
			numberTextField.requestFocus();//光标进入控件
		}
		return number;
	}
}
