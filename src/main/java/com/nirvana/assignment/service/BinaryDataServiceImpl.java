package com.nirvana.assignment.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nirvana.assignment.BinaryDataDTO;
import com.nirvana.assignment.entity.BinaryData;
import com.nirvana.assignment.repository.BinaryDataRepository;

@Service
public class BinaryDataServiceImpl implements BinaryDataService {
	
	private BinaryDataRepository repository;
	
	public BinaryDataServiceImpl(BinaryDataRepository repository) {
		this.repository = repository;
	}

	@Override
	public BinaryData addLeftData(Long id, BinaryDataDTO inputData) {
		BinaryData binaryData = findBinaryDataByIdOrCreateNew(id);
		binaryData.setLeft(inputData.getData());
		return repository.save(binaryData);
	}

	@Override
	public BinaryData addRightData(Long id, BinaryDataDTO inputData) {
		BinaryData binaryData = findBinaryDataByIdOrCreateNew(id);
		binaryData.setRight(inputData.getData());
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
