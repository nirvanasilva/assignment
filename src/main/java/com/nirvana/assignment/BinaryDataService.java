package com.nirvana.assignment;

import com.nirvana.assignment.common.BusinessException;

public interface BinaryDataService {
	
	public BinaryData addLeftData(Long id, BinaryDataDTO data);
	
	public BinaryData addRightData(Long id, BinaryDataDTO data);
	
	public BinaryDataDiffDTO getDiff(Long id) throws BusinessException;

}
