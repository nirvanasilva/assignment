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
	
}
