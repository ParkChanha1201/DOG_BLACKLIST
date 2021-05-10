package com.chcraft.dogblacklist.badman;

public class BadManDTO {
	private int id;
	private String name;
	private String phone;

	public BadManDTO() {
	}

	public BadManDTO(String name, String phone) {
		this.name = name;
		this.phone = phone;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BadManDTO other = (BadManDTO) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
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
