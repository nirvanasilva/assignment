package com.nirvana.assignment;

import javax.validation.constraints.NotEmpty;

import com.nirvana.assignment.validation.Base64Constraint;

/**
 * Input data for left / right endpoints.
 * Data is validated by checking if it's a valid Base64 encoded string.
 */
public class BinaryDataDTO {

	@NotEmpty(message = "data cannot be empty")
	@Base64Constraint
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		BinaryDataDTO other = (BinaryDataDTO) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
}
