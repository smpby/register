package de.dhbw.vs.fpr.register.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import de.dhbw.vs.fpr.register.ClassRegister;

public class ClassRegisterTest {
	ClassRegister c;

	/**
	 * this test method is not only testing if the dataset reading works, it
	 * also is used to initialize the datastructures for the other tests
	 */
	@Test
	public void testReadData() {
		String path = "/home/simon/workspace/Prog_funk/projectclassregister/src/de/dhbw/vs/fpr/register/testdata";
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

		assertNotNull(c.findClass(testClassID));
		assertNotNull(c.findClass(testClassID).findStudent(testStudentID));
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

		assertNotNull(c.findClass(testClassID));
		assertNotNull(c.findClass(testClassID).findStudent(testStudentID));
		assertNotNull(c.findClass(testClassID).findStudent(testStudentID)
				.getEntry(0));
		assertTrue(c.findClass(testClassID).findStudent(testStudentID)
				.getEntry(0).getNotiz().contentEquals(testInfo));
		assertTrue(c.findClass(testClassID).findStudent(testStudentID)
				.getEntry(0).getDatum().contentEquals(testDate));
	}

}
