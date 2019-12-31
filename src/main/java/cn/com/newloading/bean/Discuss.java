package cn.com.newloading.bean;

/**
 * 评论区的内容
 * @author 35030
 *
 */
public class Discuss {

	private String id;
	private String foreignId;
	private String foreingType;
	private String content;
	private String cuId;
	private String createTime;
	
	public Discuss() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	public String getForeingType() {
		return foreingType;
	}

	public void setForeingType(String foreingType) {
		this.foreingType = foreingType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
