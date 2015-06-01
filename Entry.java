package de.dhbw.vs.fpr.register;

/**
 * Class to create one entry.
 * 
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */
public class Entry {

	private String notiz;
	private String datum;
	private String IDofStudent;
	private String completeEntry;

	/**
	 * Constructs entry with text, date and ID of one student.
	 * 
	 * @param n
	 *            for the text to write.
	 * @param date
	 *            of an entry.
	 * @param ID
	 *            of one student.
	 */
	public Entry(String n, String date, String ID) {
		notiz = n;
		datum = date;
		IDofStudent = ID;
	}

	/**
 * 
 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(";" + IDofStudent + ";");
		s.append(datum + ";");
		s.append(notiz);
		return s.toString();
	}

	/**
	 * Gets entry in the data type string.
	 * 
	 * @return completeEntry, of one student.
	 */
	public String getEntryString() {
		return completeEntry;
	}

	/**
	 * Gets text of the entry.
	 * 
	 * @return notiz, text of the entry.
	 */
	public String getNotiz() {
		return notiz;
	}

	/**
	 * Gets date of an entry.
	 * 
	 * @return datum of an entry.
	 */
	public String getDate() {
		return datum;
	}

	/**
	 * Gets the ID of a student.
	 * 
	 * @return IDofStudent.
	 */
	public String getIDofPupil() {
		return IDofStudent;
	}

}
