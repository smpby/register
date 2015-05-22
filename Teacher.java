package de.dhbw.vs.fpr.register;

public class Teacher {
	private String ID;
	private String firstName;
	private String lastName;
	private String userName;
	private String passWord;

	String getID() {
		return ID;
	}

	String getFirstName() {
		return firstName;
	}

	String getLastName() {
		return lastName;
	}

	String getUserName() {
		return userName;
	}

	String getPassWord() {
		return passWord;
	}

	private void setID(String iD) {
		ID = iD;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private void setUserName(String userName) {
		this.userName = userName;
	}

	private void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	Teacher( String firstName, String lastName,String Id, String userName,
			String passWord) {
		setID(Id);
		setFirstName(firstName);
		setLastName(lastName);
		setUserName(userName);
		setPassWord(passWord);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ";"+getFirstName()+";"+getLastName()+";"+getID()+";"+getUserName()+";"+getPassWord();
	}
}
