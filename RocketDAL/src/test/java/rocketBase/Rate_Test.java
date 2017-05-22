package rocketBase;

import static org.junit.Assert.*; 

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {
	@Test
	public void test() 
	{
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println("Rates size: " + rates.size());
		assert(rates.size() > 0);
	}
	
}

