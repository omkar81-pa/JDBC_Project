package test;
import java.util.*;
import java.sql.*;
public class Employee67 {

	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
	try (sc;){
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
		
		PreparedStatement ps1 = con.prepareStatement("INSERT INTO Employee67 VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		PreparedStatement ps2 = con.prepareStatement("Select * from Employee67");
		
		PreparedStatement ps3 = con.prepareStatement("select * from Employee67 where eid=?");
		
		PreparedStatement ps4 = con.prepareStatement("update Employee67 set bsal=?,har=?,da=?,totsal=? where eid=?");
		
		PreparedStatement ps5 = con.prepareStatement("delete from Employee67 where eid=?");
		while(true) {
			System.out.println("==========Choice=========");
			
			System.out.println("\t1.AddEmployees"
								+ "\n\t2.ViewEmployees"
								+ "\n\t3.ViewEmployeesByCode"
								+ "\n\t4.UpdateEmployeesDetailsByCode"
								+ "\n\t5.DeleteEmployeeDetaileByCode"
								+ "\n\t6.Exit");
			System.out.println("Enter your Choice:");
			
			switch(Integer.parseInt(sc.nextLine())) {
			
			case 1:
				  System.out.println("-------------Enter Employee details---------");
				  
				  System.out.println("Enter Employee Id:");
				  int eId = Integer.parseInt(sc.nextLine());
				  
				  System.out.println("Enter Employee Name:");
				  String eName = sc.nextLine();
						  
				  System.out.println("Enter Employee Desg:");
				  String eDesg = sc.nextLine();
				  
				  System.out.println("Enter Employee Basic-Salary:");
				  float bSal = Float.parseFloat(sc.nextLine());
				  
				  float Ehra = 0.93f * bSal;
				  float Eda = 0.63f * bSal;
				  float totsal = bSal+Ehra+Eda;
				  
				  ps1.setInt(1, eId);
				  ps1.setString(2, eName);
				  ps1.setString(3, eDesg);
				  ps1.setFloat(4, bSal);
				  ps1.setFloat(5, Ehra);
				  ps1.setFloat(6, Eda);
				  ps1.setFloat(7, totsal);
				  
				  int k1 = ps1.executeUpdate();
				  
				  if(k1>0) {
					  System.out.println("Employee Detail Addad Successfully.......");
				  }
				break;
			case 2:
				  ResultSet rs1 = ps2.executeQuery();
				  System.out.println("----------Employee Details--------");
				  System.out.println();
				  
				  
				  while(rs1.next()) {
					  System.out.println( rs1.getInt(1)+"\t"
							  			+ rs1.getString(2)+"\t"
							  			+ rs1.getString(3)+"\t"
							  			+ rs1.getFloat(4)+"\t"
							  			+ rs1.getFloat(5)+"\t"
							  			+ rs1.getFloat(6)+"\t"
							  			+ rs1.getFloat(7));
				  }
				break;
			case 3:
				  System.out.println("Enter Employee Id for displsy details.....");
				  int eId1 = Integer.parseInt(sc.nextLine());
				  
				  ps3.setInt(1, eId1);
				  ResultSet rs2 = ps3.executeQuery();
				  
				  if(rs2.next()) {
					  System.out.println( rs2.getInt(1)+"\t"
							  			+ rs2.getString(2)+"\t"
							  			+ rs2.getString(3)+"\t"
							  			+ rs2.getFloat(4)+"\t"
							  			+ rs2.getFloat(5)+"\t"
							  			+ rs2.getFloat(6)+"\t"
							  			+ rs2.getFloat(7));
				  }else {
					  System.out.println("Invalid Employee Id.........");
				  }
				break;
			case 4:
				 System.out.println("Enter Employee Id to update Employee Basic-Salary:");
				 int eId2 = Integer.parseInt(sc.nextLine());
				 ps3.setInt(1, eId2);
				 ResultSet rs3 = ps3.executeQuery();
				 
				 if(rs3.next()) {
					 System.out.println("Employee Old Salary:"+rs3.getFloat(4));
					 System.out.println("Enter new Basic-Salary:");
					 float bSalary = Float.parseFloat(sc.nextLine());
					  
					  float hra = 0.93f * bSalary;
					  float da = 0.63f * bSalary;
					  float totSal = bSalary+hra+da;
					 
					  ps4.setFloat(1, bSalary);
					  ps4.setFloat(2, hra);
					  ps4.setFloat(3, da);
					  ps4.setFloat(4, totSal);
					  ps4.setInt(5, eId2);
					  
					 int k2 = ps4.executeUpdate();
					 
					 if(k2>0) {
						 System.out.println("Update Salary........");
					 }
				 }else {
					 System.out.println("Invalid Employee Id.......");
				 }
				break;
			case 5:
				  System.out.println("Enter Employee Id for delete Employee Details..........");
				  
				  int eId3 = Integer.parseInt(sc.nextLine());
				  
				  ps3.setInt(1, eId3);
				  ResultSet rs4 = ps3.executeQuery();
				  
				  if(rs4.next()) {
					  ps5.setInt(1, eId3);
					  int k3 = ps5.executeUpdate();
					  if(k3>0) {
						  System.out.println("Employee Detail deleted successfullay......");
					  }else {
						  System.out.println("Invalid Employee Id..........");
					  }
				  }
				break;
			case 6:
				System.out.println("Operation Stopped........");
				System.exit(0);
				
			default:
				System.out.println("Ivalid Choice..........");
			}
		}
	} catch (Exception e) {
		System.out.println(e.toString());
	}

	}

}
