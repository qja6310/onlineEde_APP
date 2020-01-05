package cn.com.newloading.bean;

public class StudentCurriculumLog {

	private String id;
	private String sId;
	private String clId;
	private String hwState;//作业状态
	private String hwScore;//作业分
	private String hwTime;//作业时间
	private String state;//
	private String time;
	
	private Student student;
	
	public StudentCurriculumLog() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getClId() {
		return clId;
	}

	public void setClId(String clId) {
		this.clId = clId;
	}

	public String getHwState() {
		return hwState;
	}

	public void setHwState(String hwState) {
		this.hwState = hwState;
	}

	public String getHwScore() {
		return hwScore;
	}

	public void setHwScore(String hwScore) {
		this.hwScore = hwScore;
	}

	public String getHwTime() {
		return hwTime;
	}

	public void setHwTime(String hwTime) {
		this.hwTime = hwTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
