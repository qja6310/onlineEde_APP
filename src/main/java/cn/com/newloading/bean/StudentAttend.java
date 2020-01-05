package cn.com.newloading.bean;

public class StudentAttend {
	
	private String stuId;
	private String stuName;
	private String zCount;//正常考勤统计
	private String cCount;//迟到
	private String kCount;//旷课
	private String qCount;//请假
	
	public StudentAttend() {
		// TODO Auto-generated constructor stub
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getzCount() {
		return zCount;
	}

	public void setzCount(String zCount) {
		this.zCount = zCount;
	}

	public String getcCount() {
		return cCount;
	}

	public void setcCount(String cCount) {
		this.cCount = cCount;
	}

	public String getkCount() {
		return kCount;
	}

	public void setkCount(String kCount) {
		this.kCount = kCount;
	}

	public String getqCount() {
		return qCount;
	}

	public void setqCount(String qCount) {
		this.qCount = qCount;
	}
}
