package de.dhbw.vs.fpr.register;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class ClassRegister to write new entries.
 * 
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */
public class ClassRegister {

	private String path;
	private ArrayList<Class> classesArray = new ArrayList<Class>();
	private ArrayList<Teacher> teacherArray = new ArrayList<Teacher>();

	/**
	 * Constructor of ClassRegister. It's ok, because there is no need to change
	 * the path of the data sets dynamically.
	 * 
	 * @param path
	 *            Is path to data sets of student, teacher an register.
	 */
	public ClassRegister(String path) {
		this.path = path;
	}

	/**
	 * Text file student.txt is read in.
	 * 
	 * @throws FileNotFoundException
	 *             When the text file student.txt is not found.
	 *             
	 * @throws DataCorruptionException
	 *             Is thrown when students could not be imported right. For further
	 *             reference see addStudents() in {@link Class}.
	 */
	public void readStudents() throws FileNotFoundException,
			DataCorruptionException {

		StringBuilder sb = new StringBuilder();

		File myfile = new File(path + "/student.txt");

		Scanner fileScanner = new Scanner(myfile,"UTF-8");

		String s = new String();

		while (fileScanner.hasNextLine()) {
			s = fileScanner.nextLine();
			sb.append(s + "\n");

		}
		// String from the text file will be divided into classes in the array
		// helper
		String[] helper = sb.toString().split("<class>");

		// Creating of class objects, to save class lists.
		for (int i = 1; i < helper.length; i++) {
			Class k = new Class(helper[i].substring(0, 3));
			// Brings ID's of the classes from the text file

			k.addStudents(helper[i]);
			classesArray.add(k);

		}
		fileScanner.close();
	}

	/**
	 * The text file register.txt is read in and the found classes are saved in
	 * classesArray. 
	 * 
	 * @throws FileNotFoundException
	 *             When the text file register.txt is not found.
	 *             
	 * @throws DataCorruptionException
	 *             When the content of register.txt is not complying with our
	 *             standards for formating.
	 */
	public void readRegister() throws FileNotFoundException,
			DataCorruptionException {
		File myRegister = new File(path + "/register.txt");

		Scanner fileScanner = new Scanner(myRegister,"UTF-8");
		StringBuilder sb = new StringBuilder();

		while (fileScanner.hasNextLine()) {

			sb.append(fileScanner.nextLine() + "\n");

		}
		String[] entryArray = sb.toString().split("<§>"); // Divides
															// register.txt
															// in separate class
															// parts

		if (entryArray.length % 3 == 1) { // If rest = 1; then the file will be
											// filled with x entries with 3
											// parts

			Student s = null;
			for (int i = 0; i < entryArray.length - 1; i = i + 3) {

				s = findReference(entryArray[i + 1].substring(0, 5));
				s.addEntry(new Entry(entryArray[i + 3].trim(),
						entryArray[i + 2], entryArray[i + 1]));
				// Entry with student is added

			}

		} else {
			throw new DataCorruptionException(
					"Dataset of Register.txt is incomplete/corrupt!");
		}

		fileScanner.close();

	}

	/**
	 * The text file teacher.txt is read in and all teachers are saved in
	 * teacherArray.
	 * 
	 * @throws FileNotFoundException
	 *             When the text teacher.txt is not found.
	 *             
	 * @throws DataCorruptionException
	 *             Is thrown if teachers are not readable from file because of
	 *             separator count mismatch.
	 */
	public void readTeacher() throws FileNotFoundException,
			DataCorruptionException {
		String file = path + "/teacher.txt";
		File myRegister = new File(file);
		Scanner fileScanner=new Scanner(myRegister,"UTF-8");

		StringBuilder sb = new StringBuilder();

		while (fileScanner.hasNextLine()) {

			sb.append(fileScanner.nextLine() + "\n");

		}
		String stringcopy[] = sb.toString().split("<§>"); // Divides teacher.txt
															// in separate class
															// parts

		if (stringcopy.length % 5 == 1) { // If rest = 1; then the file will be
											// filled with x entries with 3
											// parts

			for (int i = 1; i < stringcopy.length; i = i + 5) {
				Teacher t = new Teacher(stringcopy[0 + i], stringcopy[1 + i],
						stringcopy[2 + i], stringcopy[3 + i],
						stringcopy[4 + i].trim());
				// Creates new Instance of Teacher, with all the necessary
				// Parameters from file
				teacherArray.add(t);

			}
		} else {
			throw new DataCorruptionException(
					"Dataset Teacher.txt is incomplete/corrupt!");
		}

		fileScanner.close();

	}

