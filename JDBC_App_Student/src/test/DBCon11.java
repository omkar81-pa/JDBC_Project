package test;
import java.util.*;
import java.sql.*;
class DBCon11 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try(s;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
			CallableStatement cs = con.prepareCall("{call getStudentData(?,?,?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("Enter the Student rollno to display Details:");
			int rollno = Integer.parseInt(s.nextLine());
			cs.setInt(1, rollno);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.registerOutParameter(9, Types.INTEGER);
			cs.registerOutParameter(10, Types.FLOAT);
			cs.registerOutParameter(11, Types.VARCHAR);
			cs.execute();
			
			System.out.println("********Student Details********");
			System.out.println("Student Roll-Number:"+rollno);
			System.out.println("Student Name:"+cs.getString(2));
			System.out.println("Student Branch:"+cs.getString(3));
			System.out.println("Student City:"+cs.getString(4));
			System.out.println("Student State:"+cs.getString(5));
			System.out.println("Student PinCode:"+cs.getString(6));
			System.out.println("Student MailId:"+cs.getString(7));
			System.out.println("Student Phone-Number:"+cs.getString(8));
			System.out.println("Student Total-Marks:"+cs.getInt(9));
			System.out.println("Student Percentage:"+cs.getFloat(10));
			System.out.println("Student Result:"+cs.getString(11));
			con.close();
		}catch(Exception e) {
			System.err.println(e.toString());
		}

	}

}
