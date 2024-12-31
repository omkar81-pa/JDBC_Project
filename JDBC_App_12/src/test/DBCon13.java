package test;
import java.sql.*;
public class DBCon13 {
	public static void main(String[] args) {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","omkar","OMKAR");
		
		System.out.println("*******DataBaseMetaData*********");
		DatabaseMetaData dmd = con.getMetaData();
		System.out.println("Product-Name:"+dmd.getDatabaseProductName());
		System.out.println("Driver-Name:"+dmd.getDriverName());
		System.out.println("Varsion:"+dmd.getDriverVersion());
		
		PreparedStatement ps1 = con.prepareStatement("insert into Bank67 values(?,?,?,?)"); 
		System.out.println("******ParameterMetaData*******");
		ParameterMetaData pmd = ps1.getParameterMetaData();
		System.out.println("Parameter-Count:"+pmd.getParameterCount());
		System.out.println("Parameter-ClassName:"+pmd.getParameterClassName(1));
		System.out.println("Parameter-HashCode:"+pmd.hashCode());
		
		PreparedStatement ps2 = con.prepareStatement("select code,prise,qty from Product67"); 
		ResultSet rs = ps2.executeQuery();
		System.out.println("*****ResultSetMetaData*****");
		ResultSetMetaData rsmd = rs.getMetaData();
		System.out.println("Column-Count:"+rsmd.getColumnCount());
		System.out.println("Column-Name:"+rsmd.getColumnName(2));
		System.out.println("Column-Count:"+rsmd.getColumnClassName(1));
		
		con.close();

	}catch(Exception e) {
		e.printStackTrace();
	}
	}
}
