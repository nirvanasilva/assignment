package com.nirvana.assignment.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nirvana.assignment.dto.BinaryDataDTO;
import com.nirvana.assignment.entity.BinaryData;
import com.nirvana.assignment.repository.BinaryDataRepository;

@ExtendWith(MockitoExtension.class)
public class BinaryDataServiceImplTest {
	
	private static final long SAMPLE_ID = 1L;
	private static final byte[] SAMPLE_DATA = new byte[] { 0, 0, 1 };

	@InjectMocks
	private BinaryDataServiceImpl service;
	
	@Mock
	private BinaryDataRepository mockRepository;
	
	private BinaryDataDTO inputData;
	private BinaryData expected;
	
	@BeforeEach
	public void setUp() {
		inputData = createInputData();
		expected = createBinaryData();
	}
	
	@Test
	public void shouldAddLeftDataToBinaryData() {
		when(mockRepository.save(any(BinaryData.class))).thenReturn(expected);
		when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		BinaryData result = service.addLeftData(SAMPLE_ID, inputData);
		
		verify(mockRepository, times(1)).save(any(BinaryData.class));
		
		assertNotNull(result);
		assertAll(
			() -> assertEquals(expected.getId(), result.getId()),
			() -> assertArrayEquals(expected.getLeftData(), result.getLeftData())
		);
	}

	@Test
	public void shouldUpdateLeftDataOfBinaryData() {
		when(mockRepository.save(any(BinaryData.class))).thenReturn(expected);
		when(mockRepository.findById(anyLong())).thenReturn(Optional.of(expected));
		
		BinaryData result = service.addLeftData(SAMPLE_ID, inputData);
		
		verify(mockRepository, times(1)).save(any(BinaryData.class));
		
		assertNotNull(result);
		assertAll(
			() -> assertEquals(expected.getId(), result.getId()),
			() -> assertArrayEquals(expected.getLeftData(), result.getLeftData())
		);
	}
	
	@Test
	public void shouldAddRightDataToBinaryData() {
		when(mockRepository.save(any(BinaryData.class))).thenReturn(expected);
		when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		BinaryData result = service.addRightData(SAMPLE_ID, inputData);
		
		verify(mockRepository, times(1)).save(any(BinaryData.class));
		
		assertNotNull(result);
		assertAll(
			() -> assertEquals(expected.getId(), result.getId()),
			() -> assertArrayEquals(expected.getRightData(), result.getRightData())
		);
	}
	
	@Test
	public void shouldUpdateRightDataOfBinaryData() {
		when(mockRepository.save(any(BinaryData.class))).thenReturn(expected);
		when(mockRepository.findById(anyLong())).thenReturn(Optional.of(expected));
		
		BinaryData result = service.addRightData(SAMPLE_ID, inputData);
		
		verify(mockRepository, times(1)).save(any(BinaryData.class));
		
		assertNotNull(result);
		assertAll(
			() -> assertEquals(expected.getId(), result.getId()),
			() -> assertArrayEquals(expected.getRightData(), result.getRightData())
		);
	}

	private BinaryDataDTO createInputData() {
		BinaryDataDTO inputData = new BinaryDataDTO();
		inputData.setData(SAMPLE_DATA);
		return inputData;
	}
	
	private BinaryData createBinaryData() {
		BinaryData expected = new BinaryData();
		expected.setId(SAMPLE_ID);
		expected.setLeftData(SAMPLE_DATA);
		expected.setRightData(SAMPLE_DATA);
		return expected;
	}

}
