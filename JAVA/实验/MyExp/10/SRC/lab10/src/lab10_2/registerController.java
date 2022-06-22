package lab10_2;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

public class registerController 
{
	@FXML private TextField userNameTextField;
	@FXML private PasswordField passwordTextField;
	@FXML private TextField emailTextField;
	@FXML private Button confirmationButton;
	@FXML private Button closeButton;
	
	private String userName;
	private String password;
	private String email;
	
	static ArrayList<String> wrongInformation = new ArrayList<>();
	static ArrayList<Throwable> exceptionList = new ArrayList<>();
	
	//用户名要求：不能为空，只能由字母、数字和_组成，第一位不能为数字。
	//[]匹配方括号内的任意单个字符,+表示1次或多次
	//$匹配输入字符串结尾的位置,^匹配输入字符串开始的位置(在方括号则表示不接受)
	private Pattern userNamePattern = Pattern.compile("^[A-Za-z0-9_]+$");//匹配由英文字母或数字组成的字符串 
	private Pattern userNamePatternStartByNum = Pattern.compile("^[0-9]+[A-Za-z0-9_]+$");
	private Matcher userNameMatcher;
	//不能为空，密码长度至少8位，由字母、数字、下划线组成。
	private Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9_]+$");
	private Matcher passwordMatcher;
	//不能为空，需包含”@”符号。”@”符号后需要出现多个由”. ”分割的词。
	//\w代表可用于标识的字符,'\w{1,}' 等价于 '\w+'
	//将下一个字符标记为或特殊字符、或原义字符、或向后引用、或八进制转义符。如序列 '\\' 匹配 "\"
	//"()":标记一个子表达式的开始和结束位置。
	private Pattern emailPattern = Pattern.compile("^\\w+@\\w{1,}+(.\\w+)+$");
	private Matcher emailMatcher;
	
	private Boolean isRegisterSuccessful = false;
	
	@FXML
	private void confirmationButtonPressed(ActionEvent e)
	{
		//获取用户名
		try
		{
			userName = userNameTextField.getText();
			userNameMatcher = userNamePattern.matcher(userName);
			if(userName.length() == 0){
				wrongInformation.add("Username can not be empty！");
				throw new userNameException1();
			}
			if(userNameMatcher.find()){
				userNameMatcher = userNamePatternStartByNum.matcher(userName);
				//如果发现用户名的开头是数字
				if(userNameMatcher.find())
				{
					wrongInformation.add("Username cannot start with a number！");
					throw new userNameException2();
				}
			}
			//若果用户名除了英文字母或数字下划线还有其他字符
			else{
				wrongInformation.add("Username can only consist of letters, numbers, and underscores！");
				throw new userNameException2();
			}
		}
		catch(userNameException1 e1){
			exceptionList.add(e1);
		}
		catch(userNameException2 e2){
			exceptionList.add(e2);
		}
		//获取密码
		try
		{
			password = passwordTextField.getText();
			passwordMatcher = passwordPattern.matcher(password);
			if(password.length() == 0){
				wrongInformation.add("Password can not be blank！");
				throw new passwordException();
			}
			if(passwordMatcher.find())
			{
				//密码小于8位
				if(password.length() < 8){
					wrongInformation.add("Password cannot be less than 8 characters！");
					throw new passwordException1();
				}
			}
			else
			{
				wrongInformation.add("The password can only consist of letters, numbers, and underscores！");
				throw new passwordException1();
			}
		}
		catch(passwordException e1){
			exceptionList.add(e1);
		}
		catch(passwordException1 e2){
			exceptionList.add(e2);
		}
		//获取邮箱
		try
		{
			email = emailTextField.getText();
			emailMatcher = emailPattern.matcher(email);
			
			if(email.length() == 0){
				wrongInformation.add("E-mail can not be empty！");
				throw new emailException1();
			}
			if(!emailMatcher.matches())
			{
				wrongInformation.add("Email format error! \nCorrect format: number/letter/underscore+@+address suffix");
				throw new emailException2();
			}
		}
		catch(emailException1 e1){
			exceptionList.add(e1);
		}
		catch(emailException2 e2){
			exceptionList.add(e2);
		}
		
		//判断登录是否成功
		try
		{
			if(exceptionList.size() > 0){
				isRegisterSuccessful = false;
				throw new Exception();
			}
			else isRegisterSuccessful = true;
		}
		catch(Exception e1)
		{
			//警告窗口
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Registration failed！");
			alert.setHeaderText("Please follow the tips below to update your registration information:");
			String contextText = wrongInformation.get(0);
			for(int i = 1; i < wrongInformation.size(); i++){
				contextText += ("\n" + wrongInformation.get(i));
			}
			//打印错误消息
			alert.setContentText(contextText);
			exceptionList.clear();
			wrongInformation.clear();
			alert.showAndWait();
		}
		
		//注册成功
		if(isRegisterSuccessful == true)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Registration success！");
			alert.setHeaderText("Your user information is as follows:");
			//设置隐藏密码
			StringBuilder passwordHidden = new StringBuilder(password.length());
			for(int i = 0; i < password.length(); i++)
			{
				passwordHidden.append('*');
			}
			alert.setContentText("User：" + userName + "\n" + "Password：" + passwordHidden + "\n" + "Email：" + email);
			ButtonType buttonTypeOne = new ButtonType("Show Password");
			ButtonType buttonTypeCancel = new ButtonType("OK", ButtonData.CANCEL_CLOSE);
			 
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
			 
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne)
			{
				alert.setContentText("User：" + userName + "\n" + "Password：" + password + "\n" + "Email：" + email);
				alert.getButtonTypes().clear();
				alert.getButtonTypes().setAll(buttonTypeCancel);
				alert.showAndWait();
			} 
		}
	}
	//关闭按钮
	@FXML
	private void closeButtonPressed(ActionEvent e)
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
}
