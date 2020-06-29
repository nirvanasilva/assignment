package com.nirvana.assignment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

class DiffCalculatorTest {
	
	@Test
	public void shouldGetEmptyDiffListIfNotSameSizeArray() {
		byte[] left = new byte[] { 1, 2, 3 };
		byte[] right = new byte[] { 1, 2 };
		
		Map<Integer, Integer> diffs = DiffCalculator.generateDiffMap(left, right);
		
		assertAll(
			() -> assertNotNull(diffs),
			() -> assertEquals(0, diffs.size())
		);
	}

	@Test
	public void shouldGetEmptyDiffListIfEqualArrays() {
		byte[] left = new byte[] { 1, 2 };
		byte[] right = new byte[] { 1, 2 };
		
		Map<Integer, Integer> diffs = DiffCalculator.generateDiffMap(left, right);
		
		assertAll(
			() -> assertNotNull(diffs),
			() -> assertEquals(0, diffs.size())
		);
	}
	
	@Test
	public void shouldGetDiffList() {
		byte[] left = new byte[] { 0, 1, 0, 1, 2, 3 };
		byte[] right = new byte[] { 1, 1, 0, 2, 4, 3 };
		
		Map<Integer, Integer> diffs = DiffCalculator.generateDiffMap(left, right);
		
		assertAll(
			() -> assertNotNull(diffs),
			() -> assertEquals(2, diffs.size(), "Diff list size is incorrect"),
			() -> assertTrue(diffs.containsKey(0), "Diff offset is incorret"),
			() -> assertEquals(1, diffs.get(0), "Diff length for offset is incorrect"),
			() -> assertTrue(diffs.containsKey(3), "Diff offset is incorret"),
			() -> assertEquals(2, diffs.get(3), "Diff length for offset is incorrect")
		);
	}
}
