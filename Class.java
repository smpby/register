package de.dhbw.vs.fpr.register;

import java.util.ArrayList;

public class Class {

	private String classID;
	private ArrayList<Student> students = new ArrayList<Student>();

	public Class(String ID) {
		classID = ID;
	}
	
	public Class(Class c){
		this.classID = c.classID;
		this.students = c.students;
	}

	public void createClass(String pupilList) {
		String allStudents[] = pupilList.substring(3).split(";");

		Student tmpStudent = new Student(allStudents[1], allStudents[2],
				allStudents[3].substring(0, 2), this.classID);
		students.add(tmpStudent);

		if (allStudents.length % 3 == 1) { // Prüfung ob der Array durch 3
											// Teilbar ist und 1 übrig bleibt
											// für die ID
			for (int i = 1; i < allStudents.length; i = i + 3) {

				Student s = new Student(allStudents[i], allStudents[i + 1],
						allStudents[i + 2].substring(0, 2), this.classID); // Schueler
																			// wird
																			// erzeugt,
																			// Array
																			// Index
																			// 0
																			// ist
																			// für
																			// die
																			// ID
																			// Reserviert,
																			// danach
																			// sind
																			// immer
																			// 3
																			// nachfolgende
																			// ArrayPlätze
																			// mit
																			// Name,
																			// Vorname
																			// und
																			// Schüler
																			// ID
				students.add(s);

			}
		} else {
			System.out.println("Dataset is incomplete!");
		}

	}

	public void addPupil(Student s) {
		students.add(s);
	}

	public String getID() {
		return this.classID;
	}

	public Student findStudent(String ID) { // Prüft ob ID aus
											// Register.txt mit der eines
											// Schuelers übereinstimmt

		Student s = null;

		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getID().equals(ID)) {
				s = students.get(i);
			}
		}

		return s;
	}

	public void listEntrysOfStudents() { // listet alle eintraege aller Schüler
		for (int i = 1; i < students.size(); i++) {
			students.get(i).getEntrys();
			System.out.println("Entrys listed");
		}
	}

	public void listStudents() throws NullPointerException { // Listet alle Studenten einer Klasse auf
		for (int i = 1; i < students.size(); i++) {

			System.out.println("Schüler: " + students.get(i).getID()
					+ " " + students.get(i).getName());

		}
	}

	public boolean areThereEntrys() {// prüft ob min 1 Schüler min 1
											// eintrag hat, gibt False zurück
											// wenn es keine Eintraege gibt

		boolean b = false;
		for (int i = 1; i < students.size(); i++) {
			if (!students.get(i).checkEntrys()) {
				b = true;
			}

		}
		return b;
	}

	public Student arrayInfo(int i) {

		return students.get(i);
	}

	public int sizeInfo() {
		return students.size();
	}

}
