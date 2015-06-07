package de.dhbw.vs.fpr.register;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Class StartRegister starts and controls the programflow.
 * 
 * If mayor errors occur the program exits:
 * 
 * 1 * for missing parameters.
 * 
 * 2 * for missing data sets.
 * 
 * 3 * for corrupted data sets.
 * 
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */

public class StartRegister {

	private static ClassRegister k1;
	private static Scanner s1 = new Scanner(System.in,"UTF-8");
	private static Class tmpClass;
	private static Student tmpStudent;
	private static Entry tmpEntry;

	/**
	 * The main method is where the text files are read.
	 * 
	 * @param args
	 *            [0] is the path to the used data sets.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out
					.println("Error: No path parameter was given. Please specify the path to the datasets necassary to execute StartRegister. ");
			System.exit(1);
		}
		k1 = new ClassRegister(args[0]);
		try {
			k1.readStudents();
			k1.readRegister();
			k1.readTeacher();

			stageZero();

		} catch (FileNotFoundException e1) {
			System.out.println("Error: One of the datasets is missing. Exiting program. Dump:");
			e1.printStackTrace();
			System.exit(2);
		} catch (DataCorruptionException e) {
			System.out
					.println("Error: One of the datasets has incorrect content. Exiting program. Dump:");
			e.printStackTrace();
			System.exit(3);
		}
		s1.close();
		System.exit(0);
		// Everything went smooth.

	}

	/**
	 * Create of an scanner object as an method, because it's more practicable.
	 * Creates an scanner object.
	 * 
	 * @param text
	 *            To be printed before scanning.
	 * 
	 * @return Scanned input.
	 */
	public static String input(String text) {
		System.out.println(text);
		return s1.nextLine();
	}

	/**
	 * Authentication of one user with his/her user name and the password. User
	 * can quit the program by pressing Q.
	 * 
	 * @throws FileNotFoundException
	 *             When writeEntries failed.
	 */
	public static void stageZero() throws FileNotFoundException {
		boolean notauthenticated = true;
		while (notauthenticated) {
			String userName = input("Bitte authentifizieren Sie sich mit ihrem Nutzernamen oder beenden Sie den Programmablauf mit Q:");
			if (userName.equals("Q")) {
				k1.writeEntries();
				System.exit(0);
			}

			String passWord = input("Bitte geben Sie jetzt Ihr Password ein (Eingabefeld nicht verdeckt):");
			notauthenticated = !k1.authenticate(userName, passWord);
			if(notauthenticated){
				System.out.println("Your password username combination is incorrect! Please try again:");
			}
		}// Do as long as authentication failed
		System.out.println("Sie haben sich erfolgreich authentifiziert.");
		stageOne();
	}

	/**
	 * User can choose the class, by giving the ID of the class. User can go one
	 * step backwards, by pressing R.
	 * 
	 * @throws FileNotFoundException
	 *             When stageZero failed.
	 */
	public static void stageOne() throws FileNotFoundException {
		k1.listClassesWithEntries();
		String in = input("Wählen Sie die Klasse, durch ihre ID:");

		if (in.equals("R")) {
			stageZero();
		}

		try {
			tmpClass = new Class(k1.findClass(in));
			System.out.println("Klasse  " + tmpClass.getID()
					+ " gewählt. Schüler sind:");

		} catch (NullPointerException e) {
			System.out
					.println("This class does not exist. Please try again with valid classID.");
			stageOne();
		}

		stageTwo();
	}

	/**
	 * User can choose one student, by giving the ID of them. User can go one
	 * step backwards, by pressing R.
	 * 
	 * @throws FileNotFoundException
	 *             When stageZero failed.
	 */
	public static void stageTwo() throws FileNotFoundException {
		tmpClass.listStudents();
		String in = input("Wählen Sie einen Schüler, durch seine ID:");
		if (in.equals("R")) {
			stageOne();
		}

		try {
			tmpStudent = tmpClass.findStudent(in);
			System.out.println("Schüler " + tmpStudent.getID() + " ("
					+ tmpStudent.getFirstName() + " " + tmpStudent.getName()
					+ ") der Klasse " + tmpClass.getID() + " gewählt");

		} catch (NullPointerException e) {
			System.out
					.println("This student does not exist. Please try again with valid studentID.");
			stageTwo();
		}

		stageThree();
	}

	/**
	 * User can choose between "Einträge anzeigen" by pressing 1,
	 * "Neuen Eintrag erstellen" by pressing 2 and
	 * "Zur Schülerwahl zurückkehren" by pressing R.
	 * 
	 * @throws FileNotFoundException
	 *             When stageZero failed.
	 */
	public static void stageThree() throws FileNotFoundException {
		String in = input("Einträge anzeigen = 1 || Neuen Eintrag erstellen = 2 || Zur Schülerwahl zurückkehren = R");

		if (in.equals("1") || in.equals("2") || in.equals("R")) {
			if (in.equals("R")) {
				stageTwo();
			}
			if (in.equals("1")) {
				tmpStudent.getEntries();
				stageThree();
			}
			if (in.equals("2")) {
				stackEntryArray();
				stageThree();
			}
		} else {
			System.out.println("Eingabe fehlerhaft. Bitte erneut versuchen!");
			stageThree();
		}

	}

	/**
	 * User can create an entry of one student into the digital class register.
	 * User have to write what the student did during the lesson and the
	 * belonging date of the day.
	 */
	public static void stackEntryArray() {
		String info = input("Was hat der Schüler verbrochen?");
		String date = input("Fügen Sie bitte noch das Datum hinzu (Im Format: DD.MM.JJJJ)");
		if (validDate(date)) {
			tmpEntry = new Entry(info, date, tmpStudent.getExplicitID());
			tmpStudent.addEntry(tmpEntry);
		} else {
			System.out.println("The entered date was invalid. Please try again with correct data:");
			stackEntryArray();
		}

	}

	/**
	 * Checks if there is one date giving and if this is in the right format.
	 * 
	 * @param date
	 *            Is the date of one entry.
	 * 
	 * @return false, if there was no date giving by the user and/or it's in the
	 *         wrong format. True, if there is a date giving in the right
	 *         format.
	 */
	public static boolean validDate(String date) {
		if (date == null) {
			// no input date was given by the user
			return false;
		}
		SimpleDateFormat s = new SimpleDateFormat("dd.mm.yyyy");
		s.setLenient(false);// strict match is required
		try {
			s.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
