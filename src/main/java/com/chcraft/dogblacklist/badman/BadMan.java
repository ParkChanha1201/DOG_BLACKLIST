package com.chcraft.dogblacklist.badman;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "badman")
public class BadMan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String phone;

	public BadMan() {
	}

	public BadMan(int id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public BadMan(BadManDTO badMan) {
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
		BadMan other = (BadMan) obj;
		if (id != other.id)
			return false;
		if (!name.equals(other.name))
			return false;
		if (!phone.equals(other.phone))
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