package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class Class2B_ConfirmationPage extends AbstractComponents {
	
	WebDriver driver;

	public Class2B_ConfirmationPage(WebDriver driver) 
	 {
		   super(driver);
		   
		  this.driver=driver;
		  
		  PageFactory.initElements(driver, this);
		
	 }

//PageFactory:	
	
	@FindBy(css= ".hero-primary")
	WebElement confirmText;
	
	public String getConfirmationText()
	{
		return confirmText.getText();
	 
		
	}
	
	
	
	
	
	
	

}
