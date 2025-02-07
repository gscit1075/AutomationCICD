package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.Class2B_CartPage;
import rahulshettyacademy.pageobjects.Class2B_CheckOutPage;
import rahulshettyacademy.pageobjects.Class2B_ConfirmationPage;
import rahulshettyacademy.pageobjects.Class2B_LandingPage;
import rahulshettyacademy.pageobjects.Class2B_ProductCatalouge;

public class StepDefinitionImplimentation extends BaseTest {
	public Class2B_LandingPage landingPageObj1;
	public Class2B_ProductCatalouge obj2ProductPage;
	public Class2B_CheckOutPage checkOutPage;
	public Class2B_ConfirmationPage confirmationPage;
	

	@Given("I landed on the Ecommerce page")
	public void i_landed_on_the_ecommerce_landing_page() throws IOException
	{
		
		landingPageObj1 =launchApplication();
	
	}
	 //  Given Logged in with the username <name> and password <password>
	@Given ("^Logged in with the username (.+) and password (.+)$")
	public void logged_in_with_username_and_Password(String username, String password)
	{
		   obj2ProductPage = landingPageObj1.appLogin(username, password);
	}
	
	@When ("^Add the product(.+) to the cart$")
	public void Add_Product_To_Cart(String productName) throws InterruptedException
	{
		obj2ProductPage.addToCart(productName);
	      
	}
	//And checkout <productName> and submit the order
	@And ("^checkout (.+) and submit the order$")
	public void CheckOut_and_Submit_Order(String productName ) throws InterruptedException
	{
		Class2B_CartPage cartPage = obj2ProductPage.clickCartButton(); 
		boolean match = cartPage.verifyProductDisplay(productName);
	   	Assert.assertTrue(match);	
		checkOutPage = cartPage.checkOut(); 
		checkOutPage.selectCountry("india");
		confirmationPage  = checkOutPage.placeOrder();  //submit the order
	}
	
	@Then ("verify the message {string} is display")
	public void verify_the_Message(String string)
	{
		confirmationPage.getConfirmationText();  
		
		Assert.assertTrue(confirmationPage.getConfirmationText().equalsIgnoreCase(string));
		
		System.out.println("End to Code Successfull");  
		driver.close();
		
	}
	
	
	//verify "Incorrect email or password." is dispalyed
	@Then("verify {string} is dispalyed")
	public void somthing_message_is_dispalyed(String stringArg)
	{
		Assert.assertEquals(landingPageObj1.getErrorMessage(), stringArg);  
		driver.close();
	}
	
	

}
