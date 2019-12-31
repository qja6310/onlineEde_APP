package cn.com.newloading.bean.dto;

import cn.com.newloading.bean.Discuss;
import cn.com.newloading.bean.Role;

public class DiscussDto {

	private Discuss discuss;
	private Role role;
	
	public DiscussDto() {
		
	}

	public Discuss getDiscuss() {
		return discuss;
	}

	public void setDiscuss(Discuss discuss) {
		this.discuss = discuss;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
