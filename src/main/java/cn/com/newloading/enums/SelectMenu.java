package cn.com.newloading.enums;

public enum SelectMenu {

	SC("SC"),MC("MC");
	private String p;
	SelectMenu(String p){
		this.p = p;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
}
