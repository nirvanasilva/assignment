package com.nirvana.assignment.service;

import com.nirvana.assignment.BinaryDataDTO;
import com.nirvana.assignment.entity.BinaryData;

public interface BinaryDataService {
	
	public BinaryData addLeftData(Long id, BinaryDataDTO data);
	
	public BinaryData addRightData(Long id, BinaryDataDTO data);

}
