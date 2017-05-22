package rocket.app.view;

import eNums.eAction; 
import exceptions.LoanRequestException;
import exceptions.RateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {
	private TextField txtNew;
	
	private MainApp mainApp;
	
	@FXML private TextField txtIncome;
	@FXML private TextField txtExpenses;
	@FXML private TextField txtCreditScore;
	@FXML private TextField txtHouseCost;
	@FXML private TextField txtDownPayment;
	@FXML private TextField txtTerm;
	@FXML private Label lblMortgagePaymentAmount;
	
	@FXML private Button btnCalculate;
	
	@FXML private Label lblError;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiTerm(Integer.parseInt(txtTerm.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		
		a.setLoanRequest(lq);
		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		lblMortgagePaymentAmount.setText(String.format("%.2f", Math.abs(lRequest.getdPayment())));
	}
	
	public void HandleExceptions(Exception e)
	{
		if (e instanceof LoanRequestException)
			lblError.setText("ERROR) Income does not cover payments"); 
		else if (e instanceof RateException)
		{
			RateException r = (RateException)e;
			lblError.setText("ERROR) No Rate for credit score: " + r.getRDM().getiMinCreditScore());
		}
	}
}