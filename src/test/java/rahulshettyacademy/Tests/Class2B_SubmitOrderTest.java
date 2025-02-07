package rahulshettyacademy.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.data.Json_DataReader;
import rahulshettyacademy.pageobjects.Class2B_CartPage;
import rahulshettyacademy.pageobjects.Class2B_CheckOutPage;
import rahulshettyacademy.pageobjects.Class2B_ConfirmationPage;
import rahulshettyacademy.pageobjects.Class2B_LandingPage;
import rahulshettyacademy.pageobjects.Class2B_OrderPage;
import rahulshettyacademy.pageobjects.Class2B_ProductCatalouge;

public class Class2B_SubmitOrderTest extends BaseTest {
     String productName = "IPHONE 13 PRO";
	  
    @Test(dataProvider="getuserData",groups= {"purchase"})
    public void orderSubmit(HashMap<String, String> hashmap) throws InterruptedException, IOException
          {
   
			//1. Landing Page:	
					//  Class2B_LandingPage landingPageObj1 =  launchApplication(); // Retrun Object let remove it bu using the @BeforeMethod
					     
			       //  call the login method from landing page.
					   //Click on login button too:
					          Class2B_ProductCatalouge obj2ProductPage = landingPageObj1.appLogin(hashmap.get("email"), hashmap.get("password")); //return Object too.
					       
			//2. Product Catalouge page:
					       
			       // Click on the add to cart button:	     
					      
					         obj2ProductPage.addToCart(hashmap.get("productName"));
					       
			        //Go to cart by click on the Cart button :	
					      
					        Class2B_CartPage cartPage = obj2ProductPage.clickCartButton();   //return Object too.
					       
			//3Cart Page:   
					         
					        boolean match = cartPage.verifyProductDisplay(hashmap.get("productName"));
					       
					   //Assertion:	
							Assert.assertTrue(match);	
							
			           //CheckOut button click:		
							Class2B_CheckOutPage checkOutPage = cartPage.checkOut();	//retrun object too	
							
			//4. Check out Page:
							
							checkOutPage.selectCountry("india");
							Class2B_ConfirmationPage confirmationPage  = checkOutPage.placeOrder();
							
							
			//Confirmation Page:  Thankyou for the order. 
							
							confirmationPage.getConfirmationText();  
							
							AssertJUnit.assertTrue(confirmationPage.getConfirmationText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
							
							System.out.println("End to Code Successfull Done");     
	         }
    
 //Orders Page: *************************************************************************************************************************
        // lets One more Testcase to Verify the "IPHONE 13 PRO" is displaying in the Orders Page after "Placing the Order" successfully.
    
    
       @Test(dependsOnMethods= {"orderSubmit"})
       public void yourOrdersHostory()
       {
    	    /* // Scenarios:
    				* Depends on the method to first execute submit order.Close the driver
    				* Landing page 
    				* Login Again
    				* Select the ORDERS tab from the header.
    	   */    			  	 
    			
    	// 1. call the methods of landing Page:
    	   Class2B_ProductCatalouge obj2ProductPage = landingPageObj1.appLogin("mahesh.gscit1075@gmail.com", "P455w0rd@1"); //return Object too.
	       
    	//  2. Call the method of go to OrderPage:
    	   
    	   Class2B_OrderPage orderPage =  obj2ProductPage.goToOrderPage();
    	     boolean match2 =  orderPage.verifyOrderProductDisplay(productName);
    	     Assert.assertTrue(match2);
    	   
       }
    
  // DataProvider:******************************************************************************************************************  
 //DataProvider: When we have to verify login with multiple dataset. So this driver the data and pass the multiple data set.
	   /* // Scenarios:
	        * Enter the first set of email and password
	        * Enter the 2nd set of email and password
	        * Verify that all goes one by one  
			*Chigu@gmail.com and password is P455w0rd@1
         */    
       
       
	   @DataProvider
	     public Object[][] getuserData() throws InterruptedException, IOException
	     {
		   
		// 1. First define the multi dimension array.	  
	    	  
	    	//  Object[][] dataSet = new Object[][] { {"mahesh.gscit1075@gmail.com","P455w0rd@1","IPHONE 13 PRO"},{"chigu@gmail.com","P455w0rd@1","qwerty"} };
	    	     //Object : it is contain all data type int, stirng, char... 
	              //retrun dataSet; 
		   
	    //2. HasMap: create the oject of HashMap class.
	    	  
	         //  HashMap<Object,Object> hashmap = new HashMap<Object,Object>(); 
	    	  
    	    // it is asking for string then insteead of Oject type string.  
    	  /*
	    	HashMap<String,String> hashmap1 = new   HashMap<String,String>();
	    	  hashmap1.put("email", "mahesh.gscit1075@gmail.com");
	    	  hashmap1.put("password", "P455w0rd@1");
	    	  hashmap1.put("productName", "IPHONE 13 PRO");
	    	  
	    	  
	       HashMap<String,String> hashmap2 = new   HashMap<String,String>();
	    	  hashmap2.put(email", "chigu@gmail.com");
	    	  hashmap2.put("pass"word", "P455w0rd@1");
	    	  hashmap2.put("productName", "qwerty");  
	      */
		   
		   // call the gerJsonDataToHashMap() method here:
		   
		        Json_DataReader jsonObject = new Json_DataReader();  //creat object first
		        
		 List <HashMap<String, String>>    dataList =  jsonObject.gerJsonDataToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");  // it will retrun list of Hashmap.
		   
                return new Object[][] { {dataList.get(0)},{dataList.get(1)} };
              
              
    	// 3. JASON file :  for HashMap creation.
              
    	 //Still it is looks to messy so why not create one Json file and put all the data there. 
         // Also create one utility file which scane this JSON and create HashMap out of it automatically :" return new Object[][] { {hashmap1},{hashmap2} }"
            
     }
       
       
/* old Methods Are below for Data provider for just FYI:
     //1st Set
    	  dataSet[0][0] = "mahesh.gscit1075@gmail.com";
    	  dataSet[0][1] = "P455w0rd@1";    	  
    //2nd Set
    	  dataSet[1][0] = "Chigu@gmail.com";
    	  dataSet[1][1] = "P455w0rd@1";
    	  
    
  // First define the multi dimension array.	  
    	  
   Object[][] dataSet = new Object[][] { {"mahesh.gscit1075@gmail.com","P455w0rd@1","IPHONE 13 PRO"},{"chigu@gmail.com","P455w0rd@1","qwerty"} };
    	   //Object : it is contain all data type int, stirng, char... 	
 * The problem is here if u have 15 dataset. so will write 15 parameter by defining  here , its look so Messy right. So to overcome this problem why not use "Hashmap' .  	    	  
 
 //Hashgmap:	
   @DataProvider: data provider also allowed to return hash map, okay. You can also send hash maps inside this arrays.
   
     *
     */
    

    

}
