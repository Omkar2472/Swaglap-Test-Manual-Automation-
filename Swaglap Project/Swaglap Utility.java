package SwaglapProject;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class SwaglapUtility {

   // Start of deleclartion of variables/objects used through the class
	    public WebDriver driver;
	    public static ExtentHtmlReporter htmlReporter;
	    public static ExtentReports report;
	    public static ExtentTest logger;
	
   // Generalising the parameter value with System.setProperty method
	    String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());	
	    String browsername;
	    String applicationLink;
    
    
    // Here you Have To update your path and the excel sheet
	    String ExcelPath ="C:\\Users\\xyz\\OneDrive\\Documents\\swaglap.xlsx";
	  
    // This Method is Defiened to Read The Data from Excel File and Browser Setting  
           public void driverSetting(){
    	// Take the Browser and application link name from excel file here
    	     try{
	    		FileInputStream fs = new FileInputStream(ExcelPath);
	    		XSSFWorkbook workbook = new XSSFWorkbook(fs);
	    		XSSFSheet sheet1 = workbook.getSheet("Sheet1");
	    	//	XSSFSheet sheet1 = workbook.getSheetAt(1);
	    		XSSFRow row = sheet1.getRow(1);
	    		
	    		browsername = row.getCell(0).getStringCellValue();
	    		applicationLink = row.getCell(1).getStringCellValue();
	    		System.out.println(browsername);
	    		System.out.println(applicationLink);
	    		workbook.close();
	    		fs.close();
	    	}
    	     catch(Exception e){
    		   e.printStackTrace();
    	        }
    	
       
	    	//Initializing the browser based on the browser name
	    	
	//    	if(browsername.equalsIgnoreCase("firefox"))
	//    	{
	////    		System.setProperty(sDriver, sDriverPath);
	////    		driver = new FirefoxDriver();
	//    	}
	    	if(browsername.equalsIgnoreCase("chrome")){
	    		System.out.println("hiii");
	    		System.setProperty("webdriver.chrome.driver", "E:\\omkar\\projectjava\\driverchrome1\\chromedriver.exe");  
	    	    ChromeOptions options = new ChromeOptions(); 
	    	    options.setBinary("E:\\chrome-win64\\chrome-win64\\chrome.exe");
	    	    driver =new ChromeDriver(options);
	    	}
	    	else if(browsername.equalsIgnoreCase("edge")){
	//    		System.setProperty(sDriver, sDriverPath);
	//    		driver = new EdgeDriver();
	    	}
	    	
	    	driver.manage().window().maximize();
	    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    	driver.get(applicationLink);
	    }
    
	    public WebElement elementfinder(By objby){
	    	WebElement temp = driver.findElement(objby);
	    	return temp;
	    }
	    
	    @BeforeSuite
	    public void initextentreport(){
	    	// Reading current date and time from system and appending
	    	// it in file name so no file will be replaced
	    	String path ="E:\\omkar\\projectjava\\src\\Reports\\";
	    
	    	File file = new File(path);
	    	
	    	
	    	htmlReporter = new ExtentHtmlReporter(path+timeStamp+".html");
	    	report = new ExtentReports();
	    	report.attachReporter(htmlReporter);
	  }
	    
	    public void StartTestCase(String Name)
	    {
	    	logger = report.createTest(Name);
	    	
	    }
	    
	    @AfterSuite
	    public void EndAllReports(){
	    	report.flush();
	    }
    
	    // Method to mark the Status of test steps
	    
	    public void markStatus(String status,String description){
	    	if(status.equalsIgnoreCase("pass"))
	    	{
	    		logger.log(Status.PASS,description );
	    	}
	    	else if(status.equalsIgnoreCase("fail"))
	    	{
	    		logger.log(Status.FAIL, "Fail :"+description);
	        }
	    	else if(status.equalsIgnoreCase("info"))
	    	{
	    		logger.log(Status.INFO,description);
	        }
	    	else if(status.equalsIgnoreCase("error"))
	    	{
	    		logger.log(Status.ERROR,description);
	        }
	    	else if(status.equalsIgnoreCase("warning"))
	    	{
	    		logger.log(Status.WARNING,description);
	        }
	    	
	    }
    
	    public void takescreenshot(String Filename){
	    	String path ="C:\\Users\\xyz\\OneDrive\\Documents\\Reports\\";
	//    	File file = new File(path);
	    	
	//    	if(!file.exists()){
	//    		file.mkdir();
	//    	}
	    	File source =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        File dest = new File(path+Filename+timeStamp +".png");
	        try{
	        	FileHandler.copy(source, dest); 
	        }
	        catch(IOException e)
	        {
	        	e.printStackTrace();
	        }
	    }
	    
    
	    public void identifyIframe(String elementType, String value){
	    	if(elementType.equalsIgnoreCase("name"))
	    	{
	    		driver.switchTo().frame(value);
	    	}
	    }
	    
	    public void SwitchToDefaultApplication()
	    {
	    	driver.switchTo().defaultContent();
	    }
	    
	  
	    public void compare(String expected, String actual, String passmessage, String failmessage)
	    {
	    	if(expected.equals(actual))
	    	{
	    		markStatus("pass",passmessage);
	    	}
	    	else
	    	{
	    		markStatus("fail",failmessage);
	    	}
	    }

	    public ArrayList<String> dataread(String filepath, String sheetname, int cellno)
	        {
		        ArrayList<String> obja= new ArrayList<String>();
		    	try{
		    		FileInputStream fs = new FileInputStream(filepath);
		    		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		    		XSSFSheet objsheet = workbook.getSheet(sheetname);
		    		
		    		int rowcount = objsheet.getLastRowNum();
		    		for(int a=1; a<=rowcount;a++) // loop for rows
		    		{
		    			XSSFRow row = objsheet.getRow(a);
		    			String temp = row.getCell(cellno).getStringCellValue(); // picking every value from rows
		    			obja.add(temp);  // Saving value in arraylist
		    		}
		    		workbook.close();
		    		fs.close();
		    	    }
		    	  catch(Exception e){
		    		e.printStackTrace();
		        }
		    	   return obja;
	         }
	   public void flushoff(){
	    	 driver.quit();
	     }
	}

