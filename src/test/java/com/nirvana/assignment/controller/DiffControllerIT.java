package com.nirvana.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirvana.assignment.BinaryDataDTO;
import com.nirvana.assignment.entity.BinaryData;

@SpringBootTest
@AutoConfigureMockMvc
public class DiffControllerIT {
	
	private static final byte[] BINARY_DATA = new byte[] { 1, 1, 1 };

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testAddLeftData() throws Exception {
		
		BinaryDataDTO binaryDataDTO = new BinaryDataDTO();
		binaryDataDTO.setData(BINARY_DATA);
		
		MvcResult result = mockMvc.perform(
			post("/v1/diff/1/left")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(binaryDataDTO))
		).andExpect(status().isOk())
		.andReturn();
		
		String content = result.getResponse().getContentAsString();
		BinaryData response = objectMapper.readValue(content, BinaryData.class);

		assertAll(
			() -> assertEquals(1L, response.getId()),
			() -> assertArrayEquals(BINARY_DATA, response.getLeftData())	
		);
	}
	
	@Test
	public void testAddRightData() throws Exception {
		
		BinaryDataDTO binaryDataDTO = new BinaryDataDTO();
		binaryDataDTO.setData(BINARY_DATA);
		
		MvcResult result = mockMvc.perform(
			post("/v1/diff/1/right")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(binaryDataDTO))
		).andExpect(status().isOk())
		.andReturn();
		
		String content = result.getResponse().getContentAsString();
		BinaryData response = objectMapper.readValue(content, BinaryData.class);

		assertAll(
			() -> assertEquals(1L, response.getId()),
			() -> assertArrayEquals(BINARY_DATA, response.getRightData())	
		);
	}
	
}
