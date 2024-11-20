package com.crm.mainservice.C_R_M_MainService.entity;



public class User {
	

	private String username;
	private String password;
	private String crmType;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCrmType() {
		return crmType;
	}

	public void setCrmType(String crmType) {
		this.crmType = crmType;
	}



	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", crmType=" + crmType + "]";
	}

}
