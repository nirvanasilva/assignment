package com.nirvana.assignment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BinaryData {
	
	@Id
	private Long id;
	
	private byte[] left;
	
	private byte[] right;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getLeft() {
		return left;
	}

	public void setLeft(byte[] left) {
		this.left = left;
	}

	public byte[] getRight() {
		return right;
	}

	public void setRight(byte[] right) {
		this.right = right;
	}
}
