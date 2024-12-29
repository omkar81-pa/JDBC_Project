package test;
import java.sql.*;
public class DBCon5 {
public static void main(String[] args) {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
		
		System.out.println("********Statement********");
		Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs1 = stm.executeQuery("select * from Product67");
		System.out.println("=========Row-4()absolute(4)==========");
		rs1.absolute(4);
		System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
		
		System.out.println("=========Row-2(relative(-2))===========");
		rs1.relative(-2);
		System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
		
		System.out.println("=======Last Row=======");
		rs1.last();
		System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));

		System.out.println("=======First Row=======");
		rs1.first();
		System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
		
		System.out.println("**************PreparedStatement*************");
		PreparedStatement ps =con.prepareStatement("select * from Product67", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs2 = ps.executeQuery();
		
		System.out.println("=======After Last=======");
		rs2.afterLast();
		while(rs2.previous()) {
			System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
		}
		System.out.println("=======Before First=======");
		rs2.beforeFirst();
		while(rs2.next()) {
			System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getFloat(3)+"\t"+rs1.getInt(4));
		}
		
		
	} catch (Exception e) {
		System.out.println(e.toString());
	}
}
}
