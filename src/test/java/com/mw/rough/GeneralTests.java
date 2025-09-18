package com.mw.rough;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GeneralTests {
	
	public static void main(String[] args) {
		
		String s = "Apple iPhone";
		
		if(Pattern.matches("^Apple.*iPhone", s)){
			System.out.println("matches");
			
		}
		
		s = "  my name is rogerwa";
		
		System.err.println(s.strip());
		
		String money = "000002000002300000";
		System.out.println(money.replaceAll("^0+|0+$", ""));
		money.replaceAll("regex", "replacement string");
		
		GeneralTests gt = new GeneralTests();
		gt.getData();
		
		
	}
	
	
	@Test(dataProvider="dp1")
	//public void f(List<Integer> l1, List<Integer> l2) {
	public void f(int l1, int l2) {
		System.out.println(l1+":"+l2);

		
	}
	
	@Test(dataProvider="dp2")
	public void f2(String name, int age) {
		System.out.println(name+":"+age);
		
	}
	
	@Test(dataProvider="dp3")
	public void f3(String bro1, String bro2) {
		
		System.out.println(bro1+" "+bro2);
	}
	
	@DataProvider(name="dp1")
	public Object[][] getData(){
		
		
		Object[][] data = new Object[2][2];
////		int[] a = {4,3,2};
////		List<Integer]> al = Arrays.asList(a);
//		
//		Integer[] numbersArray = {1, 2, 3, 4, 5};
//        List<Integer> numbersList = Arrays.asList(numbersArray);
//        
//        
//		
//		
//		data[0][0] = numbersList;
//		Integer[] l2 = {4,5,6,7,8};
//		data[1][0] = Arrays.asList(l2);
//		//System.out.println(data);
		
		data[0][0] = 2;
		data[0][1] = 3;
		data[1][0] = 4;
		data[1][1] = 5;
		
	
		return data;
	}

	
	@DataProvider(name="dp2")
	public Object[][] getData2(){
		
		Object[][] data = new Object[2][2];
		
		data[0][0] = "Ramesh";
		data[0][1] = 20;
		data[1][0] = "Rakesh";
		data[1][1] = 50;
	
		return data;
	}
	
	@DataProvider(name="dp3")
	public Object[][] getData3(){
		
		return new Object[][] {{"Dinesh", "Mahesh"}, { "chindi", "chor"}};
	}
}
