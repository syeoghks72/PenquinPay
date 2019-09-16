package ScreenObject;

import java.io.Serializable;

public class CardObject implements Serializable {
	private String cardnum;
	private String period;
	private String cvc;
	private String password;
	private String bankname;

	public CardObject() {
	}

	public CardObject(String cardnum, String period, String cvc, String password, String bankname) {
		super();
		this.cardnum = cardnum;
		this.period = period;
		this.cvc = cvc;
		this.password = password;
		this.bankname = bankname;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}	
}
