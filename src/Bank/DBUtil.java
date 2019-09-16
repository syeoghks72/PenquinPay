package Bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	static Connection con;
	static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	static String user = "scott";
	static String pwd = "tiger";


	static {
		//static initializer : main()메소드 보다도 먼저 실행하는 블럭
		//여기서 오라클 드라이버를 로드시키자.
		//System.out.println("static block");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!");
		}
		catch(ClassNotFoundException e) {
			System.out.println("Driver Loading Fail..");
			e.printStackTrace();
		}
	}//-------------------------

	public static Connection getCon() throws java.sql.SQLException{
		if(con==null) con = DriverManager.getConnection(url, user, pwd);
		return con;
	}//--------------
}
