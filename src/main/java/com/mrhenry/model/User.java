package com.mrhenry.model;

public class User extends Abstract<User> {

	private String username;
	private String password;
	private String fullname;
	private int status;
	private Long roleid;
	private Role role;
	
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	
	public User() {}
	
	public User(String username, String password, String fullname, int status, Long roleid) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.status = status;
		this.roleid = roleid;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
