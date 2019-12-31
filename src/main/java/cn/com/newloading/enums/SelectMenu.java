package cn.com.newloading.enums;

public enum SelectMenu {

	SC("SC"),MC("MC");//SC--还没选择的课程  MC--是我的课程，已选择的课程
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
