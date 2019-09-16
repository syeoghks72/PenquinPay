package ScreenObject;

import java.io.Serializable;

public class TimeLineObject implements Serializable{
	private String trader;
	private String sentmoney;
	private String receivemoney;
	private String whentime;
	
	public TimeLineObject(String trader, String sentmoney, String receivemoney, String whentime) {
		super();
		this.trader = trader;
		this.sentmoney = sentmoney;
		this.receivemoney = receivemoney;
		this.whentime = whentime;
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
}
