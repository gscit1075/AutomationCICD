package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class Class2B_LandingPage extends AbstractComponents {
	
	WebDriver landDriver;

	  //WebElement userEmail = landdriver.findElement(By.id("userEmail"));
			// our chorme driver is created in Sumbmitorder test class. so this land driver does not have any life.
			// so have to pass that chorme driver to here by creating one constructor method.
	
//Peramertize Constructor:
	
	public Class2B_LandingPage(WebDriver driver)
	 {
		super(driver);
	    this.landDriver=driver;
	
		PageFactory.initElements(driver, this);   //which is used for providing the mechanism for finding elements
	
		//Why we writen "this Keyword" in initElement method? :  Because "this keyword' go like  driver and find the element. like this.findelement
	
	  }
	
//Initialize the Elements:	
	//PageFactory:
	
	@FindBy(id = "userEmail")   //it is work like this.findelement by(id("")). Here "this. " refer to the class level driver .
	WebElement uEmail;
	
	@FindBy(id="userPassword")   //it is work like this.findelement by(id("")). Here "this. " refer to the class level driver .
	WebElement password;
	
	@FindBy(css=".login-btn")   //it is work like this.findelement by(id("")). Here "this. " refer to the class level driver .
	WebElement submit;

	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;     // this is taken time to appear this webelement so use the wait webelement inside the method below.
	
	 //.ng-tns-c4-33.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	
    //**VeryIMP**: but for finding the element we need to define a PageFacotry Mathode inside the constructor.

	
	
//AppLogin : Lets create  action Method for above elemens:
	
	public Class2B_ProductCatalouge appLogin(String emailID, String pass)
	{
		uEmail.sendKeys(emailID);
		password.sendKeys(pass);
		submit.click();
		
		
      //After click it will goto the product catalouge page na so create that class object here:		
		
				Class2B_ProductCatalouge obj2ProductPage = new Class2B_ProductCatalouge(landDriver);
				return obj2ProductPage;
		
	}
	
// get the URL.	
	
	public void goTo()
	{
	
		landDriver.get("https://rahulshettyacademy.com/client");
	}
	
	
	public String getErrorMessage()  
	{
	   //wait before the element appear. becuase it is very quick
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
		
		
	}

}
