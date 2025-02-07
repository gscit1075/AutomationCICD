package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class Class2B_CheckOutPage extends AbstractComponents {
	
	WebDriver driver;

	public Class2B_CheckOutPage(WebDriver driver) {
		super(driver);
        this.driver=driver;
       PageFactory.initElements(driver, this);
		
	}

	
//PageFacory:
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	
//Action Methods:
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		waitForElementVisiblity(By.cssSelector(".ta-results"));
		selectCountry.click();
		
	}
	
	public Class2B_ConfirmationPage placeOrder()
	{
		submit.click();
		Class2B_ConfirmationPage confirmation = new Class2B_ConfirmationPage(driver);
	
		return confirmation;
	}
	
	
}
