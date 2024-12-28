package test;
import java.sql.*;
import java.util.*;
public class DBCon3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			System.out.println("Enter Cust-PhNo to display detail: ");
			long phNo = sc.nextLong();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
			Statement stm = con.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from Customer67 where phNo="+phNo+"");
			
			if(rs.next()) {
				System.out.println(rs.getLong(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
			}else {
				System.out.println("Invalid Customer Phone No.......");
			}
			con.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
