package com.nirvana.assignment;

import java.util.List;

/**
 * Output DTO for diffs.
 * 
 */
public class DiffDTO {

	private String message;
	private List<DiffDetail> diff;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<DiffDetail> getDiff() {
		return diff;
	}

	public void setDiff(List<DiffDetail> diff) {
		this.diff = diff;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		DiffDTO other = (DiffDTO) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	
}
