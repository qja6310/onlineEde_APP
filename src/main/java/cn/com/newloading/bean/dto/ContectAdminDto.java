package cn.com.newloading.bean.dto;

import cn.com.newloading.bean.ContectAdmin;
import cn.com.newloading.bean.Role;

public class ContectAdminDto {

	private ContectAdmin contectAdmin;
	private Role role;
	
	public ContectAdminDto() {
		
	}

	public ContectAdmin getContectAdmin() {
		return contectAdmin;
	}

	public void setContectAdmin(ContectAdmin contectAdmin) {
		this.contectAdmin = contectAdmin;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
