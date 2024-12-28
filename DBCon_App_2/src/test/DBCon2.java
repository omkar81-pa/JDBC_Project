package test;

import java.sql.*;
import java.util.*;

public class DBCon2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;) {
			
			System.out.println("Enter the Cast-phNo: ");
			long phNo = Long.parseLong(sc.nextLine());
			
			System.out.println("Enter the Cust-Name: ");
			String cName = sc.nextLine();
			
			System.out.println("Enter Coust-City: ");
			String cCity = sc.nextLine();
			
			System.out.println("Enter the Cust-MailId: ");
			String mId = sc.nextLine();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con  =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");			
			Statement stm=con.createStatement();
			int k = stm.executeUpdate("insert into Customer67 values("+phNo+",'"+cName+"','"+cCity+"','"+mId+"')");
			System.out.println("The valuse k:"+k);
			
			if(k>0) {
				System.out.println("Customer details inserted Successfully.......");
			}
			ResultSet rs = stm.executeQuery("Select*from Customer67");
			System.out.println("------Book Deatails-------");
			while(rs.next()) {
				System.out.println(rs.getLong(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4));
			}
			con.close();
		}
			catch(SQLIntegrityConstraintViolationException sicve) {
				System.out.println("Customer details alredy available.....");
			
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
}
