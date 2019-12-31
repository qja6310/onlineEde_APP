package cn.com.newloading.bean.dto;

import cn.com.newloading.bean.Curriculum;
import cn.com.newloading.bean.Teacher;

public class CurriculumDto {

	private Curriculum curriculum;
	private Teacher teacher;
	
	public CurriculumDto() {
		
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
