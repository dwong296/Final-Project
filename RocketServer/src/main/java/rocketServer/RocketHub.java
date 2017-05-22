package rocketServer;

import java.io.IOException;

import exceptions.LoanRequestException;
import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest)message;
			
			try {
				lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
			} catch (RateException e) {
				sendToAll(e);
				return;
			}
			
			lq.setdPayment(RateBLL.getPayment((lq.getdRate()*0.01)/12, lq.getiTerm()*12, lq.getdAmount(), 0, false));
			
			if (!RateBLL.checkPITI(lq))
			{
				LoanRequestException lqe = new LoanRequestException(lq);
				sendToAll(lqe);
				return;
			}
			sendToAll(lq);
		}
	}
}