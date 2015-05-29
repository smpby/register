package de.dhbw.vs.fpr.register;

import java.util.ArrayList;

public class Student {

	private String name = new String();
	private String vorname = new String();

	private ArrayList<Entry> eintraege = new ArrayList<Entry>();
	private String eindeutigeID;
	private String ID;

	public Student(String firstName, String name, String ID, String klassenID) {
		this.name = name;
		vorname = firstName;
		this.ID = ID;
		StringBuilder sg = new StringBuilder();
		sg.append(klassenID);
		sg.append(this.ID); // Hier wird die eindeutige ID aus KlassenID und
							// Schüler ID zusammengesetzt
		eindeutigeID = sg.toString();
	}

	public void addEintrag(Entry e) {
		if (!e.getNotiz().isEmpty()) {
			// throw away empty entrys
			if (eintraege.size() == 0) {
				// in the case of no entrys: add entry
				this.eintraege.add(e);
			} else {
				boolean tmpflag = false;

				for (int i = 0; i < eintraege.size(); i++) {

					if (this.eintraege.get(i).getDatum()
							.contentEquals(e.getDatum())
							&& this.eintraege.get(i).getNotiz()
									.contentEquals(e.getNotiz())) {
						// remember if entry exists already
						tmpflag = true;
					}

				}
				if (!tmpflag) {
					// only add nonexisting entrys
					this.eintraege.add(e);
				}
			}
		}
	}

	public String getName() {
		return name;
	}

	public String getVorname() {
		return vorname;
	}

	public String getID() {
		return ID;
	}
	public Entry getEntry(int i){
		return eintraege.get(i);
	}

	public void getEntrys() {
		if (eintraege.isEmpty()) {
			System.out.println("Keine Eintraege vorhanden!");
		} else {
			System.out.println("Datum      Eintrag");
			for (int i = 0; i < this.eintraege.size(); i++) {

				System.out.println(this.eintraege.get(i).getDatum() + " "
						+ this.eintraege.get(i).getNotiz());
				/**
				 * prints the first 10 characters of the date (if the date is to
				 * long caused by special input manner...) and than the entry
				 * info
				 */

			}
		}

	}

	public String geteindeutigeID() {
		return eindeutigeID;
	}

	public boolean checkEntrys() {// checks if there are any entrys for this
									// student
		return eintraege.isEmpty();
	}

	public int getEntrySize() {
		return eintraege.size();
	}

	public String returnEntry(int i) {
		return eintraege.get(i).toString();
	}
}
