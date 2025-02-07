package rahulshettyacademy.TestComponents;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.Class2B_LandingPage;


public class BaseTest {
	
      public WebDriver driver;                         // define the driver at class level
      public Class2B_LandingPage landingPageObj1;      //define the object here so it can be used in child class submittOrder too.
      ExtentReports extentReport;                      //Define the Extent report object variable.
	                    
	
			//We want a code so we can invoke any random browser. So what we can do, we can maintain a "global properties file".
			//	Global properties file: Where u can store which browser u want to execute. 
				   //let say you if want chrome so chrome browser code will executed.
				   //if u say FireFox:  so Firefox related code will executed.
				
			//How to Set global property: In java there is one class called Properties. this class read the property
				      //and decided at run time on which browser ur Framwork has to run the test.
				
			// So for this we have to create a "File" GlobalData.properties.	
			//  properties Class..... if it had ".properties" extension then java had a feature to read that file. 
				
			//Method1: Set the driver and call the Browser:
				 
    public WebDriver intilizeDriver() throws IOException
		    {          
			
			  Properties prop = new Properties();
			  
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
			  
			  prop.load(fis);                       // use this to load ur property file. when u load this method will automatically parse that properties file and extract all key value from it.
			   
			   //this load method want file  as a inputstream as a argument. but here file in ".properties".
			   //so first convert it  and then send to load. use the method "FileInputStream" which convert your property file into input streaam.
			  
			 // String browserName = prop.getProperty("browser");	
			  
						
	//Java ternary Oparator:
			  
		String browserName =  System.getProperty("browser")!= null ? System.getProperty("browser") :prop.getProperty("browser");
			  
			   
						
				if(browserName.contains("chrome"))
				{
					System.setProperty("Webdriver.chrome.driver","C:\\Users\\hp\\Documents\\chromedriver.exe");
					
					
					
				   ChromeOptions options = new ChromeOptions();
				
				 	if(browserName.contains("headless"))
					 {
				       options.addArguments("headless");       //the arguments to use when starting Chrome browser.
					 }
					 
					
				 driver = new ChromeDriver(options);
				 driver.manage().window().setSize(new Dimension(1440,900));  // full screen standard
				 
			 	}
				
			
				else if(browserName.equalsIgnoreCase("firefox"))
				{
					System.setProperty("Webdriver.gecko.driver","C:\\Users\\hp\\Documents\\geckodriver.exe");
					   driver=new FirefoxDriver();
				}
				
				
				/*			
				else if(browserName.equalsIgnoreCase("edge"))
				{
					 WebDriverManager.edgedriver().setup();
					 driver = new EdgeDriver();
					 
				}
				
				*/
				  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			 	  driver.manage().window().maximize();
			 	  
			 	return driver;
			
     	} //method close
   
//Method2: Get the URL.
   //get the URL: Because if 100 test case on landing page so u have to invoke the URL every time.
   
  // so create a method launch application which commonly use between all the testcases.		           

   //Launch Application: 
          
           @BeforeMethod(alwaysRun = true)
      public Class2B_LandingPage launchApplication() throws IOException 
		      {  
        	         driver = intilizeDriver();  //First call the intilizeDriver method to set the driver and all things and then take the driver.
        	 
        	//create Object for landing page: 	
   			      landingPageObj1 = new Class2B_LandingPage(driver);  // define the variable at class level 
   			 
   			// get and Launch the URL:
   		              landingPageObj1.goTo(); // url call from the landing page class
   		              
   		              return landingPageObj1;
   		              
		       }
		  @AfterMethod(alwaysRun= true)
		  public void afterTestRun()
		  {
			driver.close();
		  }
		  
// Extent Reprot Config:
			
		//Required 2 class,   ExtentSparkReporter to create the report and ExtentReports to drive the all execution.
			   
		  
			 @BeforeTest(alwaysRun = true)
			 public ExtentReports reportConfig()
			 {
				//1.Create the Object of ExtentSparkReproter: and Send the file path
				 
				 String destPath = System.getProperty("user.dir")+ "\\TestsReports\\index.html";
				    ExtentSparkReporter reporter = new ExtentSparkReporter(destPath);
				    reporter.config().setReportName("Web Automation Result");
				    reporter.config().setDocumentTitle("Test Result");
				    
				//2. Create object of ExtentReprots:
				    
				    extentReport = new ExtentReports();   //object
				    extentReport.attachReporter(reporter);
				    extentReport.setSystemInfo("Tester Name", "RahulShetty");
				    
				    return extentReport;
				   
			 }
//ScreenShot Utility:*****************************************************************************************************************
			   
			   public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
			   {
				//First cast driver to screnshot mode.   
				   TakesScreenshot ts =   (TakesScreenshot)driver;    //so driver know that it has to take a picture now.
			 // set the output type in file format:  
				  File sourceFile =  ts.getScreenshotAs(OutputType.FILE);  // take screenshot
				  
			// Now store/copy this captured screenshot into a folder for us.
				  
				  File destFile = new File(System.getProperty("user.dir") + "\\TestsReports" + testCaseName + ".png"); // this return file
				  // but System.getProperty("user.dir") + "\\TestsReports" + testCaseName + ".png": this return string
				  
				  FileUtils.copyFile(sourceFile, destFile);
				  
		  //if some one want destination file path:		  
				    return System.getProperty("user.dir") + "\\TestsReports" + testCaseName + ".png";
				   
				   
			   }	
			 
			 
   
}
