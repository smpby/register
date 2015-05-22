package de.dhbw.vs.fpr.register;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.System;

public class ClassRegister {

	ArrayList<Class> theClasses = new ArrayList<Class>();
	ArrayList<Teacher> teacherArray = new ArrayList<Teacher>();

	public void ReadData(String path) {

		try {

			StringBuilder sb = new StringBuilder();

			File myfile = new File(path + "/student.txt");

			Scanner fileScanner = new Scanner(myfile);

			String s = new String();

			while (fileScanner.hasNextLine()) {
				s = fileScanner.nextLine();
				// System.out.println(s);
				sb.append(s + "\n");

			}
			// String der Textdatei wird im Array helper nach Klassen aufgeteilt
			String[] helper = sb.toString().split("<class>");

			// es werden Klassenobjekte erzeugt die die Klassenlisten speichern

			for (int i = 1; i < helper.length; i++) {
				Class k = new Class(helper[i].substring(0, 3)); // Holt ID der
																// Klassen aus
																// Textdatei

				k.createClass(helper[i]);
				theClasses.add(k);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void eintragzuOrdnen(String path) {
		File myRegister = new File(path + "/register.txt");
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(myRegister);
			StringBuilder sb = new StringBuilder();

			while (fileScanner.hasNextLine()) {// Hallo

				sb.append(fileScanner.nextLine() + "\n");

			}
			String klassen[] = sb.toString().split(";"); // teilt Register.txt
															// in einzelne
															// klassenbereiche

			if (klassen.length % 3 == 1) { // Wenn Rest = 1 dann ist die Datei
											// jeweils mit x einträgen à 3
											// Blöcke befüllt.

				Student s = null;
				for (int i = 0; i < klassen.length - 1; i = i + 3) {

					s = findStudent(klassen[i + 1].substring(0, 5));
					s.addEintrag(new Entry(klassen[i + 3], klassen[i + 2],
							klassen[i + 1])); // Eintrag
												// wird
												// jeweiligem
												// Schüler
												// hinzugefügt
				}

			} else {
				System.out.println("Dataset of Register.txt is icomplete!");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Student findStudent(String searchID) {

		String compareClassID = searchID.substring(0, 3); // Eindeutige
															// KlassenID wird
		// dediziert
		String comparePupilID = searchID.substring(3, 5); // Eindeutige
															// SchuelerID wird
		// dediziert

		Student s = null;

		if (findKlasse(compareClassID) != null) {
			Class k = findKlasse(compareClassID);
			if (k.compareID(comparePupilID) != null) {
				s = k.compareID(comparePupilID);

			} else {
				System.out.println("Leider ist Schueler mit ID "
						+ comparePupilID + " nicht vorhanden");
			}

			System.out.println("hat geklappt!");
		} else {
			System.out.println("Leider ist keine Klasse mit der ID "
					+ compareClassID + " vorhanden!!");
		}

		// System.out.println(s.getName());
		return s;

	}

	public Class findKlasse(String ID) {
		Class k = null;

		for (int i = 0; i < theClasses.size(); i++) {

			if (theClasses.get(i).getID().equals(ID)) { // Wenn ID mit KlassenID
														// übereinstimmt soll
														// dem Klassenobjekt k
														// zugeordnet werden

				k = theClasses.get(i);
			}
		}

		return k;
	}

	public void listClasses() {
		for (int i = 0; i < theClasses.size(); i++) {
			System.out.println("");
			System.out.println("_______Klasse " + theClasses.get(i).getID()
					+ " beginnt______");
			System.out.println("");
			theClasses.get(i).listStudents();
		}
	}

	public void generateEntry(String eindeutigeID) {

	}

	public void readTeacher(String path) {
		// Lehrer einlesen
		String file = path + "/teacher.txt";
		File myRegister = new File(file);
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(myRegister);

			StringBuilder sb = new StringBuilder();

			while (fileScanner.hasNextLine()) {

				sb.append(fileScanner.nextLine() + "\n");

			}
			String stringcopy[] = sb.toString().split(";"); // teilt
															// Register.txt
															// in einzelne
															// klassenbereiche

			if (stringcopy.length % 5 == 1) { // Wenn Rest = 1 dann ist die
												// Datei
												// jeweils mit x einträgen à 3
												// Blöcke befüllt.
				for (int i = 1; i < stringcopy.length; i = i + 5) {
					Teacher t = new Teacher(stringcopy[0 + i],
							stringcopy[1 + i], stringcopy[2 + i],
							stringcopy[3 + i], stringcopy[4 + i].trim());
					// Create new Instance of Teacher - with all the necessary
					// Parameters from file
					teacherArray.add(t);

				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void printTeachers() {
		System.out.println(teacherArray.toString());
	}

	boolean authenticate() {
		Scanner sc = new Scanner(System.in);
		System.out
				.println("Bitte authentifizieren Sie sich mit ihrem Nutzernamen oder beenden Sie den Programmablauf mit Q:");
		String userName = sc.nextLine();
		if (userName == "Q") {
			System.exit(0);
		}
		System.out
				.println("Bitte geben Sie jetzt Ihr Password ein (Eingabefeld bleibt nicht verdeckt):");
		String passWord = sc.nextLine();
		for (int i = 0; i < teacherArray.size(); i++) {
			if (teacherArray.get(i).getUserName().equals(userName)) {
				return teacherArray.get(i).getPassWord().equals(passWord);
			}
		}
		return false;

		/*
		 * Console terminal = System.console(); System.out.println(terminal); if
		 * (terminal != null) { String userName = terminal .readLine(
		 * "Bitte authentifizieren Sie sich mit ihrem Nutzernamen oder beenden Sie den Programmablauf mit Q:"
		 * ); if (userName == "Q") { System.exit(0); } char[] passWord =
		 * terminal .readPassword(
		 * "Bitte geben Sie jetzt Ihr Password ein (Eingabefeld bleibt verdeckt):"
		 * ); for (int i = 0; i < teacherArray.size(); i++) { if
		 * (teacherArray.get(i).getUserName() == userName &&
		 * teacherArray.get(i).getPassWord() == passWord .toString()) { return
		 * true; } }
		 * 
		 * } return false;
		 */
	}

	Class chooseClass() {
		Scanner sc = new Scanner(System.in);
		System.out
				.println("Bitte wählen sie eine Klasse aus oder gehen Sie zu der Authentifizierung mit R zurück:");
		String chosenClass = sc.nextLine();
		if (chosenClass == "R") {
			return null; // no Class was choosen (and R was pressed) --> return to authentication
		}
		if (findKlasse(chosenClass) != null)
			return findKlasse(chosenClass);
		else
			return chooseClass();//give Teacher the oportunity to enter a valid Classname
	}
}
