package test;
import java.util.*;
import java.sql.*;
public class DBCon11 {
public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	try(s;){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
		Statement stm = con.createStatement();
		System.out.println("------Add Customer to Bank667-----");
		System.out.println("Enter the cust-accNo:");
		Long accNo = Long.parseLong(s.nextLine());
		System.out.println("Enter Customer Name:");
		String name = s.nextLine();
		System.out.println("Enter Cust-Bal:");
		float bal = Float.parseFloat(s.nextLine());
		System.out.println("Enter Cust-accType:");
		String accType = s.nextLine();
		
		stm.addBatch("insert into Bank67 values("+accNo+",'"+name+"',"+bal+",'"+accType+"')");
		
		System.out.println("-----Delete product details based on code(Product67)-------");
		System.out.println("Enter the product code to delete product details:");
		String pCode = s.nextLine();
		
		stm.addBatch("delete from Product67 where code = '"+pCode+"'");
		int k[] = stm.executeBatch();
		for(int i : k) {
			System.out.println("Query executed : "+i);
		}
		stm.clearBatch(); 
		con.close();
	}catch(Exception e) {
		System.out.println(e.toString());
	}
}
}
