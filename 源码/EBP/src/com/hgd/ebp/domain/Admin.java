package com.hgd.ebp.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
public class Admin implements Serializable {
    private int uid;
    @NotEmpty(message="用户名不能为空")
    private String adminname;
    @NotEmpty(message="密码不能为空")
    private String password;
    
    public Admin() {}
    
    public Admin(int uid, String adminname, String password) {
        this.uid = uid;
        this.adminname =adminname;
        this.password = password;
    }
    
    public int getUid() {
        return uid;
    }
    
    public void setUid(int uid) {
    	this.uid = uid;
    }

    public String getAdminname() {
        return adminname;
    }
    
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getPassword() {
        return password;
    }

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [uid=" + uid + ", adminname=" + adminname + ", password="
				+ password + "]";
	}
}
