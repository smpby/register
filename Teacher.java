package de.dhbw.vs.fpr.register;

/**
 * Class to create one teacher.
 * 
 * @author Simon Bayer 5601000
 * @author Patrice Bender 117684
 * @author Vera Gögelein 9267625
 * @author Robert Leipelt 9469264
 */

public class Teacher {
	private String ID;
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;

	/**
	 * Gets the ID of a teacher.
	 * 
	 * @return ID of a teacher.
	 */
	String getID() {
		return ID;
	}

	/**
	 * Gets first name of a teacher.
	 * 
	 * @return firstName of a teacher.
	 */
	String getFirstName() {
		return firstName;
	}

	/**
	 * Gets last name of one teacher.
	 * 
	 * @return lastName of one teacher.
	 */
	String getLastName() {
		return lastName;
	}

	/**
	 * Gets user name of one teacher.
	 * 
	 * @return userName of one teacher.
	 */
	String getUserName() {
		return userName;
	}

	/**
	 * Gets password of one teacher.
	 * 
	 * @return passWord of one teacher.
	 */
	String getPassWord() {
		return passWord;
	}

	/**
	 * Sets ID of one teacher.
	 * 
	 * @param iD
	 *            Of one teacher.
	 */
	private void setID(String iD) {
		ID = iD;
	}

	/**
	 * Sets the first name of one teacher.
	 * 
	 * @param firstName
	 *            Of one teacher.
	 */
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name of one teacher.
	 * 
	 * @param lastName
	 *            Of one teacher.
	 */
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets user name of one teacher.
	 * 
	 * @param userName
	 *            Of one teacher.
	 */
	private void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Sets password of one teacher.
	 * 
	 * @param passWord
	 *            Of one teacher.
	 */
	private void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * Constructs one teacher with first name, last name, ID, user name and
	 * password form param.
	 * 
	 * @param firstName
	 *            Of one teacher.
	 *            
	 * @param lastName
	 *            Of one teacher.
	 *            
	 * @param Id
	 *            Of one teacher.
	 *            
	 * @param userName
	 *            Of one teacher.
	 *            
	 * @param passWord
	 *            Of one teacher.
	 */
	Teacher(String firstName, String lastName, String Id, String userName,
			String passWord) {
		setID(Id);
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(userName);
		setPassWord(passWord);
	}
}
