package application;

import java.text.NumberFormat;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CarPaymentCalculatorController 
{
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	private double loanRate = 0.05;
	
	private double[] loanRateArray = new double[] {0.0475, 0.0485, 0.0495, 0.0505};
	
	@FXML
	private TextField carPriceTextField;	
	@FXML
	private TextField downPaymentTextField;		
	@FXML
	private TextField loanDurationTextField;	
	@FXML
	private TextField monthlyPaymentTextField;	
	@FXML
	private ChoiceBox<String> loanRateChoiceBox;		
	
	@FXML
	private void calculateButtonPressed(ActionEvent event)
	{
		try
		{
			double carPrice = Double.parseDouble(carPriceTextField.getText());
			double downPayment = Double.parseDouble(downPaymentTextField.getText());
			double loan = carPrice - downPayment;
			double monthlyLoanRate = loanRate / 12;
			int loanDuration = 0;
			
			if(loanRate == 0.0475) loanDuration = 24;
			else if(loanRate == 0.0485) loanDuration = 36;
			else if(loanRate == 0.0495) loanDuration = 48;
			else if(loanRate == 0.0505) loanDuration = 60;
			
			//等额本息还款
			//当月还款 = 本金 × 月利 × （ 1 ＋月利）＾还款月数］ ÷ ［（ 1 ＋月利）＾还款月数－ 1 ］
			double monthlyPayment = (loan * monthlyLoanRate * Math.pow((1 + monthlyLoanRate), loanDuration))
					/ (Math.pow((1 + monthlyLoanRate), loanDuration) - 1);
			
			if(loanDuration == 0)
			{
				loanDurationTextField.setText("Please Input Loan Duration");
				monthlyPaymentTextField.setText("Please Input Loan Duration");
			}
			else
			{
				loanDurationTextField.setText(loanDuration + " month");
				monthlyPaymentTextField.setText(currency.format(monthlyPayment));
			}
			
		}
		catch(NumberFormatException ex)
		{
			carPriceTextField.setText("Please Input Car Price");
			carPriceTextField.selectAll();
			carPriceTextField.requestFocus();
			
			downPaymentTextField.setText("Please Input Down Payment");
			downPaymentTextField.selectAll();
			downPaymentTextField.requestFocus();
		}
	}
	
	//选择框
	public void initialize()
	{
		loanRateChoiceBox.setItems(FXCollections.observableArrayList("two- 4.75%", "three- 4.85%", "four- 4.95%", "five- 5.05%"));
		
		loanRateChoiceBox.getSelectionModel().selectedIndexProperty().addListener
		(
			(ObservableValue<? extends Number>ov, Number oldValue, Number newValue) ->{
				loanRate = loanRateArray[newValue.intValue()];
			}
		);
	}
}