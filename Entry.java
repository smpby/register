package de.dhbw.vs.fpr.register;

/**
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */
public class Entry {

	private String notiz;
	private String datum;
	private String IDofPupil;
	private String completeEntry;

	public Entry(String n, String date, String ID) {
		notiz = n;
		datum = date;
		IDofPupil = ID;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(";" + IDofPupil + ";");
		s.append(datum + ";");
		s.append(notiz);
		return s.toString();
	}

	public String getEntryString() {
		return completeEntry;
	}

	public String getNotiz() {
		return notiz;
	}

	public String getDatum() {
		return datum;
	}

	public String getIDofPupil() {
		return IDofPupil;
	}

}
