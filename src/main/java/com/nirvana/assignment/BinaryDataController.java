package com.nirvana.assignment;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirvana.assignment.common.BusinessException;

@RestController
@RequestMapping("/v1/diff")
public class BinaryDataController {
	
	private BinaryDataService binaryDataService;
	private DiffService diffService;
	
	public BinaryDataController(BinaryDataService binaryDataService, DiffService diffService) {
		this.binaryDataService = binaryDataService;
		this.diffService = diffService;
	}
	
	@PutMapping(path = "/{id}/left", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BinaryData> addLeftData(@PathVariable("id") Long id, @RequestBody @Valid BinaryDataDTO binaryDataDTO) {
		BinaryData binaryData = binaryDataService.addLeftData(id, binaryDataDTO);
		return new ResponseEntity<BinaryData>(binaryData, HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}/right", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BinaryData> addRightData(@PathVariable("id") Long id, @RequestBody @Valid BinaryDataDTO binaryDataDTO) {
		BinaryData binaryData = binaryDataService.addRightData(id, binaryDataDTO);
		return new ResponseEntity<BinaryData>(binaryData, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiffDTO> getDiff(@PathVariable("id") Long id) throws BusinessException {
		DiffDTO diff = diffService.getDiff(id);
		return new ResponseEntity<DiffDTO>(diff, HttpStatus.OK);
	}

}
