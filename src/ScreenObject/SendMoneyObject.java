package ScreenObject;

import java.io.Serializable;

public class SendMoneyObject implements Serializable{
	private String money;
	private String mycardnum;
	private String mycardname;
	private String yourcardnum;
	
	public SendMoneyObject() {}

	public SendMoneyObject(String money, String mycardnum, String mycardname, String yourcardnum) {
		super();
		this.money = money;
		this.mycardnum = mycardnum;
		this.mycardname = mycardname;
		this.yourcardnum = yourcardnum;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMycardnum() {
		return mycardnum;
	}

	public void setMycardnum(String mycardnum) {
		this.mycardnum = mycardnum;
	}

	public String getMycardname() {
		return mycardname;
	}

	public void setMycardname(String mycardname) {
		this.mycardname = mycardname;
	}

	public String getYourcardnum() {
		return yourcardnum;
	}

	public void setYourcardnum(String yourcardnum) {
		this.yourcardnum = yourcardnum;
	}	
}
