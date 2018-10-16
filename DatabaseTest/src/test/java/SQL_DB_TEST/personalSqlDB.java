package SQL_DB_TEST;

import java.sql.SQLException;
import databaseImplements.dbCommonMethods;

/**
 * Class is used to run the query on the SQL database
 * 
 * @author surya.k.kumaresan
 */
public class personalSqlDB {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		runSqlQuery("create table professional ( userId int, name varchar(50))", "");
		runSqlQuery("select * from student;", "Name");
	}

	public static void runSqlQuery(String sqlQuery, String colmName) {
		try {
			String[] dbDataList = null;
			dbCommonMethods dbObj = new dbCommonMethods();
			dbObj.getSqlConnection("BDC8-DX-6PHHH92\\SQLEXPRESS", "Profiles");
			dbDataList = dbObj.runQuery(sqlQuery, colmName);
			if (dbDataList != null)
				for (String output : dbDataList)
					System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}