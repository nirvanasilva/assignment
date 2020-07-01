package com.nirvana.assignment;

import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Service to basic operations on BinaryData entity.
 * It can add left and right data to the entity.
 * If entity does not exist, it is created a new instance with the given id and data.
 */
@Service
public class BinaryDataServiceImpl implements BinaryDataService {
	
	private BinaryDataRepository repository;
	
	public BinaryDataServiceImpl(BinaryDataRepository repository) {
		this.repository = repository;
	}

	/**
	 * Add left data to a binary data object with a given ID.
	 * If the object does not exist in the database yet, the object is created. 
	 * If the object already exists, left data attribute is updated.
	 */
	@Override
	public BinaryData addLeftData(Long id, BinaryDataDTO inputData) {
		BinaryData binaryData = findBinaryDataByIdOrCreateNew(id);
		binaryData.setLeftData(Base64.getDecoder().decode(inputData.getData()));
		return repository.save(binaryData);
	}

	/**
	 * Add right data to a binary data object with a given ID.
	 * If the object does not exist in the database yet, the object is created. 
	 * If the object already exists, right data attribute is updated.
	 */
	@Override
	public BinaryData addRightData(Long id, BinaryDataDTO inputData) {
		BinaryData binaryData = findBinaryDataByIdOrCreateNew(id);
		binaryData.setRightData(Base64.getDecoder().decode(inputData.getData()));
		return repository.save(binaryData);
	}
	
	private BinaryData findBinaryDataByIdOrCreateNew(Long id) {
		BinaryData binaryData;
		Optional<BinaryData> savedBinaryData = repository.findById(id);
		if(savedBinaryData.isPresent()) {
			binaryData = savedBinaryData.get();
		} else {
			binaryData = new BinaryData();
			binaryData.setId(id);
		}
		return binaryData;
	}
}
