package com.nirvana.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirvana.assignment.dto.BinaryDataDTO;
import com.nirvana.assignment.entity.BinaryData;
import com.nirvana.assignment.repository.BinaryDataRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class DiffControllerIT {
	
	private static final String DIFF_LEFT_URL = "/v1/diff/1/left";
	private static final String DIFF_RIGHT_URL = "/v1/diff/1/right";
	
	private static final String SAMPLE_DATA = "samplQ==";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private BinaryDataRepository repository;
	
	@BeforeEach
	public void setUp() {
		repository.deleteAll();
	}
	
	@Test
	public void testAddLeftData() throws Exception {
		
		BinaryDataDTO binaryDataDTO = new BinaryDataDTO();
		binaryDataDTO.setData(SAMPLE_DATA);
		
		String input = objectMapper.writeValueAsString(binaryDataDTO);
		
		MvcResult result = mockMvc.perform(
			post(DIFF_LEFT_URL)
			.contentType(MediaType.APPLICATION_JSON)
			.content(input)
		).andExpect(status().isOk())
		.andReturn();
		
		String content = result.getResponse().getContentAsString();
		BinaryData response = objectMapper.readValue(content, BinaryData.class);

		assertAll(
			() -> assertEquals(1L, response.getId()),
			() -> assertEquals(SAMPLE_DATA, Base64.getEncoder().encodeToString(response.getLeftData()))	
		);
	}
	
	@Test
	public void testAddLeftDataUpdateExistingBinaryData() throws Exception {
		
		BinaryData savedData = new BinaryData();
		savedData.setId(1L);
		savedData.setLeftData(new byte[] { 0, 0, 1 });
		repository.save(savedData);
		
		BinaryDataDTO binaryDataDTO = new BinaryDataDTO();
		binaryDataDTO.setData(SAMPLE_DATA);
		
		MvcResult result = mockMvc.perform(
			post(DIFF_LEFT_URL)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(binaryDataDTO))
		).andExpect(status().isOk())
		.andReturn();
		
		String content = result.getResponse().getContentAsString();
		BinaryData response = objectMapper.readValue(content, BinaryData.class);

		assertAll(
			() -> assertEquals(1L, response.getId()),
			() -> assertEquals(SAMPLE_DATA, Base64.getEncoder().encodeToString(response.getLeftData()))	
		);
	}
	
	@Test
	public void testAddRightData() throws Exception {
		
		BinaryDataDTO binaryDataDTO = new BinaryDataDTO();
		binaryDataDTO.setData(SAMPLE_DATA);
		
		MvcResult result = mockMvc.perform(
			post(DIFF_RIGHT_URL)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(binaryDataDTO))
		).andExpect(status().isOk())
		.andReturn();
		
		String content = result.getResponse().getContentAsString();
		BinaryData response = objectMapper.readValue(content, BinaryData.class);
		
		assertAll(
			() -> assertEquals(1L, response.getId()),
			() -> assertEquals(SAMPLE_DATA, Base64.getEncoder().encodeToString(response.getRightData()))	
		);
	}
	
	@Test
	public void testAddRightDataWithInvalidJsonBody() throws Exception {
		
		mockMvc.perform(
			post(DIFF_RIGHT_URL)
			.contentType(MediaType.APPLICATION_JSON)
			.content(SAMPLE_DATA)
		).andExpect(status().isBadRequest());
		
		// TODO: check error message
	}
	
	@Test
	public void testAddRightDataWithInvalidData() throws Exception {
		
		BinaryDataDTO binaryDataDTO = new BinaryDataDTO();
		binaryDataDTO.setData("invalid data");
		
		mockMvc.perform(
			post(DIFF_RIGHT_URL)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(binaryDataDTO))
		).andExpect(status().isBadRequest());
		
		// TODO: check error message
	}
	
	@Test
	public void testAddRightDataWithInvalidEmptyData() throws Exception {
		
		mockMvc.perform(
			post(DIFF_RIGHT_URL)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(new BinaryDataDTO()))
		).andExpect(status().isBadRequest());
		
		// TODO: check error message
	}
	
}
