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
		createEntry();
	}
	
	public void createEntry(){
		StringBuilder s = new StringBuilder();
		s.append(";" +IDofPupil+ ";");
		s.append(datum+ ";");
		s.append(notiz);
		
		completeEntry = s.toString() ;
	}
	
	public String getEntryString(){
		return completeEntry;
	}

	public String getNotiz() {
		return notiz;
	}

	public void setNotiz(String notiz) {
		this.notiz = notiz;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getIDofPupil() {
		return IDofPupil;
	}

	public void setIDofPupil(String iDofPupil) {
		IDofPupil = iDofPupil;
	}
	
}
