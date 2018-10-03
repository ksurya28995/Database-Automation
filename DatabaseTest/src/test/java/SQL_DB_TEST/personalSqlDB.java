package SQL_DB_TEST;

import java.sql.SQLException;
import databaseImplements.dbCommonMethods;

public class personalSqlDB {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		runSqlQuery("INSERT INTO STATION VALUES (32, 'ganeshan', 'AZ', 33, 112); ", "city");
		runSqlQuery("select * from station;", "City");
	}

	public static void runSqlQuery(String sqlQuery, String colmName) {
		try {
			String[] dbDataList = null;
			dbCommonMethods dbObj = new dbCommonMethods();
			dbObj.getSqlConnection("RAY-PC", "test01");
			dbDataList = dbObj.runQuery(sqlQuery, colmName);
			for (String output : dbDataList)
				System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}