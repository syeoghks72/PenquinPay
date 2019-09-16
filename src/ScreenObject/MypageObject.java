package ScreenObject;

import java.io.Serializable;

public class MypageObject implements Serializable{
	private String name;
	private String phone;
	private String money;
	
	public MypageObject() {}
	
	public MypageObject(String name, String phone, String money) {
		super();
		this.name = name;
		this.phone = phone;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	
	
	
	
}
