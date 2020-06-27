package com.nirvana.assignment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BinaryData {
	
	@Id
	private Long id;
	
	private byte[] leftData;
	
	private byte[] rightData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getLeftData() {
		return leftData;
	}

	public void setLeftData(byte[] leftData) {
		this.leftData = leftData;
	}

	public byte[] getRightData() {
		return rightData;
	}

	public void setRightData(byte[] rightData) {
		this.rightData = rightData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BinaryData other = (BinaryData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
