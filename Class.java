package de.dhbw.vs.fpr.register;

import java.util.ArrayList;

/**
 * Class for one class of students.
 * 
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */

public class Class {

	private final String classID;
	private ArrayList<Student> studentsArray = new ArrayList<>();

	/**
	 * Constructor number 1. constructs class with classID from param.
	 * 
	 * @param ID
	 *            Is the ID for the class.
	 */
	public Class(String ID) {
		classID = ID;
	}

	/**
	 * Constructor number 2. constructs class with classID and all the students.
	 * from param c from those studentsArray.
	 * 
	 * @param class c
	 *            The class to be copied.
	 */
	public Class(Class c) {
		this.classID = c.classID;
		this.studentsArray = c.studentsArray;
	}

	/**
	 * Creates a class with Students of the studentsList. If the dataset is
	 * incomplete, there will be an error message.
	 * 
	 * @param studentList
	 *            Students of the class.
	 */

	public void createClass(String studentList) {
		String allStudents[] = studentList.substring(3).split(";");

		Student tmpStudent = new Student(allStudents[1], allStudents[2],
				allStudents[3].substring(0, 2), this.classID);
		studentsArray.add(tmpStudent);

		/**
		 * Test if array is divisible by three and if there is a rest of one for
		 * the ID
		 */
		// Ich hab keine Ahnung ob das hier javadoc korrekt ausführt.
		if (allStudents.length % 3 == 1) {

			for (int i = 1; i < allStudents.length; i = i + 3) {

				Student s = new Student(allStudents[i], allStudents[i + 1],
						allStudents[i + 2].substring(0, 2), this.classID);
				// Student is created, Index 0 of the array is reserved for the
				// ID, afterwards are always 3 arrayplaces with name, first name
				// and student ID

				studentsArray.add(s);

			}
		} else {
			System.out.println("Dataset is incomplete!");
		}

	}

	/**
	 * Adds the studentsArray to one student.
	 * 
	 * @param s
	 *            S is added to a student.
	 */

	public void addStudent(Student s) {
		studentsArray.add(s);
	}

	/**
	 * Gets the ID from a class.
	 * 
	 * @return The classID
	 */
	public String getID() {
		return this.classID;
	}

	/**
	 * Checks if the ID form Register.txt is the same as the ID from one
	 * student.
	 * 
	 * @param ID
	 *            ID from one student.
	 * @return Student s if the student was found, else returns null.
	 */

	public Student findStudent(String ID) {

		Student s = null;

		for (Student studentsArray1 : studentsArray) {
			if (studentsArray1.getID().equals(ID)) {
				s = studentsArray1;
			}
		}

		return s;
	}

	/**
	 * Lists entries of all the students and prints them on the console.
	 */
	public void listEntrysOfStudents() {
		for (int i = 1; i < studentsArray.size(); i++) {
			studentsArray.get(i).getEntries();
			System.out.println("Einträge ausgegeben");
		}
	}

	/**
	 * Lists all students of one class and prints them on the console.
	 */
	public void listStudents() {
		for (int i = 1; i < studentsArray.size(); i++) {

			System.out.println("Schüler " + studentsArray.get(i).getID() + " "
					+ studentsArray.get(i).getName() + " "
					+ studentsArray.get(i).getFirstName());

		}
	}

	/**
	 * Checks if at least there is one student with minimum one entry.
	 * 
	 * @return true or false, when there are entries or not.
	 */
	public boolean areThereEntries() {
		boolean b = false;
		for (int i = 1; i < studentsArray.size(); i++) {
			if (!studentsArray.get(i).hasNoEntries()) {
				b = true;
			}

		}
		return b;
	}

	/**
	 * Gets one student on position i.
	 * 
	 * @param i
	 * @return Information of one student on position i.
	 */
	public Student arrayInfo(int i) {

		return studentsArray.get(i);
	}

	/**
	 * Gives information how many students are in the studentsArray.
	 * 
	 * @return The size of the studentsArray.
	 */
	public int sizeInfo() {
		return studentsArray.size();
	}

}
