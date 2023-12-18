package Swaglaptest;

import java.util.ArrayList;
import org.testng.annotations.Test;
import SwaglapRepositery.homepage;
import SwaglapRepositery.loginpage;
import SwaglapProject.SwaglapUtility;

	public class loginpostive extends SwaglapUtility
	{
		loginpage objloginrepo = new loginpage();
		homepage objhomerepo = new homepage();
		String exphomeurl ="https://www.saucedemo.com/inventory.html";
		
		ArrayList<String> usernames = dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet2",0);
		ArrayList<String> passwords = dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet2",1);
	
		
		
		@Test(priority=0)
		public void initbrowser()
		{
			driverSetting();
			
		}
      
      @Test(priority=1)
      public void login()
	  {
    	  StartTestCase("Login postive");
    	      for(int i=0;i<usernames.size();i++){
    	    	  elementfinder(objloginrepo.username).sendKeys(usernames.get(i));
    	    	  elementfinder(objloginrepo.password).sendKeys(passwords.get(i));
    		  elementfinder(objloginrepo.loginbutton).click();
    		  
    		  
    		  String actualurl = driver.getCurrentUrl();
    		  System.out.println(actualurl);
    		  
    		  compare(exphomeurl, actualurl,"Login Successful with user " + usernames.get(i),
    		  "Login Failed with user " +usernames.get(i));
    		          
    		//For Logout
    		  elementfinder(objhomerepo.menu).click();
    		  elementfinder(objhomerepo.logout).click();
    		 }
    	}
		public void close()
		{
			flushoff();
		}
	
}
