package test;
import java.util.*;
import java.sql.*;

public class DBConApp {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try (s) {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "omkar", "OMKAR");

            CallableStatement cs1 = con.prepareCall("{call InsertStudentData(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            CallableStatement cs2 = con.prepareCall("{call getStudentData(?,?,?,?,?,?,?,?,?,?,?)}");
            PreparedStatement psDelete = con.prepareStatement("DELETE FROM StuResult67 WHERE rollno = ?");
            PreparedStatement psUpdateDetail = con.prepareStatement("UPDATE StuDetails67 SET name = ?, branch = ? WHERE rollno = ?");
            PreparedStatement psUpdateContact = con.prepareStatement("UPDATE StuContact67 SET mid = ?, phno = ? WHERE rollno = ?");
            PreparedStatement psUpdateAddress = con.prepareStatement("UPDATE StuAddress67 SET city = ?, state = ?, pincode = ? WHERE rollno = ?");
            PreparedStatement psUpdateMarks = con.prepareStatement("UPDATE StuMarks67 SET sub1 = ?, sub2 = ?, sub3 = ?, sub4 = ?, sub5 = ?, sub6 = ? WHERE rollno = ?");
            PreparedStatement psUpdateResult = con.prepareStatement("UPDATE StuResult67 SET totmarks = ?, per = ?, result = ? WHERE rollno = ?");

            while (true) {
                System.out.println("========Choice========");
                System.out.println("\t1. Add Student"
                        + "\n\t2. View Student Details"
                        + "\n\t3. Update Student Marks"
                        + "\n\t4. Delete Student Data"
                        + "\n\t5. Exit");

                System.out.println("Enter your Choice:");

                switch (Integer.parseInt(s.nextLine())) {
                    case 1:
                        // Insert Student Data
                        System.out.println("Enter Student Roll Number:");
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
                        String pincode = s.nextLine();

                        System.out.println("Enter Student MailId:");
                        String mid = s.nextLine();

                        System.out.println("Enter Student PhoneNumber:");
                        String phno = s.nextLine();

                        // Insert Marks
                        int[] marks = new int[6];
                        boolean validMark = true;
                        for (int i = 0; i < marks.length; i++) {
                            System.out.println("Enter Subject " + (i + 1) + " marks:");
                            marks[i] = Integer.parseInt(s.nextLine());
                            if (marks[i] < 0 || marks[i] > 100) {
                                validMark = false;
                                break;
                            }
                        }
                        if (!validMark) {
                            System.out.println("Marks must be between 0 and 100. Aborting.");
                            break;
                        }

                        cs1.setInt(1, rollno);
                        cs1.setString(2, name);
                        cs1.setString(3, branch);
                        cs1.setString(4, city);
                        cs1.setString(5, state);
                        cs1.setString(6, pincode);
                        cs1.setString(7, mid);
                        cs1.setString(8, phno);
                        for (int i = 0; i < 6; i++) {
                            cs1.setInt(9 + i, marks[i]);
                        }
                        cs1.execute();

                        int totmarks = Arrays.stream(marks).sum();
                        float percentage = (totmarks / 600.0F) * 100;
                        String result = Arrays.stream(marks).anyMatch(mark -> mark < 35) ? "Fail" : "Pass";

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
                        break;

                    case 2:
                        // View Student Data
                        System.out.println("Enter the Student Roll Number to display details:");
                        int p_rollno = Integer.parseInt(s.nextLine());

                        cs2.setInt(1, p_rollno);
                        cs2.registerOutParameter(2, Types.VARCHAR);
                        cs2.registerOutParameter(3, Types.VARCHAR);
                        cs2.registerOutParameter(4, Types.VARCHAR);
                        cs2.registerOutParameter(5, Types.VARCHAR);
                        cs2.registerOutParameter(6, Types.VARCHAR);
                        cs2.registerOutParameter(7, Types.VARCHAR);
                        cs2.registerOutParameter(8, Types.VARCHAR);
                        cs2.registerOutParameter(9, Types.INTEGER);
                        cs2.registerOutParameter(10, Types.FLOAT);
                        cs2.registerOutParameter(11, Types.VARCHAR);
                        cs2.execute();

                        System.out.println("********Student Details********");
                        System.out.println("Student Roll Number: " + p_rollno);
                        System.out.println("Student Name: " + cs2.getString(2));
                        System.out.println("Student Branch: " + cs2.getString(3));
                        System.out.println("Student City: " + cs2.getString(4));
                        System.out.println("Student State: " + cs2.getString(5));
                        System.out.println("Student PinCode: " + cs2.getString(6));
                        System.out.println("Student MailId: " + cs2.getString(7));
                        System.out.println("Student Phone Number: " + cs2.getString(8));
                        System.out.println("Student Total Marks: " + cs2.getInt(9));
                        System.out.println("Student Percentage: " + cs2.getFloat(10));
                        System.out.println("Student Result: " + cs2.getString(11));
                        break;

                    case 3:
                    	// Update Contact, Address, and Marks
                        System.out.println("Enter Student Roll Number to update:");
                        int u_rollno = Integer.parseInt(s.nextLine());
                        
                        //Update Details
                        System.out.println("Enter Student Name:");
                        String newName = s.nextLine();
                        System.out.println("Enter Branch:");
                        String newBranch = s.nextLine();
                        
                        psUpdateDetail.setString(1, newName);
                        psUpdateDetail.setString(2, newBranch);
                        psUpdateDetail.setInt(3, u_rollno);
                        psUpdateDetail.executeUpdate();
                        
                        // Update Contact Information
                        System.out.println("Enter new MailId:");
                        String newMail = s.nextLine();
                        System.out.println("Enter new Phone Number:");
                        String newPhone = s.nextLine();


                        psUpdateContact.setString(1, newMail);
                        psUpdateContact.setString(2, newPhone);
                        psUpdateContact.setInt(3, u_rollno);
                        psUpdateContact.executeUpdate();

                        // Update Address
                        System.out.println("Enter new City:");
                        String newCity = s.nextLine();

                        System.out.println("Enter new State:");
                        String newState = s.nextLine();

                        System.out.println("Enter new PinCode:");
                        String newPincode = s.nextLine();

                        psUpdateAddress.setString(1, newCity);
                        psUpdateAddress.setString(2, newState);
                        psUpdateAddress.setString(3, newPincode);
                        psUpdateAddress.setInt(4, u_rollno);
                        psUpdateAddress.executeUpdate();

                        // Update Marks
                        int[] newMarks = new int[6];
                        validMark = true;
                        for (int i = 0; i < newMarks.length; i++) {
                            System.out.println("Enter updated Subject " + (i + 1) + " marks:");
                            newMarks[i] = Integer.parseInt(s.nextLine());
                            if (newMarks[i] < 0 || newMarks[i] > 100) {
                                validMark = false;
                                break;
                            }
                        }
                        if (!validMark) {
                            System.out.println("Marks must be between 0 and 100. Aborting.");
                            break;
                        }

                        psUpdateMarks.setInt(1, newMarks[0]);
                        psUpdateMarks.setInt(2, newMarks[1]);
                        psUpdateMarks.setInt(3, newMarks[2]);
                        psUpdateMarks.setInt(4, newMarks[3]);
                        psUpdateMarks.setInt(5, newMarks[4]);
                        psUpdateMarks.setInt(6, newMarks[5]);
                        psUpdateMarks.setInt(7, u_rollno);
                        psUpdateMarks.executeUpdate();

                        // Update Result based on new marks
                        totmarks = Arrays.stream(newMarks).sum();
                        percentage = (totmarks / 600.0F) * 100;
                        result = Arrays.stream(newMarks).anyMatch(mark -> mark < 35) ? "Fail" : "Pass";

                        psUpdateResult.setInt(1, totmarks);
                        psUpdateResult.setFloat(2, percentage);
                        psUpdateResult.setString(3, result);
                        psUpdateResult.setInt(4, u_rollno);
                        psUpdateResult.executeUpdate();

                        System.out.println("Student data updated successfully.");
                        break;

                    case 4:
                        // Delete Student Data
                        System.out.println("Enter the Student Roll Number to delete data:");
                        int d_rollno = Integer.parseInt(s.nextLine());

                        psDelete.setInt(1, d_rollno);
                        int rowsDeleted = psDelete.executeUpdate();

                        if (rowsDeleted > 0) {
                            System.out.println("Student data deleted successfully.");
                        } else {
                            System.out.println("Invalid Roll Number.");
                        }
                        break;

                    case 5:
                        System.out.println("Operation Stopped.");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
