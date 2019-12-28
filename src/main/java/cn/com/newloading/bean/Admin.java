package cn.com.newloading.bean;

public class Admin extends Role{

	private String id;
	private String admName;
	private String admAccount;
	private String admPassword;
	private String role;
	public Admin() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdmName() {
		return admName;
	}
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	public String getAdmAccount() {
		return admAccount;
	}
	public void setAdmAccount(String admAccount) {
		this.admAccount = admAccount;
	}
	public String getAdmPassword() {
		return admPassword;
	}
	public void setAdmPassword(String admPassword) {
		this.admPassword = admPassword;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
