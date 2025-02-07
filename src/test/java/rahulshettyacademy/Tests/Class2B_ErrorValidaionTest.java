package rahulshettyacademy.Tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.RetryAnalyzer;
import rahulshettyacademy.pageobjects.Class2B_CartPage;
import rahulshettyacademy.pageobjects.Class2B_CheckOutPage;
import rahulshettyacademy.pageobjects.Class2B_ConfirmationPage;
import rahulshettyacademy.pageobjects.Class2B_ProductCatalouge;

public class Class2B_ErrorValidaionTest extends BaseTest {
	
	String productName = "IPHONE 13 PRO";
	 
	   @Test(groups= {"ErrorHandling"},retryAnalyzer = RetryAnalyzer.class)
    public void loginErrorValidation() throws InterruptedException, IOException
	  {
		   /*
			//Scenarios:
				* GEt the URL = use the befoer method
				* Proceed to Landing Page
				* Enter the Incorrect USerNAme
				* Enter the Incorrect Password
				* Click on the Login button
				* Verify the incorrect password and email toast message.	
				* Create the Extent Reprot and capture the screnShot if fail in diffrent method.	
				* Create the entery in this test method	
		   */
		 		 
		 //Create Entry for test:  see the listener file:
		 
		 //1. Landing Page:	
			     // Class2B_LandingPage landingPageObj2 =  launchApplication(); // Return Object. but lets remove it by using the @BeforeMethod
			     
		 //2. Call the login method from landing page.
			   //Click on login button too:
			       landingPageObj1.appLogin("mahesh.gscit1075@gmail.com", "P455w0rd@1"); //return Object too.
			  
			       Assert.assertEquals(landingPageObj1.getErrorMessage(), "Incorrect email or@ password.");   	
	     } 
	 	     
	    @Test
	    public void productErrorValidation() throws InterruptedException, IOException
	       {
	    	  
	        /*
			//Nagetiv Scenarios:
				* GEt the URL = use the befoer method
				* Proceed to Landing Page
				* Enter the correct USerNAme
				* Enter the correct Password
				* Click on the Login button
				* Select the Product
				* Add to Cart
				* giver the incorrect product name and Verify that it return false.	
				* do assertion if false return	
		   */		 
		
						//1. Login on Landing Page:	
								
								  Class2B_ProductCatalouge obj2ProductPage = landingPageObj1.appLogin("mahesh.gscit1075@gmail.com", "P455w0rd@1"); //return Object too.
								       
								
						//2. Select the product and add to cart:
								       
						       // Click on the add to cart button:	     
								      
								         obj2ProductPage.addToCart(productName);
								       
						        //click on the Cart button to goto cartpage:	
								      
								         Class2B_CartPage cartPage = obj2ProductPage.clickCartButton();   //return Object too.
								         
								      
			     //3.Cart Page:   enter the cart page
								        boolean match = cartPage.verifyProductDisplay("@@@IPHONE 14 PRO");
								       
								   //Assertion fales:	
								        AssertJUnit.assertFalse(match);
							   
		}
	 
	 
	 
	 
	 
	 
	 
}
