package de.dhbw.vs.fpr.register;

import java.util.ArrayList;

public class Class {

	private String classID;
	private ArrayList<Student> studentsArray = new ArrayList<Student>();

	public Class(String ID) {
		classID = ID;
	}

	public Class(Class c) {
		this.classID = c.classID;
		this.studentsArray = c.studentsArray;
	}

	public void createClass(String pupilList) {
		String allStudents[] = pupilList.substring(3).split(";");

		Student tmpStudent = new Student(allStudents[1], allStudents[2],
				allStudents[3].substring(0, 2), this.classID);
		studentsArray.add(tmpStudent);

		if (allStudents.length % 3 == 1) { // Prüfung ob der Array durch 3
											// Teilbar ist und 1 übrig bleibt
											// für die ID
			for (int i = 1; i < allStudents.length; i = i + 3) {

				Student s = new Student(allStudents[i], allStudents[i + 1],
						allStudents[i + 2].substring(0, 2), this.classID);
				// Schueler wird erzeugt, Array Index 0 ist für die ID
				// Reserviert, danach sind immer 3 nachfolgende ArrayPlätze
				// mit Name, Vorname und Schüler ID
				studentsArray.add(s);

			}
		} else {
			System.out.println("Dataset is incomplete!");
		}

	}

	public void addStudent(Student s) {
		studentsArray.add(s);
	}

	public String getID() {
		return this.classID;
	}

	public Student findStudent(String ID) { // Prüft ob ID aus
											// Register.txt mit der eines
											// Schuelers übereinstimmt

		Student s = null;

		for (int i = 0; i < studentsArray.size(); i++) {
			if (studentsArray.get(i).getID().equals(ID)) {
				s = studentsArray.get(i);
			}
		}

		return s;
	}

	public void listEntrysOfStudents() { // listet alle eintraege aller Schüler
		for (int i = 1; i < studentsArray.size(); i++) {
			studentsArray.get(i).getEntrys();
			System.out.println("Einträge ausgegeben");
		}
	}

	public void listStudents() throws NullPointerException { // Listet alle
																// Studenten
																// einer Klasse
																// auf
		for (int i = 1; i < studentsArray.size(); i++) {

			System.out.println("Schüler " + studentsArray.get(i).getID() + " "
					+ studentsArray.get(i).getName() + " "
					+ studentsArray.get(i).getVorname());

		}
	}

	public boolean areThereEntrys() {// prüft ob min 1 Schüler min 1
										// eintrag hat, gibt False zurück
										// wenn es keine Eintraege gibt

		boolean b = false;
		for (int i = 1; i < studentsArray.size(); i++) {
			if (!studentsArray.get(i).checkEntrys()) {
				b = true;
			}

		}
		return b;
	}

	public Student arrayInfo(int i) {

		return studentsArray.get(i);
	}

	public int sizeInfo() {
		return studentsArray.size();
	}

}
