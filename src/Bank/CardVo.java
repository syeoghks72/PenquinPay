package Bank;

public class CardVo {
	private String cardnum;
	private String period;
	private String cvc;
	private String cardpwd;
	private String email;
	private String bankname;
	public static final String TableName = "CARD";

	public CardVo() {
		super();
	}

	public CardVo(String cardnum, String period, String cvc, String cardpwd, String email) {
		super();
		this.cardnum = cardnum;
		this.period = period;
		this.cvc = cvc;
		this.cardpwd = cardpwd;
		this.email = email;
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

	public String getCardpwd() {
		return cardpwd;
	}

	public void setCardpwd(String cardpwd) {
		this.cardpwd = cardpwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

}