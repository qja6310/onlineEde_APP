package cn.com.newloading.bean;

public class FileBean{

	private String id;
	private String fname;
	private String fuuidName;//uuid码,后台的文件名
	private String fextend;//格式,可用于查询条件
	private String fpath;//路径
	private String fstate;//状态：1待审核 2通过 3驳回
	private String ftype;//类型：1课件资源 2作业 0删除
	private String fcheckTime;//审核时间
	private String ftime;//上传时间
	private String commitId;//提交者ID
	private String adminId;
	
	private Teacher teacher;//映射
	private Admin admin;
	
	public FileBean(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFextend() {
		return fextend;
	}
	public void setFextend(String fextend) {
		this.fextend = fextend;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String fpath) {
		this.fpath = fpath;
	}
	public String getFstate() {
		return fstate;
	}
	public void setFstate(String fstate) {
		this.fstate = fstate;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	public String getFuuidName() {
		return fuuidName;
	}
	public void setFuuidName(String fuuidName) {
		this.fuuidName = fuuidName;
	}
	public String getFcheckTime() {
		return fcheckTime;
	}
	public void setFcheckTime(String fcheckTime) {
		this.fcheckTime = fcheckTime;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getCommitId() {
		return commitId;
	}
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}
	
}
