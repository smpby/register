package de.dhbw.vs.fpr.register;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartRegister {

	static ClassRegister k1 = new ClassRegister();
	static Scanner s1 = new Scanner(System.in);
	static Class tmpClass;
	static Student tmpStudent;
	static Entry tmpEntry;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			k1.ReadData(args[0]);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			k1.eintragzuOrdnen(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stageZero();
		try {

		} catch (NullPointerException e) {
			System.out.println("Input Error");
		}

	}

	
	public static String input(String text) { // Scanner Objekt als Methode ist
												// Praktischer als immer zu
												// Tippen
		System.out.println(text);
		Scanner s = new Scanner(System.in);

		return s.nextLine();
	}

	public static void stageZero() {

		stageOne();

	}

	public static void stageOne() throws NullPointerException {
		k1.listClassesWithEntrys();
		String in = input("Wählen Sie die Klasse");

		if (in.equals("r")) {
			stageZero();
		}

		try {
			tmpClass = new Class(k1.findClass(in));
			System.out.println("Klasse: " + tmpClass.getID());

		} catch (NullPointerException e) {
			System.out
					.println("Diese Klasse ist leider nicht Vorhanden! Versuchen Sie es Erneut!");
			stageOne();
		}

		stageTwo();
	}

	public static void stageTwo() throws NullPointerException {
		tmpClass.listStudents();
		String in = input("Wählen Sie einen Schüler");
		if (in.equals("r")) {
			stageOne();
		}

		try {
			tmpStudent = tmpClass.findStudent(in);
			System.out.println("Klasse: " + tmpClass.getID() + "Schüler: "
					+ tmpStudent.getID());

		} catch (NullPointerException e) {
			System.out
					.println("Dieser Schüler ist leider nicht Vorhanden! Versuchen Sie es Erneut!");
			stageTwo();
		}

		stageThree();
	}

	public static void stageThree() throws NullPointerException {
		System.out.println(tmpStudent.geteindeutigeID() + "    "
				+ tmpStudent.getName());
		System.out.println("");
		String in = input("View Entrys Press: 1 || Create Entry (2)");

		if (in.equals("1") || in.equals("2") || in.equals("r")) {
			if (in.equals("r")) {
				stageTwo();
			}
			if (in.equals("1")) {
				tmpStudent.getEntrys();
				stageFour();
			}
			if (in.equals("2")) {
				stackEntryArray();
				stageFour();
			}
		} else {
			System.out.println("Eingabefehler bitte Wiederholen!");
			stageThree();
		}

	}

	public static void stageFour() {
		String in = input("Programm Beenden?(1) von Neuem Beginnen?(2)");
		if (in.equals("1") || in.equals("2")) {
			if (in.equals("1")) {
				try {
					k1.writeEntries();
				    System.exit(1);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (in.equals("2")) {
				stageZero();
			}

		} else {
			System.out.println("Bitte Aktion wählen!");
			stageFour();
		}
	}
	
	public static void stackEntryArray(){
		tmpEntry = new Entry(input("Was hat der Schüler verbrochen?"), input("fügen Sie bitte noch das Datum hinzu (DD.MM.JJJJ"), tmpStudent.geteindeutigeID());
		k1.addEntryToArray(tmpEntry.getEntryString());
	}

}
