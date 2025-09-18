package com.mw.rough;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ListPractive {
	
	public static void main(String[] args) {
		
		Integer[] x = {3,4,5};
		
		int[] a = {3,4,5};
		
		System.out.println(x);
		System.out.println(a);
		
		
		for(Integer i:x) {
			System.out.println(i.getClass());
		}
		
		for(int i:x) {
			System.out.println(i);
		}
		
		for(int i:a) {
			System.out.println(i);
		}
		
		Map<String, Integer> map = Map.of(
				"apple", 20,
				"banana", 30
				);
		
		Iterator itr = map.entrySet().iterator();
		
		while(itr.hasNext()) {
			
			Map.Entry<String, Integer> entry = (Entry<String, Integer>) itr.next();
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
		
		for(Map.Entry<String, Integer> entry:map.entrySet()) {
			
			System.out.println(entry.getKey()+ " "+ entry.getValue());
		}
		
		
		int[] marks = {20,40,50};
		List<Integer> l = List.of(20,40,50);
		for(int i:l) {
			System.out.println(i);
		}
	}

}
