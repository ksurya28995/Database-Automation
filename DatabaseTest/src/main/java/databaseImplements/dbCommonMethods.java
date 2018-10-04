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
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Creating a connection to the database
	public void getSqlConnection(String serverName, String databaseName) {
		try {
			loadSqlDriver();
			conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ":1433;databaseName=" + databaseName,
					PropertyManager.getPropertyVal("username"), PropertyManager.getPropertyVal("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Creating a connection to the database
	public void getMySqlConnection(String serverName, String databaseName) {
		try {
			loadMySqlDriver();
			conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":3306/" + databaseName,
					PropertyManager.getPropertyVal("mysqlusername"), PropertyManager.getPropertyVal("mysqlpassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String[] runQuery(String query, String colmName) {
		String[] fetchData = null;
		int databaseCount = 0;
		try {
			// Executing SQL query and fetching the resuslt
			Statement stmt = conn.createStatement();
			boolean isResultPresent = stmt.execute(query);
			rs = isResultPresent ? stmt.getResultSet() : null;
			if (rs != null) {
				while (rs.next())
					databaseCount++;
				fetchData = new String[databaseCount];
				databaseCount--;
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					fetchData[databaseCount] = rs.getString(colmName);
					databaseCount--;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fetchData;
	}
	
}
