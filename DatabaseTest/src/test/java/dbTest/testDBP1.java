package dbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class  testDBP1 {		
public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		//Loading the required JDBC Driver class
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		
		//Creating a connection to the database
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://BDC8-DX-6PHHH92\\SQLEXPRESS/Profiles","ksurya2895","getITnow@surya");
		
		//Executing SQL query and fetching the result
		Statement st = conn.createStatement();
		String sqlStr = "select * from student;";
		ResultSet rs = st.executeQuery(sqlStr);
		while (rs.next()) {
			System.out.println(rs.getString("name"));
		}		
	}
}