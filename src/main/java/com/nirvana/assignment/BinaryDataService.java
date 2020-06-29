package com.nirvana.assignment;

public interface BinaryDataService {
	
	BinaryData addLeftData(Long id, BinaryDataDTO data);
	
	BinaryData addRightData(Long id, BinaryDataDTO data);

}
