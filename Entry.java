package de.dhbw.vs.fpr.register;

public class Entry {

	private String notiz;
	private String datum;
	private String IDofPupil;
	private String completeEntry;
	
	public Entry(String n, String date, String ID){
		notiz = n;
		datum = date;
		IDofPupil = ID;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(";" +IDofPupil+ ";");
		s.append(datum+ ";");
		s.append(notiz);
		return s.toString();
	}
	
	public String getEntryString(){
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
