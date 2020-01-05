package cn.com.newloading.bean;

public class StudentAttend {
	
	private String stuId;
	private String stuName;
	private int zCount;//正常考勤统计
	private int cCount;//迟到
	private int kCount;//旷课
	private int qCount;//请假
	
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

	public int getzCount() {
		return zCount;
	}

	public void setzCount(int zCount) {
		this.zCount = zCount;
	}

	public int getcCount() {
		return cCount;
	}

	public void setcCount(int cCount) {
		this.cCount = cCount;
	}

	public int getkCount() {
		return kCount;
	}

	public void setkCount(int kCount) {
		this.kCount = kCount;
	}

	public int getqCount() {
		return qCount;
	}

	public void setqCount(int qCount) {
		this.qCount = qCount;
	}

}
