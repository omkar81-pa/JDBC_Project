package test;

import java.sql.*;

public class DBCon2 {
	public static void main(String[] args) {
		
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con  =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");	
		Statement stm=con.createStatement();
		ResultSet rs = stm.executeQuery("Select*from emp");
		System.out.println("------customer Deatails-------");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getString(5)+"\t"+rs.getInt(6)+"\t"+rs.getInt(7)+"\t"+rs.getInt(8));
		}
		con.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}