package databaseImplements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbCommonMethods {

	private static Connection conn = null;
	private static ResultSet rs = null;

	// Loading the required SQL JDBC Driver class
	public void loadSqlDriver() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Loading the required MYSQL JDBC Driver class
	public void loadMySqlDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Creating a connection to the database
	public void getSqlConnection(String serverName, String databaseName) {
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ":1433;databaseName=" + databaseName,
					"ksurya2895", "ksurya2895");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String runQuery(String query, String colmName) {
		String outString = "";
		try {
			// Executing SQL query and fetching the result
			Statement stmt = conn.createStatement();
			boolean isResultPresent = stmt.execute(query);
			rs = isResultPresent ? stmt.getResultSet() : null;
			if (rs != null) {
				while (rs.next())
					outString = outString + "//" + rs.getString(colmName).trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outString;
	}

}
