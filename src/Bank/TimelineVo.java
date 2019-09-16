package Bank;

import java.io.Serializable;

public class TimelineVo implements Serializable {
	private String timenum;
	private String trader;
	private String sentmoney;
	private String receivemoney;
	private String whentime;
	private String email;
	private String accnum;
	public static final String TableName = "TIMELINE";

	public TimelineVo() {
		super();
	}

	public TimelineVo(String timenum, String trader, String sentmoney, String receivemoney, String whentime,
			String email, String accnum) {
		super();
		this.timenum = timenum;
		this.trader = trader;
		this.sentmoney = sentmoney;
		this.receivemoney = receivemoney;
		this.whentime = whentime;
		this.email = email;
		this.accnum = accnum;
	}

	public String getTimenum() {
		return timenum;
	}

	public void setTimenum(String timenum) {
		this.timenum = timenum;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public String getSentmoney() {
		return sentmoney;
	}

	public void setSentmoney(String sentmoney) {
		this.sentmoney = sentmoney;
	}

	public String getReceivemoney() {
		return receivemoney;
	}

	public void setReceivemoney(String receivemoney) {
		this.receivemoney = receivemoney;
	}

	public String getWhentime() {
		return whentime;
	}

	public void setWhentime(String whentime) {
		this.whentime = whentime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccnum() {
		return accnum;
	}

	public void setAccnum(String accnum) {
		this.accnum = accnum;
	}

}