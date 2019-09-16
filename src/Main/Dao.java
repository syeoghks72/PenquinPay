package Main;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Bank.DBUtil;
import Bank.SignUpVo;
import Bank.TimelineVo;
import ScreenObject.AccountObject;
import ScreenObject.MypageObject;
import ScreenObject.SendMoneyObject;
import ScreenObject.TimeLineObject;

public class Dao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private Method[] methods;

	public void invokeSetMethod(Object vo, String methodToInvoke, String toSet) throws Exception {
		methods = vo.getClass().getMethods();
		String methodToInvokeTolowCase = methodToInvoke.toLowerCase();
		String firstString = methodToInvokeTolowCase.substring(0, 1).toUpperCase();
		String modifiedString = "set" + firstString + methodToInvokeTolowCase.substring(1, methodToInvoke.length());

		for (Method method : methods) {
			if (method.getName().equals(modifiedString)) {
				method.invoke(vo, toSet);
			}
		}
	}

	public String invokeGetMethod(Object vo, String methodToInvoke) throws Exception {
		methods = vo.getClass().getMethods();
		String methodToInvokeTolowCase = methodToInvoke.toLowerCase();
		String firstString = methodToInvokeTolowCase.substring(0, 1).toUpperCase();
		String modifiedString = "get" + firstString
				+ methodToInvokeTolowCase.substring(1, methodToInvokeTolowCase.length());
		for (Method method : methods) {
			if (method.getName().equals(modifiedString)) {
				return (String) method.invoke(vo);
			}
		}
		return null;
	}

	public int insertVo(Object vo, String tableName) throws Exception {
		try {
			con = DBUtil.getCon();
			ps = con.prepareStatement("select * from " + tableName + " where 1=2"); // 데이터 없음.. 패치도없음
			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCnt = rsmd.getColumnCount();

			String sql_parameter = "";
			String[] column_name_arry = new String[columnCnt];

			for (int i = 1; i <= columnCnt; i++) {
				column_name_arry[i - 1] = rsmd.getColumnName(i);
				sql_parameter += "?";
				if (i != columnCnt)
					sql_parameter += ",";
			}

			String sql = "INSERT INTO " + tableName + " VALUES(" + sql_parameter + ")";
			ps = con.prepareStatement(sql);

			for (int i = 1; i <= column_name_arry.length; i++) {
				ps.setString(i, invokeGetMethod(vo, column_name_arry[i - 1]));
			}

			int n = ps.executeUpdate();
			return n;
		} finally {
			close();
		}
	}

	public ArrayList<Object> ReturnVOArray(Class cls, String tableName) throws Exception {
		try {
			String sql = "SELECT * FROM " + tableName;
			con = DBUtil.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			return makeList(cls, rs);
		} finally {
			close();
		}
	}

	public ArrayList<Object> makeList(Class cls, ResultSet rs) throws Exception {
		ArrayList<Object> arr = new ArrayList<Object>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCnt = rsmd.getColumnCount();

		while (rs.next()) {
			Object obj = cls.newInstance();
			for (int i = 1; i <= columnCnt; i++) {
				String column_name = rsmd.getColumnName(i);
				invokeSetMethod(obj, column_name, rs.getString(column_name));
				arr.add(obj);
			}
		}
		return arr;
	}

	public boolean loginCheck(String email, String password) throws Exception{
		String sql = "select * from signup where email=? and password=?";
		boolean isData = false;
		con = DBUtil.getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, password);
		rs = ps.executeQuery();
		while(rs.next()) {
			isData = true;
			if(isData) break;
		}
		return isData;
	}

	public ArrayList<Object> getMypageObject(String email) throws Exception{
		ArrayList<Object> objectArry = new ArrayList<Object>();
		MypageObject mypageObject = new MypageObject();
		String sql = "select sum(sentmoney) money from timeline " +
				"where SUBSTR(whentime, INSTR(whentime, '/', 1, 1) + 1, " +
				"INSTR(whentime, '/', 1, 2) - " +
				"INSTR(whentime, '/', 1, 1) - 1) = to_char(sysdate,'MM') and email='"+email+"'";
		con = DBUtil.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while(rs.next()) {
			mypageObject.setMoney(rs.getString("money"));
		}

		sql = "select * from signup where email='"+email+"'";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		while(rs.next()) {
			mypageObject.setName(rs.getString("name"));
			mypageObject.setPhone(rs.getString("phone"));
		}
		objectArry.add(mypageObject);
		return objectArry;
	}

	public ArrayList<Object> getAccountObjects(String email) throws Exception{
		ArrayList<Object> objectArry = new ArrayList<Object>();
		String sql = "select a.money money, c.cardnum cardnum, c.bankname bankname from account a, card c "
				+ "where a.cardnum=c.cardnum and a.email='"+email+"' order by accnum";
		con = DBUtil.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			AccountObject accountObject =
					new AccountObject(rs.getString("bankname"), rs.getString("cardnum"), rs.getString("money"));
			objectArry.add(accountObject);
		}
		return objectArry;
	}

	public ArrayList<Object> getTimeLineObjects(String email) throws Exception{
		ArrayList<Object> objectArry = new ArrayList<Object>();
		String sql ="select trader, sentmoney, receivemoney, whentime from timeline where email='"+email+"' order by timenum desc";
		con = DBUtil.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			TimeLineObject timelineObject =
					new TimeLineObject(rs.getString("trader"), rs.getString("sentmoney"), rs.getString("receivemoney"), rs.getString("whentime"));
			objectArry.add(timelineObject);
		}
		return objectArry;
	}

	public ArrayList<Object> getMyAccountList(String email) throws Exception{
		ArrayList<Object> objectArry = new ArrayList<Object>();
		String sql = "select bankname, cardnum from card where email='"+email+"'";
		con = DBUtil.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			SendMoneyObject sendMoneyObject =
					new SendMoneyObject(null, rs.getString("cardnum"), rs.getString("bankname"), null);
			objectArry.add(sendMoneyObject);
		}
		return objectArry;
	}

	public boolean sendMoney(String money, String mycardnum, String yourcardnum, String email) throws Exception{
		ArrayList<Integer> updateCheck = new ArrayList<>();
		int updateNum=0;
		int remaining_balance=0;
		String myaccnum = "";
		String myname="";
		String youraccnum="";
		String yourname="";
		String youremail="";
		int yourmoney=0;

		String sql = "select s.name myname, a.accnum myaccnum, a.money money "
				+ "from signup s, (select accnum, money, email from account where cardnum='"+mycardnum+"') a "
				+ "where a.email=s.email";
		con = DBUtil.getCon();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			remaining_balance=rs.getInt("money");
			myaccnum=rs.getString("myaccnum");
			myname=rs.getString("myname");
		}

		int later_money =remaining_balance-Integer.parseInt(money);
		
		if(later_money > 0) {	//잔액이 송금액 보다 많아야 송금 실행
			//상대 데이터 뽑기
			sql = "select a.accnum youraccnum, a.email email, s.name yourname, a.money yourmoney "
					+ "from signup s, (select accnum, email, money from account where cardnum='"+yourcardnum+"') a "
					+ "where a.email=s.email";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				youraccnum = rs.getString("youraccnum");
				yourname = rs.getString("yourname");
				youremail = rs.getString("email");
				yourmoney = rs.getInt("yourmoney");
			}
			if(youraccnum.trim().isEmpty() ||  youraccnum.trim().isEmpty() 
					|| yourname.trim().isEmpty() ||youraccnum==null || yourname==null || youremail==null) {
				return false;
			}
			
			//내계좌의 잔액 업데이트
			sql = "update account set money='"+Integer.toString(later_money)+"' where cardnum='"+mycardnum+"'";
			ps = con.prepareStatement(sql);
			updateNum = ps.executeUpdate();
			updateCheck.add(updateNum);


			int later_your_money = yourmoney + Integer.parseInt(money);
			//상대계좌 잔액 업데이트
			sql = "update account set money='"+Integer.toString(later_your_money)+"' where cardnum='"+yourcardnum+"'";
			ps = con.prepareStatement(sql);
			updateNum = ps.executeUpdate();
			updateCheck.add(updateNum);
			////타임라인 입력부분.....  //나의 timeline 이력에 추가...
			sql = "insert into timeline values"
					+ "(timenum.nextval,'"+yourname+"','"+money+"','0',sysdate,'"+email+"',"+myaccnum+")";
			ps = con.prepareStatement(sql);
			updateNum = ps.executeUpdate();
			updateCheck.add(updateNum);
			//타임라인 입력부분....//상대 timeline 이력에 추가
			sql = "insert into timeline values"
					+ "(timenum.nextval,'"+myname+"','0','"+money+"',sysdate,'"+youremail+"',"+youraccnum+")";
			ps = con.prepareStatement(sql);
			updateNum = ps.executeUpdate();
			updateCheck.add(updateNum);		
			return true;
		}
		else {
			return false;
		}
		

	}

	public void addCard(String cardnum, String period, String cvc, String password, String email, String bankname) throws Exception{
		String sql ="insert into card values('"+cardnum+"','"+period+"','"+cvc+"','"+password+"','"+email+"','"+bankname+"')";
		con = DBUtil.getCon();
		ps = con.prepareStatement(sql);
		ps.executeUpdate();

		sql = "insert into account values(accnum.nextval,'"+email+"','"+cardnum+"',0)";
		ps = con.prepareStatement(sql);
		ps.executeUpdate();
	}

	public void addMember(String name, String email, String password, String phone) throws Exception{
		String sql = "insert into signup values(?,?,?,?)";
		con = DBUtil.getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setString(2, email);
		ps.setString(3, password);
		ps.setString(4, phone);
		ps.executeUpdate();
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

//	public static void main(String[] args) {
//		Dao da = new Dao();
//		SignUpVo vo = new SignUpVo();
//		vo.setName("abc");
//		vo.setEmail("def");
//		vo.setPwd("ghi");
//		vo.setPhone("jkl");
//		try {
//			ArrayList<Object> arry = da.ReturnVOArray(TimelineVo.class, TimelineVo.TableName);
//			for (int i = 0; i < arry.size(); i++) {
//				System.out.println(((TimelineVo) arry.get(i)).getSentmoney());
//			}
//			// da.insertVo(vo, vo.TableName);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}



//		Dao da = new Dao();
//		try {
//			ArrayList<Object> arry = da.getAccountObjects("kww888@naver.com");
//			for(int i = 0 ; i < arry.size() ; i++) {
//				System.out.println(((AccountObject)arry.get(i)).getBankname());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		};

//		Dao da = new Dao();
//		try {
//			ArrayList<Object> arry = da.getTimeLineObjects("kww888@naver.com");
//			for(int i = 0 ; i < arry.size() ; i++) {
//				System.out.println(((TimeLineObject)arry.get(i)).getSentmoney());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		};

//		Dao da = new Dao();
//		try {
//			ArrayList<Object> arry = da.getMyAccountList("kww888@naver.com");
//			for(int i = 0 ; i < arry.size() ; i++) {
//				System.out.println(((SendMoneyObject)arry.get(i)).getMycardname());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		};

//		Dao da = new Dao();
//		try {
//			da.sendMoney(null, "1554381255469401", null, null);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		};

//		Dao da = new Dao();
//		try {
//			Boolean bool = da.sendMoney("500", "3241248455469401", "1110554199419401","kww888@naver.com");
//			if(bool) {
//				System.out.println("됬는데?");
//			}
//			else {
//				System.out.println("아닌데?");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		Dao da = new Dao();
//		try {
//			da.addCard("9984547855410001", "23/05", "741", "6548", "kjyun1903@naver.com", "농협");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		};

//		Dao da = new Dao();
//		try {
//			da.addMember("이순신", "josun@naver.com", "aa123123", "010-5473-9993");
//		} catch (Exception e) {
//			e.printStackTrace();
//		};
//		
//	}
}
