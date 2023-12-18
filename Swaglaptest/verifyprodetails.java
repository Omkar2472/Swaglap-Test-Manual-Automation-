package Swaglaptest;

import java.util.ArrayList;
import org.testng.annotations.Test;
import SwaglapRepositery.homepage;
import SwaglapRepositery.loginpage;
import SwaglapRepositery.productdetailspage;
import SwaglapProject.SwaglapUtility;

	public class verifyprodetails extends SwaglapUtility{
		loginpage objloginrepo = new loginpage();
		homepage objhomerepo = new homepage();
		productdetailspage objproductdetailsrepo = new productdetailspage();
		
	    ArrayList<String> prodDetailsExp= dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet5",0);
	    ArrayList<String> usernames = dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet2",0);
	    ArrayList<String> passwords = dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet2",1);
	  
	   
		ArrayList<String> prodDetailsActual= new ArrayList<String>();
		  
		 @Test(priority=1)
	     public void initbrowser()
	     {
	   	  driverSetting();
	   	  
	     }
	   
	 @Test(priority=2)
		public void login()
		{
			StartTestCase("Verify product details");
			for(int i=0; i<usernames.size(); i++) //loop to verify list for all valid usernames
			{
				elementfinder(objloginrepo.username).sendKeys(usernames.get(i));
				elementfinder(objloginrepo.password).sendKeys(passwords.get(i));
				elementfinder(objloginrepo.loginbutton).click();
				markStatus("info", "Testing product details with user - "+ usernames.get(i));

				elementfinder(objhomerepo.backpack).click();// Click on backpack link to navigate to detail page
				
				String pname=elementfinder(objproductdetailsrepo.productname).getText();
				String desc=elementfinder(objproductdetailsrepo.productdesc).getText();
				String cost=elementfinder(objproductdetailsrepo.productcost).getText();
				
				
				prodDetailsActual.add(pname);
				prodDetailsActual.add(desc);
				prodDetailsActual.add(cost);
				
				for(int j=0; j<prodDetailsExp.size();j++)//loop to compare actual and expected list
				{
					compare(prodDetailsExp.get(j), prodDetailsActual.get(j), 
							"The value is displayed correctly - "+prodDetailsActual.get(j), 
							"The value is NOT displayed correctly - "+prodDetailsActual.get(j));
				}
				
				prodDetailsActual.clear();
				
				elementfinder(objhomerepo.menu).click(); // logout the current user
				elementfinder(objhomerepo.logout).click();
			}
			driver.close();
		}
}

