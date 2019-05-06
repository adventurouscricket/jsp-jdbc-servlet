package com.mrhenry.model;

public class Role extends Abstract<Role> {
	
	private String name;
	private String  code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Role() {}
	
	public Role(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	
	
}
