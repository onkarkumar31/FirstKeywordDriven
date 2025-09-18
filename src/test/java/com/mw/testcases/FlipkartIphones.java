package com.mw.testcases;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.mw.base.TestBase;

public class FlipkartIphones extends TestBase{
  @Test
  public void f() {
	  List<WebElement> we = driver.findElements(By.xpath(OR.getProperty("iphones")));
	  //String text = driver.findElement(By.tagName("html")).getText();
	  //System.out.println(text);
	  
	  HashMap<String, Integer> hm = new HashMap<>();
	  
	  for(WebElement wb:we) {
		  
		  String name="";
		  int price=0;
		  
		  //System.out.println(wb.getText());
		  for(String s:wb.getText().split("\n")) {
			  
			  if(Pattern.matches("^Apple.*iPhone.*", s.strip()) && name.isBlank()) {
			  //if(s.startsWith("Apple") && name.isEmpty()) {
				 name = s; 
				 //System.out.println(s);
			  }
			  if (s.startsWith("₹") && price==0) {
				  price = Integer.parseInt(s.replace("₹", "").replaceAll(",", ""));
				  //System.out.println(price);
				  
			  }
			  if(price!=0 && !name.isBlank()) {
				  hm.put(name, price);
				  break;
			  }
			  //System.out.println(name+":"+price);
		  }
		  
		  //System.out.println(name+":"+price);
	  }
	  
	  System.out.println(hm);
	  
	  for(Map.Entry<String, Integer> entry: hm.entrySet()) {
		  
		  if (entry.getValue() > 74900) {
			  
			  System.out.println(entry);
		  }
	  }
	  //System.out.println(we);
	  
  }
}
