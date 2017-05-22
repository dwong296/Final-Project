package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception 
{
	private RateDomainModel RDM;

	public RateException(RateDomainModel rDM) {
		super();
		RDM = rDM;
	}

	public RateDomainModel getRDM() {
		return RDM;
	}
}