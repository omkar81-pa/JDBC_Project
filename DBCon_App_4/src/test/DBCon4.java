package test;
import java.util.*;
import java.sql.*;
public class DBCon4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
			
			PreparedStatement ps1 = con.prepareStatement("insert into Product67 values(?,?,?,?)");
			
			PreparedStatement ps2 = con.prepareStatement("select * from Product67");
			
			PreparedStatement ps3 = con.prepareStatement("select * from Product67 where code =?");
			
			PreparedStatement ps4 = con.prepareStatement("update Product67 set prise=?,qty=qty+? where code=?");
			
			PreparedStatement ps5 = con.prepareStatement("delete from Product67 where code=?");
			while(true) {
				System.out.println("=======Choice=======");
				System.out.println("\t1.AddProduct"
							+ "\n\t2.ViewAllProduct"
							+ "\n\t3.ViewProductByCode"
							+ "\n\t4.UpdateProductByCode(price-qty)"
							+ "\n\t5.DeleteProdectByCode"
							+ "\n\t6.Exit");
				System.out.println("Enter your Choice:");
				
				switch(Integer.parseInt(sc.nextLine())) {
				case 1:
					  System.out.println("------Enter Product Details-------");
					  System.out.println("Enter the Prod-Code:");
					  String pCode = sc.nextLine();					
					  System.out.println("Enter the Prod-Name:");
					  String pName = sc.nextLine();
					  System.out.println("Enter the Prod-Price:");
					  float pPrice = Float.parseFloat(sc.nextLine());
					  System.out.println("Enter the Prod-Qty");
					  int pQty = Integer.parseInt(sc.nextLine());
					  
					  ps1.setString(1, pCode);
					  ps1.setString(2, pName);
					  ps1.setFloat(3, pPrice);
					  ps1.setInt(4, pQty);
					  
					  int k1 = ps1.executeUpdate();
					  if(k1>0) {
						  System.out.println("Product Added Successfully......");
					  }
					  break;
				case 2:
					  ResultSet rs1 = ps2.executeQuery();
					  System.out.println("--------Product Details---------");
					  System.out.println("CODE\tNAME\tPRICE\tQTY");
					  System.out.println();
					  
					  while(rs1.next()) {
						  System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
					  }
					break;
				case 3:
					  System.out.println("Enter the Prod-Code to display Product details:");
					  String pCode2 = sc.nextLine();
					  
					  ps3.setString(1,pCode2);
					  ResultSet rs2 = ps3.executeQuery() ;
					  
					  if(rs2.next()) {
						  System.out.println(rs2.getString(1)+"\t"+rs2.getString(2)+"\t"+rs2.getFloat(3)+"\t"+rs2.getInt(4));
					  }
					break;
				case 4:
					  System.out.println("Enter product code to update price and qty:");
					  String pCode3 = sc.nextLine();
					  ps3.setString(1, pCode3);
					  
					  ResultSet rs3 = ps3.executeQuery();
					  
					  if(rs3.next()) {
						  System.out.println("Old price of product:"+rs3.getFloat(3));
						  System.out.println("Enter tne new price for product:");
						  float nPrice = Float.parseFloat(sc.nextLine());
						  System.out.println("Existing Product qty:"+rs3.getInt(4));
						  System.out.println("Enter the new qty for product:");
						  int nQty = Integer.parseInt(sc.nextLine());
						  
						  ps4.setFloat(1, nPrice);
						  ps4.setInt(2, nQty);
						  ps4.setString(3, pCode3);
						  
						  int k2= ps4.executeUpdate();
						  
						  if(k2>0) {
							  System.out.println("Product update Successfully.....");
						  }else {
							  System.out.println("Invalid product code.....");
						  }
					  }
					  break;
				case 5:
					 System.out.println("Enter Product Code to delete Product details:");
					 String pCode4 = sc.nextLine();
					 ps3.setString(1, pCode4);
					 ResultSet rs4 = ps3.executeQuery();
					 
					 if(rs4.next()) {
						 ps5.setString(1, pCode4);
						 int k3 = ps5.executeUpdate();
						 
						 if(k3>0) {
							 System.out.println("Product deleted successfully.....");
						 }
					 }else {
						 System.out.println("Invalid Prod-Code......");
					 }
					break;
				case 6:
					  System.out.println("Operation Stopped......");
					  System.exit(0);
				default:
					System.out.println("Invalid choice.....");
				}
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}

	}

}
