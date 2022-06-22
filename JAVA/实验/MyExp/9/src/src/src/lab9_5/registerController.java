package lab9_5;

import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class registerController 
{
	@FXML private Button confirmationButton;
	@FXML private Button closeButton;
	@FXML private TextField userNameTextField;
	@FXML private TextField passwordTextField;
	@FXML private TextField confirmPasswordTextField;
	
	private String userName;
	private String password;
	private String confirmPassword;
	private Boolean isRegisterSuccessful = false;
	
	static ArrayList<String> wrongInformation = new ArrayList<>();
	static ArrayList<Throwable> exceptionList = new ArrayList<>();
	
	@FXML
	private void closeButtonPressed(ActionEvent e)
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void confirmationButtonPressed(ActionEvent e)
	{
		try
		{
			userName = userNameTextField.getText();
			if(userName.length() == 0)
			{
				wrongInformation.add("Please enter user name£¡");
				throw new userNameException();
			}
			else if(userName.length() < 4)
			{
				wrongInformation.add("Username length cannot be less than 4£¡");
				throw new userNameException();
			}
		}
		catch(userNameException e1)
		{
			exceptionList.add(e1);
		}
		
		try
		{
			password = passwordTextField.getText();
			confirmPassword = confirmPasswordTextField.getText();
			if(password.length() == 0)
			{
				wrongInformation.add("Please enter password£¡");
				throw new passwordConfirmException();
			}
			else if(!password.equals(confirmPassword))
			{
				wrongInformation.add("Inconsistent password entered twice£¡");
				throw new passwordConfirmException();
			}
		}
		catch(passwordConfirmException e1)
		{
			exceptionList.add(e1);
		}
		
		try
		{
			if(exceptionList.size() > 0)
			{
				isRegisterSuccessful = false;
				throw new Exception();
			}
				
			else
				isRegisterSuccessful = true;
		}
		catch(Exception e1)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Registration failed£¡");
			alert.setHeaderText("Please follow the tips below to update your registration information:");
			String contextText = wrongInformation.get(0);
			for(int i = 1; i < wrongInformation.size(); i++)
			{
				contextText += ("\n" + wrongInformation.get(i));
			}
			alert.setContentText(contextText);
			exceptionList.clear();
			wrongInformation.clear();
			alert.showAndWait();
		}
		
		if(isRegisterSuccessful == true)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Registration success£¡");
			alert.setHeaderText("Your user information is as follows:");
			StringBuilder passwordHidden = new StringBuilder(password.length());
			for(int i = 0; i < password.length(); i++)
			{
				passwordHidden.append('*');
			}
			alert.setContentText("Username£º" + userName + "\n" + "Password£º" + passwordHidden);
			ButtonType buttonTypeOne = new ButtonType("Show Password");
			ButtonType buttonTypeCancel = new ButtonType("Confirm", ButtonData.CANCEL_CLOSE);
			 
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
			 
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne)
			{
				alert.setContentText("Username£º" + userName + "\n" + "Password£º" + password);
				alert.getButtonTypes().clear();
				alert.getButtonTypes().setAll(buttonTypeCancel);
				alert.showAndWait();
			} 
		}
	}
}