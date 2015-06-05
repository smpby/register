package de.dhbw.vs.fpr.register.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import de.dhbw.vs.fpr.register.ClassRegister;
import de.dhbw.vs.fpr.register.DataCorruptionException;
import de.dhbw.vs.fpr.register.Entry;

/**
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */

// To run this test use -Dpath=<pathToTheTestData> as parameter to the [J]VM

public class ClassRegisterTest {

	String path;
	ClassRegister c;
	String testClassID;
	String testStudentID;
	String testInfo;
	String testDate;
	Entry testEntry;

	/**
	 * Preparation for the tests by preparing instance variables and reading the
	 * data from the prepared test files.
	 */
	@Before
	public void setUp() {

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
	 * This test method is not only used for testing if the reading of data sets works, it
	 * also is used to initialize the data structures for the other tests.
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
		}catch (DataCorruptionException e) {
			fail("input datasets contained defective data");
		}

	}

	/**
	 * Tests if specific student with Name="Brecht" was put in the right class
	 * and given the appropriate StudentID in the reading process.
	 */
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

	/**
	 * Tests if a specific entry is read correctly from file (has fitting
	 * student, note and date).
	 * 
	 */
	@Test
	public void testReadentries() {
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

	/**
	 * Tests if an new entry is correctly put in the internal data  structures.
	 */
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

	/**
	 * Tests if a newly assembled entry is saved correctly and can be read
	 * without any changes to his data. It also deletes the entry afterwards for
	 * a new runability.
	 */
	@Test
	public void testSaveEntry() {

		// Generate new entry
		testMakeEntry();

		// write entries
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

	/**
	 * Tests the authenticate method.
	 */
	@Test
	public void testAuthenticate() {

		assertFalse("False authentification went thru",
				c.authenticate("simone", "4321"));
		assertTrue("Correct authentification data did not work",
				c.authenticate("simone", "1234"));
	}
}
