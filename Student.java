package de.dhbw.vs.fpr.register;

import java.util.ArrayList;

/**
 * Class to create one student.
 * 
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */

public class Student {

	private String lastName = new String();
	private String firstName = new String();

	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private String explicitID;
	private String ID;

	/**
	 * Constructor constructs student with first name, name, ID and class ID
	 * from param.
	 * 
	 * @param firstName
	 *            of one student.
	 * @param name
	 *            of one student.
	 * @param ID
	 *            of one student.
	 * @param klassenID
	 *            of one student.
	 */
	public Student(String firstName, String name, String ID, String klassenID) {
		this.lastName = name;
		this.firstName = firstName;
		this.ID = ID;
		StringBuilder sg = new StringBuilder();
		sg.append(klassenID);
		sg.append(this.ID); // Building of the explicit ID of class ID and
							// students ID
		explicitID = sg.toString();
	}

	/**
	 * Adding of one entry to students entries (list).
	 * 
	 * @param e entry object to be added.
	 */
	public void addEntry(Entry e) {
		if (!e.getNote().isEmpty()) { // Empty entries are thrown away

			if (entries.size() == 0) { // If there are no entries, add entry

				this.entries.add(e);
			} else {
				if (!findEntry(e))
					// Entry was not found
					this.entries.add(e);
			}
		}
	}

	/**
	 * Gets name of one student.
	 * 
	 * @return name of one student.
	 */

	public String getName() {
		return lastName;
	}

	/**
	 * Gets first name of one student.
	 * 
	 * @return first name one student.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets ID of one student.
	 * 
	 * @return ID of one student.
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Gets entry of one student on position i.
	 * 
	 * @param i
	 *            Position of one student in the array.
	 * @return entries of one student.
	 */
	public Entry getEntry(int i) {
		return entries.get(i);
	}

	/**
	 * Prints date and the entry.
	 */
	public void getEntries() {
		if (entries.isEmpty()) {
			System.out.println("Keine Eintraege vorhanden!");
		} else {
			System.out.println("Datum      Eintrag");
			for (int i = 0; i < this.entries.size(); i++) {

				System.out.println(this.entries.get(i).getDate() + " "
						+ this.entries.get(i).getNote());
			}
		}

	}

	/**
	 * Gets explicit ID of one student.
	 * 
	 * @return explicit ID of one student.
	 */
	public String getExplicitID() {
		return explicitID;
	}

	/**
	 * Checks if there are any entries for this student.
	 * 
	 * @return entries of one student.
	 */
	public boolean hasNoEntries() {
		return entries.isEmpty();
	}

	/**
	 * Gets size of entry.
	 * 
	 * @return size of entry.
	 */
	public int getEntrySize() {
		return entries.size();
	}

	/**
	 * Lists entry.
	 * 
	 * @param i
	 *            of one student at the position i.
	 * @return entry of one student at the position i.
	 */
	public String returnEntry(int i) {
		return entries.get(i).toString();
	}

	/**
	 * Searches for entries for one Student.
	 * 
	 * @param toSearch
	 *            is the Entry to which the identical sibling should be found.
	 * @return true if toSearch was found as entry (with identical date and
	 *         information).
	 */
	public boolean findEntry(Entry toSearch) {
		for (int i = 0; i < entries.size(); i++) {
			if (this.entries.get(i).getDate().contentEquals(toSearch.getDate())
					&& this.entries.get(i).getNote()
							.contentEquals(toSearch.getNote())) {
				return true;
			}

		}
		return false;
	}
}
