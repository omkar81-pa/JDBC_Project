package test;
import java.util.*;
import java.io.*;
import java.sql.*;
public class DBCon7 {
public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	try(s;){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
		
		System.out.println("Enter id to retrive Image:");
		String id = s.nextLine();
		PreparedStatement ps = con.prepareStatement("select * from StreamTab67 where id=?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Blob b = rs.getBlob(2);
			byte by[] = b.getBytes(1, (int)b.length());
			System.out.println("Enter the fPath&fName to store image(Destination):");
			
			File f = new File(s.nextLine());
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(by);
			System.out.println("Image retrieved successfully.....");
			fos.close();
		}else {
			System.out.println("Invalid id......");
		}
	}catch(Exception e) {
		System.out.println(e.toString());
	}
}
}
