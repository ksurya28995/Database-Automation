package MySQL_DB_TEST;

import java.sql.SQLException;
import databaseImplements.dbCommonMethods;
/**
 * Class is used to run the query on the Mysql database
 * 
 * @author SuryaRay
 */
public class personalMysqlDb {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String insert = "INSERT INTO studentprofiles (studentprofiles.id, studentprofiles.Name, studentprofiles.Age, studentprofiles.Address, studentprofiles.Sex) VALUES ('6', 'Ajay', '23', 'Chennai', 'Male');";
		runSqlQuery(insert);
		runSqlQuery("select * from studentprofiles;");
	}

	public static void runSqlQuery(String sqlQuery) {
		try {
			String[] dbDataList = null;
			dbCommonMethods dbObj = new dbCommonMethods();
			dbObj.getMySqlConnection("localhost", "students");
			dbDataList = dbObj.runQuery(sqlQuery);
			if (dbDataList != null)
				for (String output : dbDataList)
					System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}