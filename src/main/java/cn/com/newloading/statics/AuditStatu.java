package cn.com.newloading.statics;

public enum AuditStatu {

	PASS("PASS"),NOPASS("NOPASS"),PENDING("PENDING");
	
	private String p;
	AuditStatu(String p){
		this.p = p;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	
}
