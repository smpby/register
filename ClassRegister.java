package de.dhbw.vs.fpr.register;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassRegister {

	ArrayList<Class> theClasses = new ArrayList<Class>();

	public void ReadData() {

		try {

			StringBuilder sb = new StringBuilder();

			File myfile = new File(
					"/Users/Patrice/Documents/workspace/ClassRegister/src/de/dhbw/vs/fpr/register/student.txt");

			Scanner fileScanner = new Scanner(myfile);

			String s = new String();
			Student p;

			while (fileScanner.hasNextLine()) {
				s = fileScanner.nextLine();
				// System.out.println(s);
				sb.append(s + "\n");

			}
			// String der Textdatei wird im Array helper nach Klassen aufgeteilt
			String[] helper = sb.toString().split("<class>");

			// es werden Klassenobjekte erzeugt die die Klassenlisten speichern

			for (int i = 1; i < helper.length; i++) {

				String classID = new String(helper[i].substring(0, 3));
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
//Hallo TEEEST
	public void eintragzuOrdnen() {
		File myRegister = new File(
				"/Users/Patrice/Documents/workspace/ClassRegister/src/de/dhbw/vs/fpr/register/register.txt");
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(myRegister);
			StringBuilder sb = new StringBuilder();

			while (fileScanner.hasNextLine()) {

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

					s = findStudent(klassen[i+1].substring(0, 5));
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

		//System.out.println(s.getName());
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
}
