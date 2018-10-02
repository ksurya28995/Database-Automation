package MySQL_DB_TEST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class personalMysqlDb {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		String dbUrl = "jdbc:mysql://localhost:1433/Profiles";
		// Database Username
		String username = "ksurya2895";
		// Database Password
		String password = "getITnow@surya";
		// Query to Execute
		String query = "select * from student;";

		// Load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver");
		// Object of Statement. It is used to create a Statement to execute the query
		Statement stmt = null;
		// Object of ResultSet => 'It maintains a cursor that points to the current row
		// in the result set'
		ResultSet resultSet = null;

		// Create Connection to DB
		Connection con = DriverManager.getConnection(dbUrl, username, password);
		// Execute a query
		stmt = con.createStatement();
		resultSet = stmt.executeQuery(query);
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3)
					+ "  " + resultSet.getString(4) + "  " + resultSet.getString(5));
		}

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
			}
		}
	}
}