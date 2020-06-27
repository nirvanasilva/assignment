package com.nirvana.assignment;

import java.util.Arrays;

public class BinaryDataDiffDTO {

	private byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
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
		BinaryDataDiffDTO other = (BinaryDataDiffDTO) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		return true;
	}
	
	
}
