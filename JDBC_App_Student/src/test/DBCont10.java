package test;
import java.util.*;
import java.sql.*;
public class DBCont10 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		try(s;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
			
			CallableStatement cs = con.prepareCall("{call InsertStudentData(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("Enter Student RollNumber:");
			int rollno = Integer.parseInt(s.nextLine());
			
			System.out.println("Enter Student Name:");
			String name = s.nextLine();
			
			System.out.println("Enter Student Branch:");
			String branch = s.nextLine();
			
			System.out.println("Enter Student City:");
			String city = s.nextLine();
			
			System.out.println("Enter Student State:");
			String state = s.nextLine();
			
			System.out.println("Enter Student PinCode:");
			String pincode  = s.nextLine();
			
			System.out.println("Enter Student MailId:");
			String mid = s.nextLine();
			
			System.out.println("Enter Student PhoneNumber:");
			String phno = s.nextLine();
			
			int []marks = new int[6];
			boolean validMark = true;
			for(int i = 0; i <= marks.length-1; i++) {
				System.out.println("Enter Subject"+(i+1)+" marks:");
				marks[i] = s.nextInt();
				if(marks[i] < 0 || marks[i] > 100) {
					validMark = false;
					break;
				}
			}
			if(!validMark) {
				System.out.println("Marks must be between 0 and 100. Aborting.");
				return;
			}
			cs.setInt(1, rollno);
			cs.setString(2, name);
			cs.setString(3, branch);
			cs.setString(4, city);
			cs.setString(5, state);
			cs.setString(6, pincode);
			cs.setString(7, mid);
			cs.setString(8, phno);
			for (int i = 0; i < 6; i++) {
                cs.setInt(9 + i, marks[i]);
            }
			cs.execute();
			
			int totmarks = 0;
			for(int mark : marks) {
				totmarks += mark;
			}
			float percentage = (totmarks/600.0F)*100;
			String result = (marks[0] < 35 || marks[1] < 35 || marks[2] < 35 || marks[3] < 35 || marks[4] < 35 || marks[5] < 35)?"Fail":"Pass";
			 
			PreparedStatement ps = con.prepareStatement("INSERT INTO StuResult67 (rollno, totmarks, per, result) VALUES (?, ?, ?, ?)");
            ps.setInt(1, rollno);
            ps.setInt(2, totmarks);
            ps.setFloat(3, percentage);
            ps.setString(4, result);
            ps.execute();

            System.out.println("Student data inserted successfully.");
            System.out.println("Total Marks: " + totmarks);
            System.out.println("Percentage: " + percentage + "%");
            System.out.println("Result: " + result);
            con.close();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
