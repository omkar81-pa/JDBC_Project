package test;
import java.util.*;
import java.sql.*;
public class DBCon_10 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try(s;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
			System.out.println("Commit-status: "+con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("Commit-status:"+con.getAutoCommit());
			
			PreparedStatement ps1 = con.prepareStatement("Select * from Bank67 where accno = ?");
			PreparedStatement ps2 = con.prepareStatement("update Bank67 set bal = bal + ? where accno = ?");
			
			Savepoint sp = con.setSavepoint();
			System.out.println("Enter the HomeAccNo:");
			long hAccNo = s.nextLong();
			ps1.setLong(1, hAccNo);
			ResultSet rs1 = ps1.executeQuery();
			
			if(rs1.next()) {
				float bl =rs1.getFloat(3);
				System.out.println("Available bal : "+bl);
				System.out.println("Enter the benifiecieryAccNo:");
				Long bAccNo = s.nextLong();
				ps1.setLong(1, bAccNo);
				ResultSet rs2 = ps1.executeQuery();
				
				if(rs2.next()) {
					System.out.println("Enter the amount to be transferred:");
					float amt = s.nextFloat();
					
					if(amt <= bl) {
						ps2.setFloat(1, -amt);
						ps2.setLong(2, hAccNo);
						int i = ps2.executeUpdate();
						
						ps2.setFloat(1, +amt);
						ps2.setLong(2, bAccNo);
						int j = ps2.executeUpdate();
						
						if(i == 1 && j == 1) {
							System.out.println("Transaction is Successfull.....");
							con.commit();
						}else {
							System.out.println("Transaction failed......");
							con.rollback();
						}
					}else {
						System.out.println("Insufficient fund.....");
					}
				}else {
					System.out.println("Invalid benifiecieryAccNo.....");
				}
			}else {
				System.out.println("Invalid homeAccNo.....");
			}
			con.close();
		}catch(SQLException e) {
			if(e.getMessage().contains("invalid homeAccNo")) {
				System.out.println("Invalid hAccNo.....");
			}else if(e.getMessage().contains("invalid benifiecieryAccNo")) {
				System.out.println("Invalid bAccNo.....");
			}else if(e.getMessage().contains("Insufficient fund")) {
				System.out.println("Insufficient Fund...");
			}else {
				System.out.println("Error:"+e.toString());
			}
		}catch(Exception e) {
			System.out.println("Error: "+e.toString());
		}
	}
}
