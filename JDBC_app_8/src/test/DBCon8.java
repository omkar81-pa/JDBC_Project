package test;
import java.util.*;
import java.sql.*;
public class DBCon8 {
public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	try(s;){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
		
		CallableStatement cs = con.prepareCall("{call CreateAccount67(?,?,?,?,?,?,?,?,?)}");
		
		System.out.println("Enter Customer AccountNumber:");
		long accNo = Long.parseLong(s.nextLine());
		System.out.println("Enter Customer Name:");
		String name = s.nextLine();
		System.out.println("Enter Customer Balance:");
		float bal = Float.parseFloat(s.nextLine());
		System.out.println("Enter Customer Account Type:");
		String accType = s.nextLine();
		System.out.println("Enter Customer City:");
		String city = s.nextLine();
		System.out.println("Enter Customer State:");
		String state = s.nextLine();
		System.out.println("Enter Customer PinCode:");
		int pinCode = Integer.parseInt(s.nextLine());
		System.out.println("Enter Customer MailId:");
		String mId = s.nextLine();
		System.out.println("Enter Customer MobileNumber:");
		long phNo = Long.parseLong(s.nextLine());
		
		cs.setLong(1, accNo);;
		cs.setString(2, name);
		cs.setFloat(3, bal);
		cs.setString(4, accType);
		cs.setString(5,city);
		cs.setString(6, state);
		cs.setInt(7, pinCode);
		cs.setString(8, mId);
		cs.setLong(9, phNo);
		
		cs.execute();
		System.out.println("Customer account created successfully...");
		cs.close();
	}catch(Exception e) {
		System.out.println(e.toString());
	}
}
}
