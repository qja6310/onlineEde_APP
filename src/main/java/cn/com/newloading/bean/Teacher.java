package cn.com.newloading.bean;

public class Teacher {

	private String id;
	private String teaName;
	private String teaAccount;//登录账号
	private String teaPassword;//登录密码
	private String status;//状态
	public Teacher() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getTeaAccount() {
		return teaAccount;
	}
	public void setTeaAccount(String teaAccount) {
		this.teaAccount = teaAccount;
	}
	public String getTeaPassword() {
		return teaPassword;
	}
	public void setTeaPassword(String teaPassword) {
		this.teaPassword = teaPassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
