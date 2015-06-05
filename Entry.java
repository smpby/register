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

	private String note;
	private String date;
	private String IDofStudent;
	private String completeEntry;

	/**
	 * Constructs entry with text, date and ID of one student.
	 * 
	 * @param n
	 *            For the text to write.
	 * 
	 * @param date
	 *            Of an entry.
	 * 
	 * @param ID
	 *            Of one student.
	 */
	public Entry(String n, String date, String ID) {
		note = n;
		this.date = date;
		IDofStudent = ID;
	}

	/**
	 * Generating of a string with the ID of student, date, note formated for
	 * later saving.
	 * 
	 * @return writeable String for register.txt.
	 */

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("<§>" + IDofStudent + "<§>");
		s.append(date + "<§>");
		s.append(note);
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
	 * @return note, text of the entry.
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Gets date of an entry.
	 * 
	 * @return date of an entry.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Gets the ID of a student.
	 * 
	 * @return IDofStudent.
	 */
	public String getIDofStudent() {
		return IDofStudent;
	}

}
