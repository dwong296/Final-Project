package rocketBase;

import static org.junit.Assert.*; 

import java.util.ArrayList;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {
	
	@Test
	public void TestAllRates()
	{
		ArrayList<RateDomainModel> allRates;
		allRates = RateDAL.getAllRates();
		
		for (RateDomainModel r: allRates)
		{
			try {
				double rRate = RateBLL.getRate(r.getiMinCreditScore());
				assertEquals(r.getdInterestRate(), rRate, 0.01);
			} catch (RateException e) {
				fail("TestAllRates Failed");
			}
		}
	}
	
	@Test (expected = RateException.class)
	public void TestBadRate() throws RateException
	{
		double rRate = RateBLL.getRate(400);
	}
	
	@Test
	public void TestPayment()
	{
		double pv = 300000;
		double fv = 0;
		int nbrOfPayments = 360;
		double rate = 0.04/12;
		
		double pmt = RateBLL.getPayment(rate, nbrOfPayments, pv, fv, false);
	
		assertEquals(-1432.25, pmt, 0.01);
	}
}
