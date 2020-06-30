package com.nirvana.assignment;

import java.util.HashMap;
import java.util.Map;

public final class DiffCalculator {
	
	private DiffCalculator() {}
	
	/**
	 * Given two arrays of bytes, creates a Map with offsets and lengths. 
	 * Key is the offset and Value is the length.
	 * @param left
	 * @param right
	 * @return map with offsets and lengths
	 */
	public static Map<Integer, Integer> generateDiffMap(byte[] left, byte[] right) {
		if(left.length != right.length) return new HashMap<Integer, Integer>();
		
		Map<Integer, Integer> diffMap = new HashMap<Integer, Integer>();
		int lastOffsetIndex = -1;
		for(int b = 0; b < left.length; b++) {
			if(left[b] != right[b]) {
				if(lastOffsetIndex == -1) {
					lastOffsetIndex = b;
				}
				
				if(diffMap.containsKey(lastOffsetIndex)) {
					diffMap.put(lastOffsetIndex, diffMap.get(lastOffsetIndex) + 1);
				} else {
					diffMap.put(lastOffsetIndex, 1);
				}
			} else {
				lastOffsetIndex = -1;
			}
		}
		return diffMap;
	}
}
