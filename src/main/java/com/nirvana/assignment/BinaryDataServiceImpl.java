package com.nirvana.assignment;

import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BinaryDataServiceImpl implements BinaryDataService {
	
	private BinaryDataRepository repository;
	
	public BinaryDataServiceImpl(BinaryDataRepository repository) {
		this.repository = repository;
	}

	@Override
	public BinaryData addLeftData(Long id, BinaryDataDTO inputData) {
		BinaryData binaryData = findBinaryDataByIdOrCreateNew(id);
		binaryData.setLeftData(Base64.getDecoder().decode(inputData.getData()));
		return repository.save(binaryData);
	}

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
