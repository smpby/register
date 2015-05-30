/**
 * @author Simon Bayer 
 * @author Patrice Bender 
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */
package de.dhbw.vs.fpr.register;

import java.util.ArrayList;

public class Class {

	private String classID;
	private ArrayList<Student> studentsArray = new ArrayList<Student>();

	/**
	 * Constructor number 1. Constructs class with classID from param.
	 * 
	 * @param ID
	 *            Is the ID for the class.
	 */
	public Class(String ID) {
		classID = ID;
	}

	/**
	 * Constructor number 2. Constructs class with classID and all the students
	 * from param c from those studentsArray.
	 * 
	 * @param c
	 *            The class to be copied.
	 */
	public Class(Class c) {
		this.classID = c.classID;
		this.studentsArray = c.studentsArray;
	}

	/**
	 * Creates a class with Students of the pupillist. If the dataset ist
	 * incomplete there will be an error message.
	 * 
	 * @param pupilList
	 *            Students of the class.
	 */
	public void createClass(String pupilList) {
		String allStudents[] = pupilList.substring(3).split(";");

		Student tmpStudent = new Student(allStudents[1], allStudents[2],
				allStudents[3].substring(0, 2), this.classID);
		studentsArray.add(tmpStudent);

		if (allStudents.length % 3 == 1) { // Test if array is dividable by
											// three and if there is a rest of
											// one for the ID

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
	 * Adds the studentarray to one student.
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
	 * @return Student s.
	 */
	public Student findStudent(String ID) {

		Student s = null;

		for (int i = 0; i < studentsArray.size(); i++) {
			if (studentsArray.get(i).getID().equals(ID)) {
				s = studentsArray.get(i);
			}
		}

		return s;
	}

	/**
	 * Lists entries of all the students and prints them on the console.
	 */
	public void listEntrysOfStudents() {
		for (int i = 1; i < studentsArray.size(); i++) {
			studentsArray.get(i).getEntrys();
			System.out.println("Einträge ausgegeben");
		}
	}

	/**
	 * Lists all students of one class and prints them on the console.
	 * 
	 * @throws NullPointerException //was macht hier die NullPointerException?
	 */
	public void listStudents() throws NullPointerException {
		for (int i = 1; i < studentsArray.size(); i++) {

			System.out.println("Schüler " + studentsArray.get(i).getID() + " "
					+ studentsArray.get(i).getName() + " "
					+ studentsArray.get(i).getVorname());

		}
	}
/**
 * Checks if at least there is one student with minimum one entry.
 * @return true or false
 */
	public boolean areThereEntrys() {
		boolean b = false;
		for (int i = 1; i < studentsArray.size(); i++) {
			if (!studentsArray.get(i).hasNoEntrys()) {
				b = true;
			}

		}
		return b;
	}
/**
 * Gets information of one student.
 * @param i 
 * @return Information of one student i.
 */
	public Student arrayInfo(int i) {

		return studentsArray.get(i);
	}
/**
 * Gives information how many students are in the studentsArray.
 * @return The size of the studentsArray.
 */
	public int sizeInfo() {
		return studentsArray.size();
	}

}
