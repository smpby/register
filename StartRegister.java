/**
 * @author Simon Bayer 5601000
 * @author Patrice Bender 
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */
package de.dhbw.vs.fpr.register;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class StartRegister {

	static ClassRegister k1;
	static Scanner s1 = new Scanner(System.in);
	static Class tmpClass;
	static Student tmpStudent;
	static Entry tmpEntry;

	public static void main(String[] args) {

		k1 = new ClassRegister(args[0]);
		try {
			k1.readStudents();
			k1.readRegister();
			k1.readTeacher();

			stageZero();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Scanner Objekt als Methode ist Praktischer als immer zu Tippen
	 */
	public static String input(String text) {
		System.out.println(text);
		Scanner s = new Scanner(System.in);

		return s.nextLine();
	}

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
		}// Do as long aus authentification failed

		stageOne();
	}

	public static void stageOne() throws NullPointerException,
			FileNotFoundException {
		k1.listClassesWithEntrys();
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
					.println("Diese Klasse ist leider nicht Vorhanden! Versuchen Sie es erneut mit valider Klassennummer!");
			stageOne();
		}

		stageTwo();
	}

	public static void stageTwo() throws NullPointerException,
			FileNotFoundException {
		tmpClass.listStudents();
		String in = input("Wählen Sie einen Schüler, durch seine ID:");
		if (in.equals("R")) {
			stageOne();
		}

		try {
			tmpStudent = tmpClass.findStudent(in);
			System.out.println("Schüler " + tmpStudent.getID() + " ("
					+ tmpStudent.getVorname() + " " + tmpStudent.getName()
					+ ") der Klasse " + tmpClass.getID() + " gewählt");

		} catch (NullPointerException e) {
			System.out
					.println("Dieser Schüler ist leider nicht Vorhanden! Bitte versuchen Sie es erneut mit valider Schülernummer!");
			stageTwo();
		}

		stageThree();
	}

	public static void stageThree() throws NullPointerException,
			FileNotFoundException {
		String in = input("Einträge anzeigen = 1 || Neuen Eintrag erstellen = 2 || Zur Schülerwahl zurückkehren = R");

		if (in.equals("1") || in.equals("2") || in.equals("R")) {
			if (in.equals("R")) {
				stageTwo();
			}
			if (in.equals("1")) {
				tmpStudent.getEntrys();
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

	public static void stackEntryArray() {
		String info = input("Was hat der Schüler verbrochen?");
		String date = input("Fügen Sie bitte noch das Datum hinzu (Im Format: DD.MM.JJJJ)");
		if (validDate(date)) {
			tmpEntry = new Entry(info, date, tmpStudent.geteindeutigeID());
			tmpStudent.addEntry(tmpEntry);
		} else {
			stackEntryArray();
		}

	}

	public static boolean validDate(String date) {
		if (date == null) {
			// no input was given
			return false;
		}
		SimpleDateFormat s = new SimpleDateFormat("dd.mm.yyyy");
		s.setLenient(false);// strict match is requiered
		try {
			s.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
