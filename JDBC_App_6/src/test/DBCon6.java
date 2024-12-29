package test;
import java.util.*;
import java.io.*;
import java.sql.*;
public class DBCon6 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try(s;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
			
			PreparedStatement ps = con.prepareStatement("insert into StreamTab67 values(?,?)");
			System.out.println("Enter the id to store image:");
			String id = s.nextLine();
			
			System.out.println("Enter the fPath&fName(Source-path):");
			File f = new File(s.nextLine());
			if(f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				ps.setString(1, id);
				ps.setBinaryStream(2, fis, f.length());
				int k = ps.executeUpdate();
				
				if(k>0) {
					System.out.println("Image stored successfully......");
				}else {
					System.out.println("Invalid fPath or fname......");
				}
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}

	}

}
