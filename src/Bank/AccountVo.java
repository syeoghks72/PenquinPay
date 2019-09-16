package Bank;

import java.io.Serializable;

public class AccountVo implements Serializable {
	private String accnum;
	private String email;
	private String cardnum;
	private String money;
	public static final String TableName = "ACCOUNT";

	public AccountVo() {
		super();
	}

	public AccountVo(String accnum, String email, String cardnum, String money) {
		super();
		this.accnum = accnum;
		this.email = email;
		this.cardnum = cardnum;
		this.money = money;
	}

	public String getAccnum() {
		return accnum;
	}

	public void setAccnum(String accnum) {
		this.accnum = accnum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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