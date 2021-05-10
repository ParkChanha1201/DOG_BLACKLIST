package com.chcraft.dogblacklist.badman;

public class BadManDTO {
	private int id;
	private String name;
	private String phone;

	public BadManDTO() {
	}

	public BadManDTO(int id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public BadManDTO(BadMan badMan) {
		this.id = badMan.getId();
		this.name = badMan.getName();
		this.phone = badMan.getPhone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
