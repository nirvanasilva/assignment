package com.nirvana.assignment;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * This service handles diff operations over a BinaryData.
 * Diff is calculated only if left and right data have the same length.
 */
@Service
public class DiffServiceImpl implements DiffService {
	
	private BinaryDataRepository repository;
	
	public DiffServiceImpl(BinaryDataRepository repository) {
		this.repository = repository;
	}

	@Override
	public DiffDTO getDiff(Long id) throws BinaryDataException {
		
		Optional<BinaryData> savedBinaryData = repository.findById(id);
		if(!savedBinaryData.isPresent()) {
			throw new BinaryDataException(BinaryDataConstants.MESSAGE_DATA_DOES_NOT_EXIST);
		}
		
		BinaryData data = savedBinaryData.get();
		DiffDTO responseData = new DiffDTO();
		
		byte[] left = data.getLeftData();
		byte[] right = data.getRightData();
		
		if(left == null || right == null) {
			throw new BinaryDataException(BinaryDataConstants.MESSAGE_INVALID_LEFT_RIGHT_DATA);
		} else if(data.getLeftData().length != data.getRightData().length) {
			responseData.setMessage(BinaryDataConstants.MESSAGE_NOT_SAME_SIZE);
		} else if(Arrays.equals(data.getLeftData(), data.getRightData())) {
			responseData.setMessage(BinaryDataConstants.MESSAGE_DATA_IS_EQUAL);
		} else {
			responseData.setMessage(BinaryDataConstants.MESSAGE_DATA_IS_NOT_EQUAL);
			responseData.setDiff(createDiffDetails(left, right));
		}
		
		return responseData;
	}
	
	private List<DiffDetail> createDiffDetails(byte[] left, byte[] right) {
		Map<Integer, Integer> diffMap = DiffCalculator.generateDiffMap(left, right);
		return diffMap.entrySet()
				.stream()
				.map(e -> new DiffDetail(e.getKey(), e.getValue()))
				.collect(Collectors.toList());
	}

}
