package com.nirvana.assignment;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nirvana.assignment.common.BusinessException;

@ExtendWith(MockitoExtension.class)
public class DiffServiceImplTest {
	
	private static final long SAMPLE_ID = 1L;
	private static final byte[] SAMPLE_BYTE_DATA = new byte[] { 1, 0, 1 };
	private static final byte[] DIFF_SAMPLE_BYTE_DATA = new byte[] { 1, 1, 0 };
	private static final byte[] BIGGER_SAMPLE_BYTE_DATA = new byte[] { 1, 0, 1, 1 };
	
	@InjectMocks
	private DiffServiceImpl service;
	
	@Mock
	private BinaryDataRepository mockRepository;
	
	private BinaryData binaryData;
	
	@BeforeEach
	public void setUp() {
		binaryData = new BinaryData();
		binaryData.setId(SAMPLE_ID);
	}

	@Test
	public void shouldGetDiffForEqualData() throws BusinessException {
		
		binaryData.setLeftData(SAMPLE_BYTE_DATA);
		binaryData.setRightData(SAMPLE_BYTE_DATA);
		
		when(mockRepository.findById(anyLong())).thenReturn(Optional.of(binaryData));
		
		DiffDTO diffDTO = service.getDiff(SAMPLE_ID);
		
		assertAll(
			() -> assertNotNull(diffDTO),
			() -> assertEquals("Data is equal", diffDTO.getMessage()),
			() -> assertNull(diffDTO.getDiff())
		);
	}
	
	@Test
	public void shouldGetDiffExceptionIfLeftDataIsNull() throws BusinessException {
		
		binaryData.setRightData(SAMPLE_BYTE_DATA);
		
		when(mockRepository.findById(anyLong())).thenReturn(Optional.of(binaryData));
		
		assertThrows(BusinessException.class, () -> {
			service.getDiff(SAMPLE_ID);
		});
	}
	
	@Test
	public void shouldGetDiffExceptionIfRightDataIsNull() throws BusinessException {
		
		binaryData.setLeftData(SAMPLE_BYTE_DATA);
		
		when(mockRepository.findById(anyLong())).thenReturn(Optional.of(binaryData));
		
		assertThrows(BusinessException.class, () -> {
			service.getDiff(SAMPLE_ID);
		});
	}
	
	@Test
	public void shouldGetDiffExceptionIfResourceNotFound() throws BusinessException {
		
		when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		assertThrows(BusinessException.class, () -> {
			service.getDiff(SAMPLE_ID);
		});
	}
	
	@Test
	public void shouldGetDiffExceptionIfSizeNotEqual() throws BusinessException {
		
		binaryData.setLeftData(SAMPLE_BYTE_DATA);
		binaryData.setRightData(BIGGER_SAMPLE_BYTE_DATA);
		
		when(mockRepository.findById(anyLong())).thenReturn(Optional.of(binaryData));
		
		assertThrows(BusinessException.class, () -> {
			service.getDiff(SAMPLE_ID);
		});
	}
	
	@Test
	public void shouldGetDiffForSameSizeData() throws BusinessException {
		
		binaryData.setLeftData(SAMPLE_BYTE_DATA);
		binaryData.setRightData(DIFF_SAMPLE_BYTE_DATA);
		
		when(mockRepository.findById(anyLong())).thenReturn(Optional.of(binaryData));
		
		DiffDTO diffDTO = service.getDiff(SAMPLE_ID);
		
		assertAll(
			() -> assertNotNull(diffDTO),
			() -> assertEquals("Data is not equal. Check diff details.", diffDTO.getMessage()),
			() -> assertEquals(1, diffDTO.getDiff().size()),
			() -> assertEquals(1, diffDTO.getDiff().get(0).getOffset()),
			() -> assertEquals(2, diffDTO.getDiff().get(0).getLength())
		);
	}

}
