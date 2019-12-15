package cn.com.newloading.bean;

public class TeacherReg extends Teacher {

	private String regTime;//申请时间
	private String auditTime;//审核时间
	private String auditResult;//审核结果
	private String dealExplain;//处理说明
	private String adminId;//管理员ID
	public TeacherReg() {
		
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public String getDealExplain() {
		return dealExplain;
	}
	public void setDealExplain(String dealExplain) {
		this.dealExplain = dealExplain;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
}
