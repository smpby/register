package de.dhbw.vs.fpr.register;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClassRegister {

	private String path;
	ArrayList<Class> classesArray = new ArrayList<Class>();
	ArrayList<Teacher> teacherArray = new ArrayList<Teacher>();

	/**
	 * This constructor is ok because there is no need to change the path to the
	 * datasets dynamically!
	 */
	public ClassRegister(String path) {
		this.path = path;
	}

	public void readStudents() throws FileNotFoundException {

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
			Class k = new Class(helper[i].substring(0, 3));
			// Holt ID der Klassen aus Textdatei

			k.createClass(helper[i]);
			classesArray.add(k);
		}

	}

	public void readRegister() throws FileNotFoundException {
		File myRegister = new File(path + "/register.txt");

		Scanner fileScanner = new Scanner(myRegister);
		StringBuilder sb = new StringBuilder();

		while (fileScanner.hasNextLine()) {// Hallo

			sb.append(fileScanner.nextLine() + "\n");

		}
		String[] entryArray = sb.toString().split(";"); // teilt Register.txt
														// in einzelne
														// klassenbereiche
	
		if (entryArray.length % 3 == 1) { // Wenn Rest = 1 dann ist die Datei
											// jeweils mit x einträgen à 3
											// Blöcke befüllt.

			Student s = null;
			for (int i = 0; i < entryArray.length - 1; i = i + 3) {

				s = findReference(entryArray[i + 1].substring(0, 5));
				s.addEntry(new Entry(entryArray[i + 3].trim(),
						entryArray[i + 2], entryArray[i + 1]));
				// Eintrag wird jeweiligem Schüler hinzugefügt

			}

		} else {
			System.out.println("Dataset of Register.txt is icomplete!");
		}

	}

	public Student findReference(String searchID) {

		String compareClassID = searchID.substring(0, 3); // Eindeutige
															// KlassenID wird
		// dediziert
		String comparePupilID = searchID.substring(3, 5); // Eindeutige
															// SchuelerID wird
		// dediziert

		Student s = null;

		if (findClass(compareClassID) != null) {
			Class k = findClass(compareClassID); // Ordnet Klasse einem
													// Temporären Attribut zu
			if (k.findStudent(comparePupilID) != null) {
				s = k.findStudent(comparePupilID); // Ordnent einem Temporären
													// Attribut den jeweiligen
													// Schüler zu

			} else {
				System.out.println("Leider ist Schueler mit ID "
						+ comparePupilID + " nicht vorhanden");
			}

		} else {
			System.out.println("Leider ist keine Klasse mit der ID "
					+ compareClassID + " vorhanden!!");
		}

		// System.out.println(s.getName());
		return s;

	}

	public Class findClass(String ID) {
		Class k = null;

		for (int i = 0; i < classesArray.size(); i++) {

			if (classesArray.get(i).getID().equals(ID)) { // Wenn ID mit
															// KlassenID
															// übereinstimmt
															// soll
															// dem Klassenobjekt
															// k
															// zugeordnet werden

				k = classesArray.get(i);
			}
		}

		return k;
	}
/**
	public void listClasses() { // Listet alle Klassen auf mit allen Schülern
		for (int i = 0; i < classesArray.size(); i++) {
			System.out.println("");
			System.out.println("_______Klasse " + classesArray.get(i).getID()
					+ " beginnt______");
			System.out.println("");
			classesArray.get(i).listStudents();
		}
	}
*/
	public void listClassesWithEntrys() { // Listet alle Klassen mit entrys
		for (int i = 0; i < classesArray.size(); i++) {
			if (classesArray.get(i).areThereEntrys()) {
				System.out.println("Klasse " + classesArray.get(i).getID()
						+ " hat Einträge");
			} else {
				System.out.println("Klasse " + classesArray.get(i).getID()
						+ " hat keine Einträge");
			}
		}
	}

	public void readTeacher() throws FileNotFoundException {
		String file = path + "/teacher.txt";
		File myRegister = new File(file);
		Scanner fileScanner;
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
				Teacher t = new Teacher(stringcopy[0 + i], stringcopy[1 + i],
						stringcopy[2 + i], stringcopy[3 + i],
						stringcopy[4 + i].trim());
				// Create new Instance of Teacher - with all the necessary
				// Parameters from file
				teacherArray.add(t);

			}
		}

	}

	public void showEntryforOneStudent(String eindeutigeID) {
		findReference(eindeutigeID).getEntrys();
	}

	public void writeEntries() throws FileNotFoundException {

		PrintWriter out = new PrintWriter(path + "/register.txt");

		for (int i = 0; i < classesArray.size(); i++) {

			for (int j = 0; j < classesArray.get(i).sizeInfo(); j++) {

				for (int k = 0; k < classesArray.get(i).arrayInfo(j)
						.getEntrySize(); k++) {
					out.println(classesArray.get(i).arrayInfo(j).returnEntry(k));
				}
			}
		}
		out.close();
	}

	void printTeachers() {
		System.out.println(teacherArray.toString());
	}

	/**
	 * 
	 * @param userName
	 *            of Possible User which is searched for in data
	 * @param passWord
	 * @return true for fitting username Password combination - else false
	 */
	boolean authenticate(String userName, String passWord) {
		for (int i = 0; i < teacherArray.size(); i++) {
			if (teacherArray.get(i).getUserName().equals(userName)) {
				return teacherArray.get(i).getPassWord().equals(passWord);
			}
		}
		return false;
	}

	Class chooseClass() {
		Scanner sc = new Scanner(System.in);
		System.out
				.println("Bitte wählen sie eine Klasse aus oder gehen Sie zu der Authentifizierung mit R zurück:");
		String chosenClass = sc.nextLine();
		if (chosenClass == "R") {
			return null; // no Class was choosen (and R was pressed) --> return
							// to authentication
		}
		if (findClass(chosenClass) != null)
			return findClass(chosenClass);
		else
			return chooseClass();// give Teacher the oportunity to enter a valid
									// Classname
	}
}
