package de.dhbw.vs.fpr.register.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.*;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.vs.fpr.register.ClassRegister;
import de.dhbw.vs.fpr.register.Entry;

/**
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */

/**
 * To run this test use -Dpath=<pathToTheTestData> as parameter to the [J]VM
 * 
 * @author simon
 *
 */
public class ClassRegisterTest {

	String path;// =
				// "/home/simon/workspace/Prog_funk/projectclassregister/src/de/dhbw/vs/fpr/register/testdata";
	;
	ClassRegister c;
	String testClassID;
	String testStudentID;
	String testInfo;
	String testDate;
	Entry testEntry;

	@Before
	public void setUp() {
		// Set up for the test, manipulating instance variables and Read Data
		testClassID = "012";
		testStudentID = "11";
		testInfo = "stört unterricht NICHT";
		testDate = "12.11.1111";
		testEntry = new Entry(testInfo, testDate, testClassID + testStudentID);
		path = System.getProperty("path");
		assertNotNull("path variable wasn't send to JVM", path);
		testReadData();
	}

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

		// Check for existing Class and Student
		assertNotNull(c.findClass(testClassID));
		assertNotNull(c.findClass(testClassID).findStudent(testStudentID));

		// Check for fitting Studentname
		assertTrue(c.findClass(testClassID).findStudent(testStudentID)
				.getName().contentEquals(testStudentName));
	}

	@Test
	public void testReadEntrys() {
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
						.hasNoEntries());

		// Check for fitting info and Date
		assertTrue(c.findClass(testClassID).findStudent(testStudentID)
				.findEntry(testEntry));
	}

	@Test
	public void testMakeEntry() {

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

		// No saving of the newly added Entry

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
		File inputFile = new File(path + "/register.txt.orig");
		Scanner fileScanner = null;
		PrintWriter out = null;
		try {
			fileScanner = new Scanner(inputFile);
			out = new PrintWriter(path + "/register.txt");
		} catch (IOException e) {
			fail("Restoring default test data failed - for fourther testing please reset register.txt manually");
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
