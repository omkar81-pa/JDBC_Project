package test;

import java.sql.*;

public class BookDetails {
	public static void main(String[] args) {
		
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con  =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");	
		Statement stm=con.createStatement();
		ResultSet rs = stm.executeQuery("Select*from BookDetails67");
		System.out.println("------Book Deatails-------");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getInt(4)+"\t\t"+rs.getInt(5));
		}
		con.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}