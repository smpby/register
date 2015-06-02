package de.dhbw.vs.fpr.register;

/**
 * Class DataCorruptionException custom exception for handling formating errors
 * in source data sets
 * 
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */
public class DataCorruptionException extends Exception {

	// I have no idea why this is needed. Eclipse forced me...
	private static final long serialVersionUID = 723500623848917757L;

	public DataCorruptionException(String msg) {
		super(msg);
	}

}
