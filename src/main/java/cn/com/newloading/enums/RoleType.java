package cn.com.newloading.enums;

public enum RoleType {

	STU("STU"),TEA("TEA"),ADM("ADM");
	
	private String role;
	RoleType(String role){
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String Role) {
		this.role = role;
	}
	
}
