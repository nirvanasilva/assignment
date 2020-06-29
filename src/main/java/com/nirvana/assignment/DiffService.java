package com.nirvana.assignment;

import com.nirvana.assignment.common.BusinessException;

public interface DiffService {
	
	DiffDTO getDiff(Long id) throws BusinessException;

}
