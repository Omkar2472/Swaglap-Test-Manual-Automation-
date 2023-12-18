package Swaglaptest;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import SwaglapRepositery.homepage;
import SwaglapRepositery.loginpage;
import SwaglapProject.SwaglapUtility;


	public class Productlist extends SwaglapUtility {
		  loginpage objloginrepo = new loginpage();
		  homepage objhomerepo = new homepage();
		  
		//for list of product
		  ArrayList<String> explistofproducts=dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet4",0);
		  ArrayList<String> usernames = dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet2",0);
	      ArrayList<String> passwords = dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet2",1);
	//     
	//      //for list of product
	//	  ArrayList<String> explistofproduct=dataread("E:\\selenium software\\testcaseexcel\\swaglap.xlsx","Sheet4",1);
	//		
		  
		   @Test(priority=1)
		      public void initbrowser()
		      {
		    	  driverSetting();
		    	  
		      }
	   
	   @Test(priority=2)
	      public void login(){
	    	  StartTestCase("Verify List of all product");
	    	  
	    	  ArrayList<String> actuallist= new ArrayList<String>();
	      	
	    	      for(int i=0;i<usernames.size();i++){
	    		  elementfinder(objloginrepo.username).sendKeys(usernames.get(i));
	    		  elementfinder(objloginrepo.password).sendKeys(passwords.get(i));
	    		  elementfinder(objloginrepo.loginbutton).click();
	    		  
	    		  
	    		  markStatus("info", "Testing the List with user - "+usernames.get(i));
	    		  
	    		  
	    		  List<WebElement> allproducts = driver.findElements(By.xpath("//div[@class='inventory_details_name large_size']"));

	    		  List<String> actuallist1 = new ArrayList<>();

	    		  for (int j = 0; j < allproducts.size(); j++) {
	    		      String temp = allproducts.get(j).getText();
	    		      actuallist1.add(temp);
	    		  }
	    		  
	    		  Reporter.log("Actual List of Products: " + actuallist1);
		      		
		      		
		      		for (int a = 0; a < explistofproducts.size() && a < actuallist1.size(); a++)
		      		{
		      		    compare(explistofproducts.get(a), actuallist1.get(a),
		      		            "Product Display Successfully - " + actuallist1.get(a),
		      		            "Product NOT Display Successfully -" + actuallist1.get(a));
		      		}

		      		actuallist1.clear();
		      		elementfinder(objhomerepo.menu).click();
		      		elementfinder(objhomerepo.logout).click();
		      	    }
		         }
	   
			   
			     public  void close()
			      {
			      	flushoff();
			      }
	      
}
	   
	   

