package SwaglapRepositery;

import org.openqa.selenium.By;

public class productdetailspage {
         public By productname = By.xpath("//*[@id='inventory_item_container']/div/div/div[2]/div[1]");
	 public By productdesc = By.xpath("//div[@class='inventory_details_desc large_size']");
	 public By productcost = By.xpath("//div[@class='inventory_details_price']");
	 public By backtoproductsbutton = By.id("back-to-products");
} 
