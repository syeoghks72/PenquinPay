package ScreenObject;

import java.io.Serializable;

public class AccountObject implements Serializable{
	private String bankname;
	private String cardnum;
	private String money;
	
	public AccountObject() {}

	public AccountObject(String bankname, String cardnum, String money) {
		super();
		this.bankname = bankname;
		this.cardnum = cardnum;
		this.money = money;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	
}
