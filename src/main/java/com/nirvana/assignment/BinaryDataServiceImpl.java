package com.nirvana.assignment;

import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nirvana.assignment.common.AppConstants;
import com.nirvana.assignment.common.BusinessException;

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

	@Override
	public BinaryDataDiffDTO getDiff(Long id) throws BusinessException {
		
		Optional<BinaryData> savedBinaryData = repository.findById(id);
		if(!savedBinaryData.isPresent()) {
			throw new BusinessException(AppConstants.ERROR_MESSAGE_DATA_DOES_NOT_EXIST);
		}
		
		BinaryData data = savedBinaryData.get();
		BinaryDataDiffDTO diff = new BinaryDataDiffDTO();
		
		byte[] left = data.getLeftData();
		byte[] right = data.getRightData();
		
		if(left == null || right == null) {
			throw new BusinessException(AppConstants.ERROR_MESSAGE_INVALID_LEFT_RIGHT_DATA);
		} else if(data.getLeftData().length != data.getRightData().length) {
			throw new BusinessException(AppConstants.ERROR_MESSAGE_NOT_SAME_SIZE);
		} else if(Arrays.equals(data.getLeftData(), data.getRightData())) {
			diff.setData(data.getLeftData());
		} else {
			// TODO: get details
			throw new BusinessException("Not equal");
		}
		
		return diff;
	}
}
