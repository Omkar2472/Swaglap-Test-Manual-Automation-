package SwaglapRepositery;

import org.openqa.selenium.By;

public class loginpage {
           public By username = By.id("user-name");
		   public By password = By.id("password");
		   public By loginbutton = By.id("login-button");
		   public By incorrectcredentialserrormessage = By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3");
}
