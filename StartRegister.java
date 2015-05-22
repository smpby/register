package de.dhbw.vs.fpr.register;

public class StartRegister {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ClassRegister k1 = new ClassRegister();
		control(args[0], k1);
		/*
		 * k1.ReadData(args[0]); k1.eintragzuOrdnen(args[0]);
		 * 
		 * k1.listClasses(); // k1.findStudent("01101");
		 * k1.readTeacher(args[0]); k1.printTeachers();
		 * System.out.println(k1.authenticate());
		 */
	}

	static void control(String path, ClassRegister k1) {
		k1.ReadData(path);
		k1.eintragzuOrdnen(path);// pulls Entrys
		k1.readTeacher(path);
		Class c = null;
		do{
		boolean flag = false;
		while (flag == false)
			flag = k1.authenticate();
		k1.listClasses();
		c = null;
		c = k1.chooseClass();
		}while(c==null);
		

		}
	

}