	/**
	 * Finds student to an ID.
	 * 
	 * @param searchID
	 *            ID of one student.
	 *            
	 * @return Student s, if this is the student with the right ID.
	 */
	public Student findReference(String searchID) {

		String compareClassID = searchID.substring(0, 3); // Certain definitely
															// class ID

		String compareStudentID = searchID.substring(3, 5); // Certain
															// definitely
															// student ID

		Student s = null;

		if (findClass(compareClassID) != null) {
			Class k = findClass(compareClassID); // Assigns class to an
													// temporary attribute

			if (k.findStudent(compareStudentID) != null) {
				s = k.findStudent(compareStudentID); // Assigns temporary
														// attribute to a student

			} else {
				System.out.println("Student with ID  "
						+ compareStudentID + " does not exist");
			}

		} else {
			System.out.println("Class with ID "
					+ compareClassID + " does not exist");
		}

		return s;

	}

	/**
	 * Finds class with the ID from param.
	 * 
	 * @param ID
	 *            Of one class.
	 *            
	 * @return Class with the ID.
	 */
	public Class findClass(String ID) {
		Class k = null;

		for (int i = 0; i < classesArray.size(); i++) {

			if (classesArray.get(i).getID().equals(ID)) { // If the ID is the
															// same as the class
															// ID, then it
															// should be
															// assigned to the
															// class object

				k = classesArray.get(i);
			}
		}

		return k;
	}

	/**
	 * Lists all classes with entries.
	 */
	public void listClassesWithEntries() {
		for (int i = 0; i < classesArray.size(); i++) {
			if (classesArray.get(i).areThereEntries()) {
				System.out.println("Klasse " + classesArray.get(i).getID()
						+ " hat Einträge");
			} else {
				System.out.println("Klasse " + classesArray.get(i).getID()
						+ " hat keine Einträge");
			}
		}
	}

	/**
	 * Show entry for one student, with an explicit ID.
	 * 
	 * @param explicitID
	 *            Combination of class and student ID.
	 */
	public void showEntryforOneStudent(String explicitID) {
		findReference(explicitID).getEntries();
	}

	/**
	 * Writes entries for students in the text file register.
	 * 
	 * @throws FileNotFoundException
	 *             When the text file register.txt is not found.
	 */
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

	/**
	 * Authenticates one user with user name and password.
	 * 
	 * @param userName
	 *            Of possible user.
	 *            
	 * @param passWord
	 *            Of possible user.
	 *            
	 * @return true for fitting user name password combination, else false.
	 */
	public boolean authenticate(String userName, String passWord) {
		for (int i = 0; i < teacherArray.size(); i++) {
			if (teacherArray.get(i).getUserName().equals(userName)) {
				return teacherArray.get(i).getPassWord().equals(passWord);
			}
		}
		return false;
	}

	/**
	 * User can choose one class. User can go one step backwards by pressing R.
	 * 
	 * @return null, if no class was chosen and R was pressed or returns the
	 *         class that was chosen.
	 */
	Class chooseClass() {
		Scanner sc = new Scanner(System.in,"UTF-8");
		System.out
				.println("Bitte wählen sie eine Klasse aus oder gehen Sie zu der Authentifizierung mit R zurück:");
		String chosenClass = sc.nextLine();

		sc.close();

		if (chosenClass == "R") {
			return null; // no Class was chosen and R was pressed it returns
							// to authentication
		}
		if (findClass(chosenClass) != null)
			return findClass(chosenClass);
		else
			return chooseClass();// gives the teacher the opportunity to enter a
									// valid class name

	}

}
