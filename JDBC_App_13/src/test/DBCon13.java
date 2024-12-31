package test;
import java.util.*;
import java.sql.*;
public class DBCon13 {
	public static void main(String[] args) {
		 Scanner s = new Scanner(System.in);
	       try(s;)
	       {
	    	   Class.forName("oracle.jdbc.driver.OracleDriver");
	    	   Connection con = DriverManager.getConnection
	    			   ("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
	    	   try(con;){
	    		   Statement stm = con.createStatement();
	    		   try(stm;){
	    			   System.out.println("Enter the NonSelect-query(create,insert,update,delete)");
	    			   String qry = s.nextLine();
	    			   int k = stm.executeUpdate(qry);
	    			   System.out.println("The value in k : "+k);
	    			   if(k>=0) {
	    				   System.out.println("query executed Successfully....");
	    			   }
	    		   }//end of try
	    	   }//end of try
	       }//end of try
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	       }

	}
}
