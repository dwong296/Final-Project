package exceptions;

import rocketData.LoanRequest;

public class LoanRequestException extends Exception 
{
	private LoanRequest LQ;

	public LoanRequestException(LoanRequest lQ) {
		super();
		LQ = lQ;
	}

	public LoanRequest getLQ() {
		return LQ;
	}
}
