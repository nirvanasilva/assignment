package com.nirvana.assignment;

import java.util.HashMap;
import java.util.Map;

public final class DiffCalculator {
	
	private DiffCalculator() {}
	
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
