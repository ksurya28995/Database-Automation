package databaseImplements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbCommonMethods {

	private static Connection conn = null;
	private static ResultSet rs = null;

	/**
	 * Method is used to load the required SQL JDBC Driver class
	 * 
	 * @author SuryaRay
	 */
	public void loadSqlDriver() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method is used to load the required MYSQL JDBC Driver class
	 * 
	 * @author SuryaRay
	 */
	public void loadMySqlDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method is used to create a connection to the sql database
	 * 
	 * @author SuryaRay
	 */
	public void getSqlConnection(String serverName, String databaseName) {
		try {
			loadSqlDriver();
			conn = DriverManager.getConnection("jdbc:sqlserver://" + serverName + ":1433;databaseName=" + databaseName,
					PropertyManager.getPropertyVal("username"), PropertyManager.getPropertyVal("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method is used to create a connection to the mysql database
	 * 
	 * @author SuryaRay
	 */
	public void getMySqlConnection(String serverName, String databaseName) {
		try {
			loadMySqlDriver();
			conn = DriverManager.getConnection("jdbc:mysql://" + serverName + ":3306/" + databaseName,
					PropertyManager.getPropertyVal("mysqlusername"), PropertyManager.getPropertyVal("mysqlpassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method is used to run the query
	 * 
	 * @param query to run on the sql, colmName from which the value to be fetched as output
	 * 
	 * @author SuryaRay
	 */
	public String[] runQuery(String query) {
		if (query.toLowerCase().contains("select")) {
			System.out.println("Result of the SQL query: \"" + query + "\"");
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		}
		String rowData = "";
		String fullDbData = "";
		String[] dbArrayData = null;
		int colmCount = 0;
		int i = 1;
		try {
			// Executing SQL query and fetching the resuslt
			Statement stmt = conn.createStatement();
			boolean isResultPresent = stmt.execute(query);
			rs = isResultPresent ? stmt.getResultSet() : null;
			if (rs != null) {
				colmCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					while (colmCount >= i) {
						if (!rs.getString(i).equals(" ")) {
							rowData = rowData + rs.getString(i) + ";";
						} else {
							rowData = rowData + "<empty>;";
						}
						i++;
					}
					rowData = rowData + "@@";
					i = 1;
				}
			}
			fullDbData = rowData + "##";
			fullDbData = fullDbData.replaceAll(";@@##", "");
			if (!fullDbData.equals("")) {
				int rowCount = fullDbData.split("@@").length;
				int dbArrayIndex = 0;
				dbArrayData = new String[rowCount];
				while (rowCount > dbArrayIndex) {
					dbArrayData[dbArrayIndex] = fullDbData.split("@@")[dbArrayIndex];
					dbArrayIndex++;
				}
			}
			if (!fullDbData.equals("##"))
				for (String line : dbArrayData) {
					System.out.println(line);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out
				.println("-------------------------------------------------------------------------------------------");
		return dbArrayData;
	}
	
}
