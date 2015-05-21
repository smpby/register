package de.dhbw.vs.fpr.register;

import java.io.FileNotFoundException;

public class StartRegister {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassRegister k1 = new ClassRegister();
	
			try {
				k1.ReadData();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		try {
			k1.eintragzuOrdnen();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		k1.listClassesWithEntrys();
		// k1.findStudent("01101");

	}

}
