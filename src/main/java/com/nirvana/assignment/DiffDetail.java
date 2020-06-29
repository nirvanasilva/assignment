package com.nirvana.assignment;

public class DiffDetail {
	
	private int offset;
	private int length;
	
	public DiffDetail(int offset, int length) {
		this.offset = offset;
		this.length = length;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + length;
		result = prime * result + offset;
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
		DiffDetail other = (DiffDetail) obj;
		if (length != other.length)
			return false;
		if (offset != other.offset)
			return false;
		return true;
	}
	
}
