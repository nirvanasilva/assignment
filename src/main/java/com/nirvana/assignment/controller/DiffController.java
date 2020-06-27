package com.nirvana.assignment.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirvana.assignment.dto.BinaryDataDTO;
import com.nirvana.assignment.entity.BinaryData;
import com.nirvana.assignment.service.BinaryDataService;

@RestController
@RequestMapping("/v1/diff")
public class DiffController {
	
	private BinaryDataService service;
	
	public DiffController(BinaryDataService service) {
		this.service = service;
	}
	
	@PostMapping(path = "/{id}/left", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BinaryData> addLeftData(@PathVariable("id") Long id, @RequestBody @Valid BinaryDataDTO binaryDataDTO) {
		BinaryData binaryData = service.addLeftData(id, binaryDataDTO);
		return new ResponseEntity<BinaryData>(binaryData, HttpStatus.OK);
	}
	
	@PostMapping(path = "/{id}/right", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BinaryData> addRightData(@PathVariable("id") Long id, @RequestBody @Valid BinaryDataDTO binaryDataDTO) {
		BinaryData binaryData = service.addRightData(id, binaryDataDTO);
		return new ResponseEntity<BinaryData>(binaryData, HttpStatus.OK);
	}

}
