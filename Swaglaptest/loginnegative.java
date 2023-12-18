package Swaglaptest;

import java.util.ArrayList;
import org.testng.annotations.Test;
import SwaglapRepositery.loginpage;
import SwaglapProject.SwaglapUtility;

	public class loginnegative extends SwaglapUtility 
	{
	
		loginpage objloginrepo = new loginpage();
		
		ArrayList<String> usernames = dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet3",0);
		ArrayList<String> passwords = dataread("C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx","Sheet3",1);
		
		String experrormsg = "Epic sadface: Sorry, this user has been locked out.";
		
		@Test(priority=1)
		public void initbrowser()
		{
			driverSetting();
			
		}
      
      
      @Test(priority=2)
      public void login()
	  {
    	  StartTestCase("Login negative scenario");
    	      for(int i=0;i<usernames.size();i++)
    	      {
    		  elementfinder(objloginrepo.username).sendKeys(usernames.get(i));
    		  elementfinder(objloginrepo.password).sendKeys(passwords.get(i));
    		  elementfinder(objloginrepo.loginbutton).click();
    		  
    	      String actualerrormsg = elementfinder(objloginrepo.incorrectcredentialserrormessage).getText();
    	      compare(experrormsg, actualerrormsg, "Error message dispalyed correctly for user - " + usernames.get(i),
    			"Error message is NOT dispalyed correctly for user - " + usernames.get(i));
    	
    	      }
    	      
    	    driver.close();
       }
       public void close()
	   {
    	   flushoff();
       }
    }
