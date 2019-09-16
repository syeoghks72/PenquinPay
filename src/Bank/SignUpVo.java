package Bank;

import java.io.Serializable;

public class SignUpVo implements Serializable {

	// 아이디 이메일 비번 전번
	private String name;
	private String email;
	private String password;
	private String phone;
	public static final String TableName = "SIGNUP";

	public SignUpVo() {
		super();
	}

	public SignUpVo(String name, String email, String password, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return password;
	}

	public void setPwd(String pwd) {
		this.password = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}// -------------------------------