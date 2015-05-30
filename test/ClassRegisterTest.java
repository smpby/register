package de.dhbw.vs.fpr.register.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.*;
import java.util.Scanner;

import org.junit.Test;

import de.dhbw.vs.fpr.register.ClassRegister;
import de.dhbw.vs.fpr.register.Entry;

public class ClassRegisterTest {
	String path = "/home/simon/workspace/Prog_funk/projectclassregister/src/de/dhbw/vs/fpr/register/testdata";
	ClassRegister c;
	String testClassID = "012";
	String testStudentID = "11";
	String testInfo = "stört unterricht NICHT";
	String testDate = "12.11.1111";
	Entry testEntry = new Entry(testInfo, testDate, testClassID + testStudentID);

	/**
	 * this test method is not only testing if the dataset reading works, it
	 * also is used to initialize the datastructures for the other tests
	 */
	@Test
	public void testReadData() {
		c = new ClassRegister(path);
		try {
			c.readStudents();
			c.readRegister();
			c.readTeacher();
		} catch (FileNotFoundException e) {
			fail("datasets not found");
		}

	}

	@Test
	public void testReadStudents() {
		String testClassID = "011";
		String testStudentID = "02";
		String testStudentName = "Brecht";

		testReadData();

		// Check for existing Class and Student
		assertNotNull(c.findClass(testClassID));
		assertNotNull(c.findClass(testClassID).findStudent(testStudentID));

		// Check for fitting Studentname
		assertTrue(c.findClass(testClassID).findStudent(testStudentID)
				.getName().contentEquals(testStudentName));
	}

	@Test
	public void testReadEntrys() {
		testReadData();

		String testClassID = "012";
		String testStudentID = "11";
		String testInfo = "stört unterricht";
		String testDate = "11.11.1111";
		Entry testEntry = new Entry(testInfo, testDate, testClassID
				+ testStudentID);

		// Check for existing Class, Student and Entry
		assertNotNull("class dose not exists", c.findClass(testClassID));
		assertNotNull("Student dos not exist", c.findClass(testClassID)
				.findStudent(testStudentID));
		assertFalse("No entry exists",
				c.findClass(testClassID).findStudent(testStudentID)
						.hasNoEntrys());

		// Check for fitting info and Date
		assertTrue(c.findClass(testClassID).findStudent(testStudentID)
				.findEntry(testEntry));
	}

	@Test
	public void testMakeEntry() {

		testReadData();

		// Check for existing Class and Student
		assertNotNull(c.findClass(testClassID));
		assertNotNull(c.findClass(testClassID).findStudent(testStudentID));

		// Check for nonexistens of the testentry
		assertFalse(c.findClass(testClassID).findStudent(testStudentID)
				.findEntry(testEntry));

		// add testentry
		c.findClass(testClassID).findStudent(testStudentID).addEntry(testEntry);

		// Check if testentry now is saved within the student
		assertTrue(c.findClass(testClassID).findStudent(testStudentID)
				.findEntry(testEntry));

		// No saving of the newly added Entry!!
	}

	@Test
	public void testSaveEntry() {

		// Generate new entry
		testMakeEntry();

		// write entrys
		try {
			c.writeEntries();
		} catch (FileNotFoundException e) {
			fail("Couldn't write register.txt file");
		}

		// reread data
		testReadData();

		// overwrite register.txt to leave it in original state
		Scanner fileScanner = new Scanner(path + "register.txt.orig");
		PrintWriter out = null;
		try {
			out = new PrintWriter(path + "/register.txt");
		} catch (IOException e) {
			fail("Restoring default test data failed - for fourther testing reset register.txt");
		}

		while (fileScanner.hasNext()) {
			out.println(fileScanner.nextLine());
		}
		out.close();
		// Check if testentry was written and read correctly (therefor is here!)
		assertTrue(c.findClass(testClassID).findStudent(testStudentID)
				.findEntry(testEntry));

	}

}
