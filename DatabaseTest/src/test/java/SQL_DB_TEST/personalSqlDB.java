package SQL_DB_TEST;

import java.sql.SQLException;

import databaseImplements.dbCommonMethods;

public class personalSqlDB {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		runSqlQuery("INSERT INTO STATION VALUES (30, 'ganesh', 'AZ', 33, 112); ", "city");
		runSqlQuery("select * from station;", "City");
	}

	public static void runSqlQuery(String sqlQuery, String colmName) {
		String outputLine = null;
		String[] outputList = null;
		dbCommonMethods dbObj = new dbCommonMethods();
		dbObj.loadSqlDriver();
		dbObj.getSqlConnection("RAY-PC", "test01");
		outputLine = dbObj.runQuery(sqlQuery, colmName);
		outputList = outputLine.split("//", -1);
		for(String output : outputList)
			System.out.println(output);
}
}